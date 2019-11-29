package cc.catface.base.utils.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import cc.catface.base.utils.android.common_print.log.TLog;

/**
 * 图片加载及转化工具 ----------------------------------------------------------------------- 延伸：一个Bitmap到底占用多大内存？系统给每个应用程序分配多大内存？ Bitmap占用的内存为：像素总数
 * * 每个像素占用的内存。在Android中， Bitmap有四种像素类型：ARGB_8888、ARGB_4444、ARGB_565、ALPHA_8， 他们每个像素占用的字节数分别为4、2、2、1。因此，一个2000*1000的ARGB_8888
 * 类型的Bitmap占用的内存为2000*1000*4=8000000B=8MB。
 *
 * @author chen.lin
 */
public class BitmapUtil {

    /**
     * 1)软引用 ,已经不适合缓存图片信息,加载图片时会出现重叠的现象
     * 2)Android 3.0 (API Level 11)中，图片的数据会存储在本地的内存当中
     * 因而无法用一种可预见的方式将其释放，这就有潜在的风险造成应用程序的内存溢出并崩溃，
     * 3)因为从 Android 2.3 (API Level 9)开始，垃圾回收器会更倾向于回收持有软引用或弱引用的对象，
     * 这让软引用和弱引用变得不再可靠。
     */
    private static Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

    /**
     * 初始化lrucache,最少使用最先移除,LruCache来缓存图片，
     * 当存储Image的大小大于LruCache设定的值，系统自动释放内存，
     */
    private static LruCache<String, Bitmap> mMemoryCache;

