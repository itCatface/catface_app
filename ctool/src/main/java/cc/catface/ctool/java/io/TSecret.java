package cc.catface.ctool.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TSecret {

    /** 异或加密 */
    public static final int XOR_CONST = 0X99;   // key's of xor encrypt

    public static String encrypt(String str) {
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = (char) (charArr[i] ^ 0X99);
        }

        return new String(charArr);
    }


    public static void encrypt(String srcPath, String desPath) {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            File srcFile = new File(srcPath);
            File desFile = new File(desPath);

            if (!srcFile.exists()) return;

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(desFile);

            int read;
            while ((read = fis.read()) > -1) {
                fos.write(read ^ XOR_CONST);
            }

        } catch (IOException e) {
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

}
