package cc.catface.ctool.java.encode;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TMD5 {

    public static String str2MD5(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(str.getBytes());

            StringBuffer sb = new StringBuffer();
            for (byte b : result) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);

                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }

                sb.append(hexString);
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String file2MD5(String path) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream in = new FileInputStream(path);

            int len = 0;
            byte[] buffer = new byte[1024];

            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }

            byte[] result = digest.digest();

            StringBuffer sb = new StringBuffer();
            for (byte b : result) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);

                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }

                sb.append(hexString);
            }

            System.out.println(sb.toString());
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
