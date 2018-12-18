package cc.catface.base.utils.java.file.base;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileT {


    /*** 判断文件/目录是否存在 ***/
    public static boolean isFileInvalid(String filePath) {
        File file = new File(filePath);
        return null == file || !file.exists() || !file.isFile();
    }


    /*** create file or dir ***/
    // create dir and keep src dir
    public static boolean create(String path) {
        return create(path, false, false);
    }

    // create dir/file and keep src dir/file
    public static boolean create(String path, boolean isFile) {
        return create(path, isFile, false);
    }

    public static boolean create(String path, boolean isFile, boolean delSrcFile) {

        if ("".equals(path) || null == path) {
            return false;
        }

        try {

            if (isFile) {
                File file = new File(path);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                } else if (delSrcFile) {
                    file.delete();
                    file.createNewFile();
                }

            } else {
                File dir = new File(path);
                if (!dir.exists()) dir.mkdirs();

                else if (delSrcFile) {
                    del(path);
                    dir.mkdirs();
                }
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    /*** delete file or all of contents of dir ***/
    // control keep or not root dir
    public static void del(String path, boolean keepRootDir) {
        if (keepRootDir) {

            del(path);
            new File(path).mkdirs();

        } else {
            del(path);
        }
    }

    // delete all of dir's sub files and itself
    public static void del(String path) {

        File file = new File(path);

        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                del(f.getAbsolutePath());
                f.delete();
            }
        }

        file.delete();
    }


    /**** 递归遍历目录下所有子目录和文件 ****/
    static void iterateDir(File file) {
        File flist[] = file.listFiles();
        if (flist == null || flist.length == 0) {
            return;
        }
        for (File f : flist) {
            if (f.isDirectory()) {
                //这里将列出所有的文件夹
                System.out.println("Dir==>" + f.getAbsolutePath());
                iterateDir(f);
            } else {
                //这里将列出所有的文件
                System.out.println("file==>" + f.getAbsolutePath());
            }
        }
    }

    /**
     * fileCopy single file
     *
     * @example: D:\\a.txt -> C:\\b.txt
     */
    public static void copyFile(String oldPath, String newPath) {

        InputStream is = null;

        // FileOutputStream: will create file when it does not exist
        FileOutputStream fis = null;

        try {

            File oldFile = new File(oldPath);
            File newFile = new File(newPath);

            if (!oldFile.exists()) return;


            if (newFile.exists()) newFile.delete();
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }

            is = new FileInputStream(oldPath);
            fis = new FileOutputStream(newFile);

            byte[] buffer = new byte[1024 * 2];
            int length = 0;
            while ((length = is.read(buffer)) != -1) {
                fis.write(buffer, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (null != fis) fis.close();
                if (null != is) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * fileCopy all content of folder to another dir
     *
     * @example: D:\\aa -> D:\\bb: all content of aa is in dir bb
     */
    public static void copyFolder(String oldDir, String newDir) {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {

            // create dir if does not exist
            if (!(new File(newDir)).mkdirs()) return;

            String[] files = new File(oldDir).list();

            if (null == files) return;

            File temp;

            for (int i = 0; i < files.length; i++) {

                if (oldDir.endsWith(File.separator)) {
                    temp = new File(oldDir + files[i]);

                } else {
                    temp = new File(oldDir + File.separator + files[i]);
                }

                if (temp.isFile()) {
                    fis = new FileInputStream(temp);
                    fos = new FileOutputStream(newDir + "/" + (temp.getName()));
                    byte[] b = new byte[1024 * 2];
                    int len;
                    while ((len = fis.read(b)) != -1) {
                        fos.write(b, 0, len);
                    }
                }

                if (temp.isDirectory()) {
                    copyFolder(oldDir + "/" + files[i], newDir + "/" + files[i]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (null != fos) fos.close();
                if (null != fis) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * move single file or dirs to another place
     */
    public static void move(String oldPath, String newPath) {

        File oldFile = new File(oldPath);

        if (oldFile.isFile()) {

            copyFile(oldPath, newPath);

        } else if (oldFile.isDirectory()) {

            copyFolder(oldPath, newPath);
        }

        del(oldPath);
    }


    /**
     * get suffix name of file[not directory]
     */
    public static String suffix(String filePath) {

        File file = new File(filePath);

        if (!file.exists() || !file.isFile())
            throw new RuntimeException("function.FileTK -> suffix() --> file does not exist");


        String fileName = file.getName();

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    /**
     * get content of file[txt, java, c, json, xml...... whatever]
     */
    public static String read(String filePath) {

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) throw new RuntimeException("FileT -> read() --> file does not exist");

        String content = "";
        InputStream is = null;
        InputStreamReader isr = null;

        try {
            is = new FileInputStream(file);

            isr = new InputStreamReader(is, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content += line + "\n";
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (null != isr) isr.close();
                if (null != is) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content;
    }


    /**
     * write string to file and clear src file's content
     */
    public static void write(String filePath, String content) {
        write(filePath, content, false);
    }

    /**
     * control if clear src file's content
     */
    public static void write(String filePath, String content, boolean isAppend) {

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(filePath, isAppend));
            bw.write(content);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (null != bw) bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] bytes(String filePath) {

        byte[] buffer = null;

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void file(byte[] fileBytes, String filePath, String fileName) {

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file;

        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(fileBytes);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (null != bos) bos.close();
                if (null != fos) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存照片
     */
    public static boolean saveBitmap(Bitmap bitmap, String savePath) {

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
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos); // 向缓冲区之中压缩图片
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurBitmap(Context context, Bitmap image, float blurRadius) {
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
}
