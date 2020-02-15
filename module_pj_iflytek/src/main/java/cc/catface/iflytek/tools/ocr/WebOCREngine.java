package cc.catface.iflytek.tools.ocr;
/**
 * 印刷文字识别WebAPI接口调用示例接口文档(必看)：https://doc.xfyun.cn/rest_api/%E5%8D%B0%E5%88%B7%E6%96%87%E5%AD%97%E8%AF%86%E5%88%AB.html
 * 上传图片base64编码后进行urlencode要求base64编码和urlencode后大小不超过4M最短边至少15px，最长边最大4096px支持jpg/png/bmp格式
 * (Very Important)创建完webapi应用添加合成服务之后一定要设置ip白名单，找到控制台--我的应用--设置ip白名单，如何设置参考：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=41891
 * 错误码链接：https://www.xfyun.cn/document/error-code (code返回错误码时必看)
 *
 * @author iflytek
 */

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class WebOCREngine {
    // OCR webapi 接口地址
    private static final String WEBOCR_URL = "https://webapi.xfyun.cn/v1/service/v1/ocr/general";
    // 应用ID (必须为webapi类型应用，并印刷文字识别服务，参考帖子如何创建一个webapi应用：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=36481)
    private static final String APPID = "5c88a39f";
    // 接口密钥(webapi类型应用开通印刷文字识别服务后，控制台--我的应用---印刷文字识别---服务的apikey)
    private static final String API_KEY = "cd91c09cfdc8b3aa63bc998a2d824d19";
    // 是否返回位置信息
    private static final String LOCATION = "false";
    // 语种(可选值：en（英文），cn|en（中文或中英混合)
    private static final String LANGUAGE = "cn|en";

    public static String request(byte[] bytes) throws IOException {
        Map<String, String> header = buildHttpHeader();
        String imageBase64 = new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
        return post(WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
    }

    /**
     * 组装http请求头
     */
    private static Map<String, String> buildHttpHeader() {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"location\":\"" + LOCATION + "\",\"language\":\"" + LANGUAGE + "\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes(StandardCharsets.UTF_8)));
        String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", APPID);
        return header;
    }


    public static String post(String url, Map<String, String> header, String body) {
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            for (String key : header.keySet()) {
                httpURLConnection.setRequestProperty(key, header.get(key));
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            out.print(body);
            out.flush();
            if (HttpURLConnection.HTTP_OK != httpURLConnection.getResponseCode()) {
                System.out.println("Http 请求失败，状态码：" + httpURLConnection.getResponseCode());
                return null;
            }
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }


    // OCR结果解析示例
    /*result = "";


    OcrResult ocrResult = new Gson().fromJson(json, OcrResult.class);
    List<OcrResult.Data.Block> blocks = ocrResult.getData().getBlock();
    for (OcrResult.Data.Block block : blocks) {
        List<OcrResult.Data.Block.Line> lines = block.getLine();
        for (OcrResult.Data.Block.Line line : lines) {
            List<OcrResult.Data.Block.Line.Word> words = line.getWord();
            for (OcrResult.Data.Block.Line.Word word : words) {
                result += word.getContent();
            }
        }
    }*/
}