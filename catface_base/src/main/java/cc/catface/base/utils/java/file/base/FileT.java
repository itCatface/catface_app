package cc.catface.base.utils.java.file.base;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;

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
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import androidx.annotation.RequiresApi;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class FileT {


    /*** 判断文件/目录是否存在 ***/
    public static boolean isFileInvalid(String filePath) {
        File file = new File(filePath);
        return null == file || !file.exists() || !file.isFile();
    }


    /*** created file or dir ***/
    // created dir and keep src dir
    public static boolean create(String path) {
        return create(path, false, false);
    }

    // created dir/file and keep src dir/file
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

        // FileOutputStream: will created file when it does not exist
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

            // created dir if does not exist
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

        if (!file.exists() || !file.isFile())
            throw new RuntimeException("FileT -> read() --> file does not exist");

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

    /* 获取当前文件或者文件夹大小 */
    public static long getTotalSizeOfFilesInDir(final File file) {
        if (file.isFile()) return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null) for (final File child : children)
            total += getTotalSizeOfFilesInDir(child);
        return total;
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


    /* 读取txt文本内容 */
    public static String getTxtContent(File file) {
        String content = "";
        if (!file.exists() || !file.isFile()) return content;
        InputStream is = null;
        if (file.getName().endsWith(".txt")) {
            try {
                is = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content += line + "\n";
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content;
    }


    /* 保存字符串内容 */
    public static void saveString2File(String filePath, String content) {

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(content);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (bw != null) bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /* 流转成字符串 */
    public static String stream2String(InputStream is) {
        String result = "";
        if (null == is || "".equals(is.toString())) return result;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();

            result = baos.toString();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                is.close();
                if (null != baos) baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    /**
     * 读取res/raw中的json文件
     */
    public static String get(Context context, int id) {
        InputStream stream = context.getResources().openRawResource(id);
        return read(stream);
    }

    public static String read(InputStream stream) {
        return read(stream, "utf-8");
    }

    public static String read(InputStream is, String encode) {
        if (is != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, encode));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                return sb.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    /*------*/
    //@Android计算文件的MD5和SHA1
    //项目需要，计算文件的MD5和SHA1值，找了一些代码效率比较低，有的还晦涩难懂，这里给出测试后通过，速度也相对较快的代码。
    //从StackOverflow上找到的，为了提高速度，可以将buffer开的大一点，还有的使用JNI编写的，可以参考。
    /**
     * Get the md5 value of the filepath specified file
     *
     * @param filePath
     *            The filepath of the file
     * @return The md5 value
     */
    public static String fileToMD5(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath); // Create an
            // FileInputStream
            // instance according
            // to the filepath
            byte[] buffer = new byte[1024]; // The buffer to read the file
            MessageDigest digest = MessageDigest.getInstance("MD5"); // Get a
            // MD5
            // instance
            int numRead = 0; // Record how many bytes have been read
            while (numRead != -1) {
                numRead = inputStream.read(buffer);
                if (numRead > 0)
                    digest.update(buffer, 0, numRead); // Update the digest
            }
            byte[] md5Bytes = digest.digest(); // Complete the hash computing
            return convertHashToString(md5Bytes); // Call the function to
            // convert to hex digits
        } catch (Exception e) {
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close(); // Close the InputStream
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Get the sha1 value of the filepath specified file
     *
     * @param filePath
     *            The filepath of the file
     * @return The sha1 value
     */
    public static String fileToSHA1(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath); // Create an
            // FileInputStream
            // instance according
            // to the filepath
            byte[] buffer = new byte[1024]; // The buffer to read the file
            MessageDigest digest = MessageDigest.getInstance("SHA-1"); // Get a
            // SHA-1
            // instance
            int numRead = 0; // Record how many bytes have been read
            while (numRead != -1) {
                numRead = inputStream.read(buffer);
                if (numRead > 0)
                    digest.update(buffer, 0, numRead); // Update the digest
            }
            byte[] sha1Bytes = digest.digest(); // Complete the hash computing
            return convertHashToString(sha1Bytes); // Call the function to
            // convert to hex digits
        } catch (Exception e) {
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close(); // Close the InputStream
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Convert the hash bytes to hex digits string
     *
     * @param hashBytes
     * @return The converted hex digits string
     */
    private static String convertHashToString(byte[] hashBytes) {
        String returnVal = "";
        for (int i = 0; i < hashBytes.length; i++) {
            returnVal += Integer.toString((hashBytes[i] & 0xff) + 0x100, 16)
                    .substring(1);
        }
        return returnVal.toLowerCase();
    }
}