    static {
        final int memory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = memory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            protected int sizeOf(String key, Bitmap value) {
                // return value.getByteCount() / 1024;
                return value.getHeight() * value.getRowBytes();
            }
        };
    }

    // ---lrucache----------------------------------------------------

    /**
     * 添加图片到lrucache
     *
     * @param key
     * @param bitmap
     */
    public synchronized void addBitmapToMemCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            if (key != null & bitmap != null) {
                mMemoryCache.put(key, bitmap);
            }
        }
    }

    /**
     * 清除缓存
     */
    public void clearMemCache() {
        if (mMemoryCache != null) {
            if (mMemoryCache.size() > 0) {
                mMemoryCache.evictAll();
            }
            mMemoryCache = null;
        }
    }

    /**
     * 移除缓存
     */
    public synchronized void removeMemCache(String key) {
        if (key != null) {
            if (mMemoryCache != null) {
                Bitmap bm = mMemoryCache.remove(key);
                if (bm != null) bm.recycle();
            }
        }
    }

    /**
     * 从lrucache里读取图片
     *
     * @param key
     * @return
     */
    public Bitmap getBitmapFromMemCache(String key) {
        if (key != null) {
            return mMemoryCache.get(key);
        }
        return null;
    }

    /**
     * 加载图片
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public void loadBitmap(Context context, int resId, ImageView imageView) {
        final String imageKey = String.valueOf(resId);
        final Bitmap bitmap = getBitmapFromMemCache(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(resId);
            BitmapWorkerTask task = new BitmapWorkerTask(context);
            task.execute(resId);
        }
    }

    /**
     * 任务类
     *
     * @author chenlin
     * @version 1.0
     * @Project App_View
     * @Package com.android.view.tool
     * @Date 2014年5月10日
     */
    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private Context mContext;

        public BitmapWorkerTask(Context context) {
            mContext = context;
        }

        // 在后台加载图片。
        @Override protected Bitmap doInBackground(Integer... params) {
            final Bitmap bitmap = decodeSampledBitmapFromResource(mContext.getResources(), params[0], 100, 100);
            addBitmapToMemCache(String.valueOf(params[0]), bitmap);
            return bitmap;
        }
    }

    // --软引用---------------------------------------------------------
    public static void addBitmapToCache(String path) {
        // 强引用的Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        // 软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitmap);
        // 添加该对象到Map中使其缓存
        imageCache.put(path, softBitmap);
    }

    public static Bitmap getBitmapByPath(String path) {
        // 从缓存中取软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = imageCache.get(path);
        // 判断是否存在软引用
        if (softBitmap == null) {
            return null;
        }
        // 取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
        Bitmap bitmap = softBitmap.get();
        return bitmap;
    }

    public Bitmap loadBitmap(final String imageUrl, final ImageCallBack imageCallBack) {
        SoftReference<Bitmap> reference = imageCache.get(imageUrl);
        if (reference != null) {
            if (reference.get() != null) {
                return reference.get();
            }
        }
        final Handler handler = new Handler() {
            public void handleMessage(final android.os.Message msg) {
                // 加入到缓存中
                Bitmap bitmap = (Bitmap) msg.obj;
                imageCache.put(imageUrl, new SoftReference<Bitmap>(bitmap));
                if (imageCallBack != null) {
                    imageCallBack.getBitmap(bitmap);
                }
            }
        };
        new Thread() {
            public void run() {
                Message message = handler.obtainMessage();
                message.obj = downloadBitmap(imageUrl);
                handler.sendMessage(message);
            }
        }.start();
        return null;
    }

    public interface ImageCallBack {
        void getBitmap(Bitmap bitmap);
    }

    // ----其它工具----------------------------------------------------------------------------------

    /**
     * 从网上下载图片
     *
     * @param imageUrl
     * @return
     */
    private Bitmap downloadBitmap(String imageUrl) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(imageUrl).openStream());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * drawable 转bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * bitmap 转 drawable
     *
     * @param bm
     * @return
     */
    public static Drawable bitmap2Drable(Bitmap bm) {
        return new BitmapDrawable(bm);
    }

    /**
     * 把字节数组通过BASE64Encoder转换成字符串
     *
     * @param image
     * @return
     */
    /*public static String getBase64(byte[] image) {
        String string = "";
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            string = encoder.encodeBuffer(image).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }*/

    /**
     * 把字节数据转换成Drawable
     *
     * @param imgByte 字节数据
     * @return
     */
    @SuppressWarnings("deprecation") public static Drawable byte2Drawable(byte[] imgByte) {
        Bitmap bitmap;
        if (imgByte != null) {
            bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            Drawable drawable = new BitmapDrawable(bitmap);

            return drawable;
        }
        return null;
    }

    /**
     * 把图片转换成字节数组
     *
     * @param bm
     * @return
     */
    public static byte[] bitmap2Byte(Bitmap bm) {
        Bitmap outBitmap = Bitmap.createScaledBitmap(bm, 150, bm.getHeight() * 150 / bm.getWidth(), true);
        if (bm != outBitmap) {
            bm.recycle();
            bm = null;
        }
        byte[] compressData = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            try {
                outBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            compressData = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compressData;
    }

    /**
     * 缩放图片
     *
     * @param bitmap    原图片
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap setBitmapSize(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = (newWidth * 1.0f) / width;
        float scaleHeight = (newHeight * 1.0f) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /**
     * 缩放图片
     *
     * @param bitmapPath 图片路径
     * @return
     */
    public static Bitmap setBitmapSize(String bitmapPath, float newWidth, float newHeight) {
        Bitmap bitmap = BitmapFactory.decodeFile(bitmapPath);
        if (bitmap == null) {
            TLog.d("bitmap", "bitmap------------>发生未知异常！");
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /**
     * 计算图片的缩放大小 如果==1，表示没变化，==2，表示宽高都缩小一倍 ----------------------------------------------------------------------------
     * inSampleSize是BitmapFactory.Options类的一个参数，该参数为int型， 他的值指示了在解析图片为Bitmap时在长宽两个方向上像素缩小的倍数。inSampleSize的默认值和最小值为1（当小于1时，解码器将该值当做1来处理），
     * 且在大于1时，该值只能为2的幂（当不为2的幂时，解码器会取与该值最接近的2的幂）。 例如，当inSampleSize为2时，一个2000*1000的图片，将被缩小为1000*500，相应地， 它的像素数和内存占用都被缩小为了原来的1/4：
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 原始图片的宽高
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // 在保证解析出的bitmap宽高分别大于目标尺寸宽高的前提下，取可能的inSampleSize的最大值
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 根据计算出的inSampleSize生成Bitmap(此时的bitmap是经过缩放的图片)
     *
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 首先设置 inJustDecodeBounds=true 来获取图片尺寸
        final BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * inJustDecodeBounds属性设置为true，decodeResource()方法就不会生成Bitmap对象，而仅仅是读取该图片的尺寸和类型信息：
         */
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // 计算 inSampleSize 的值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // 根据计算出的 inSampleSize 来解码图片生成Bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 将图片保存到本地时进行压缩, 即将图片从Bitmap形式变为File形式时进行压缩,
     * 特点是: File形式的图片确实被压缩了, 但是当你重新读取压缩后的file为 Bitmap是,它占用的内存并没有改变
     *
     * @param bmp
     * @param file
     */
    public static void compressBmpToFile(Bitmap bmp, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;// 个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将图片从本地读到内存时,进行压缩 ,即图片从File形式变为Bitmap形式
     * 特点: 通过设置采样率, 减少图片的像素, 达到对内存中的Bitmap进行压缩
     *
     * @param srcPath
     * @return
     */
    public static Bitmap compressImageFromFile(String srcPath, float pixWidth, float pixHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, options);

        options.inJustDecodeBounds = false;
        int w = options.outWidth;
        int h = options.outHeight;
        int scale = 1;
        if (w > h && w > pixWidth) {
            scale = (int) (options.outWidth / pixWidth);
        } else if (w < h && h > pixHeight) {
            scale = (int) (options.outHeight / pixHeight);
        }
        if (scale <= 0) scale = 1;
        options.inSampleSize = scale;// 设置采样率

        options.inPreferredConfig = Bitmap.Config.ARGB_8888;// 该模式是默认的,可不设
        options.inPurgeable = true;// 同时设置才会有效
        options.inInputShareable = true;// 。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(srcPath, options);
        // return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        // 其实是无效的,大家尽管尝试
        return bitmap;
    }

    /**
     * 判断照片的角度
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * Android根据设备屏幕尺寸和dpi的不同，给系统分配的单应用程序内存大小也不同，具体如下表
     *
     * 屏幕尺寸 DPI 应用内存 
     * small / normal / large ldpi / mdpi 16MB 
     * small / normal / large tvdpi / hdpi 32MB 
     * small / normal / large xhdpi 64MB
     * small / normal / large 400dpi 96MB 
     * small / normal / large xxhdpi 128MB 
     * ------------------------------------------------------- 
     * xlarge mdpi 32MB 
     * xlarge tvdpi / hdpi 64MB 
     * xlarge xhdpi 128MB 
     * xlarge 400dpi 192MB 
     * xlarge xxhdpi 256MB
     */

    /*---------------------*/
    /**
     * 保存照片quality[0-100]
     */
    public static boolean saveBitmap(Bitmap bitmap, String savePath, int quality) {

        File file = new File(savePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos); // 向缓冲区之中压缩图片
            bos.flush();
            bos.close();

        } catch (Exception e) {
            return false;

        } finally {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != bitmap && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }

            System.gc();
        }

        return true;
    }


    /* 高斯模糊 */
    // 图片缩放比例(即模糊度)
    private static final float BITMAP_SCALE = 1.0f;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1) public static Bitmap blurBitmap(Context context, Bitmap image, float blurRadius) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        blurScript.setRadius(blurRadius);
        blurScript.setInput(tmpIn);
        blurScript.forEach(tmpOut);

        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }



    /**
     * 根据给定的宽和高进行拉伸
     *
     * @param origin    原图
     * @param newWidth  新图的宽
     * @param newHeight 新图的高
     * @return new Bitmap
     */
    public static Bitmap scale(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    /**
     * 按比例缩放图片
     *
     * @param origin 原图
     * @param ratio  比例
     * @return 新的bitmap
     */
    public static Bitmap scale(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(ratio, ratio);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, true);
        if (newBM.equals(origin)) {
            return newBM;
        }
        //        origin.recycle();
        return newBM;
    }

    /**
     * 裁剪
     *
     * @param bitmap 原图
     * @return 裁剪后的图像
     */
    public static Bitmap crop(Bitmap bitmap) {

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        int startX = (int) (bitmapWidth * 0.1);
        int startY = (int) (bitmapHeight * 0.1);
        int widthScope = (int) (bitmapWidth * 0.8);
        int heightScope = (int) (bitmapHeight * 0.8);


        // 原图 | 起始X坐标 | 起始Y坐标 | 要截的宽度 | 要截的高度 | 矩阵变换像素 | 过滤原图(matrix包含翻转才有效)
        return Bitmap.createBitmap(bitmap, startX, startY, widthScope, heightScope, null, false);
    }


    public static Bitmap crop(Bitmap bitmap, float startX, float startY, float widthScope, float heightScope) {

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        int startXFinal = (int) (bitmapWidth * startX);
        int startYFinal = (int) (bitmapHeight * startY);
        int widthScopeFinal = (int) (bitmapWidth * widthScope);
        int heightScopeFinal = (int) (bitmapHeight * heightScope);

        // 原图 | 起始X坐标 | 起始Y坐标 | 要截的宽度 | 要截的高度 | 矩阵变换像素 | 过滤原图(matrix包含翻转才有效)

        return Bitmap.createBitmap(bitmap, startXFinal, startYFinal, widthScopeFinal, heightScopeFinal, null, false);
    }

    /**
     * 选择变换
     *
     * @param origin 原图
     * @param angle  旋转角度，可正可负
     * @return 旋转后的图片
     */
    public static Bitmap rotate(Bitmap origin, float angle) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(angle);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    /**
     * 偏移效果
     *
     * @param origin 原图
     * @return 偏移后的bitmap
     */
    public static Bitmap skew(Bitmap origin) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.postSkew(-0.6f, -0.3f);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }


    /**
     * 图片镜像翻转
     *
     * @param origin
     * @return
     */
    public static Bitmap convert(Bitmap origin) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(-1, 1);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    //     * 质量压缩图片，图片占用内存减小，像素数不变，常用于上传
    //     * @param image
    //     * @param size 期望图片的大小，单位为kb
    //     * @param options 图片压缩的质量，取值1-100，越小表示压缩的越厉害,如输入30，表示压缩70%

    public static Bitmap compressBitmap(Bitmap bitmap, int size, int options) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);

        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > size) {
            options -= 5;// 每次都减少10
            baos.reset();// 重置baos即清空baos
            // 这里压缩options%，把压缩后的数据存放到baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }


    /**
     * 将当前 View 转成 Bitmap 图片
     */
    public static Bitmap getBitmap(View view) {
        Bitmap bitmap;

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache(); // 启动 DrawingCache 并创建位图
        bitmap = view.getDrawingCache();
        view.setDrawingCacheEnabled(false); // 避免影响性能

        return bitmap;
    }


}