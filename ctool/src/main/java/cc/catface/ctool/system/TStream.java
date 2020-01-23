package cc.catface.ctool.system;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TStream {


    public static String stream2Str(InputStream in) throws IOException {
        if (in != null) {
            ByteArrayOutputStream out = null;
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            String result = out.toString();
            in.close();
            out.close();

            return result;
        }

        return "";
    }

}
