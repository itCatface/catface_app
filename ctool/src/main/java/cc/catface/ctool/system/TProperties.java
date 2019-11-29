package cc.catface.ctool.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TProperties {

    public static String getValue(String filePath, String key) {
        File file;
        InputStream is = null;
        Properties properties;

        try {
            file = new File(filePath);
            if (!file.exists()) {
                return null;
            }

            is = new FileInputStream(file);

            properties = new Properties();
            properties.load(is);

            return new String(properties.getProperty(key).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8); // 处理中文乱码

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            try {
                if (null != is) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, String> getAllKV(String filePath) {
        File file;
        InputStreamReader is = null;
        Properties properties;

        try {
            file = new File(filePath);
            if (!file.exists()) {
                return null;
            }

            is = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            properties = new Properties();
            properties.load(is);

            Map<String, String> map = new HashMap<>();
            // way1: use Enumeration to visit the properties
            /*Enumeration<?> enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                String value = (String) enumeration.nextElement();
                System.out.println(value + "=" + properties.getProperty(value));
            }

            // way2: use KeySet to visit the properties
            Set<Object> keyset = properties.keySet();
            Iterator<Object> itr = keyset.iterator();
            while (itr.hasNext()) {
                String key = itr.next().toString();
                System.out.println(key + "=" + properties.getProperty(key));
            }*/

            // way3: use stringPropertyNames to visit the properties
            Set<String> keys = properties.stringPropertyNames();
            for (String key : keys) {
                map.put(key, properties.getProperty(key));
            }
            return map;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            try {
                if (null != is) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
