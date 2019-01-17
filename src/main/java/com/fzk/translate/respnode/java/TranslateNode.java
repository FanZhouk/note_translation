package com.fzk.translate.respnode.java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fzk.translate.respnode.ProcessContext;
import com.fzk.translate.respnode.RespNode;
import com.fzk.translate.util.HttpUtils;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 翻译节点
 * <p>
 * 使用有道云API，文档地址：https://ai.youdao.com/docs/doc-trans-api.s#p01
 */
public class TranslateNode extends RespNode {

    private static final String appKey = "7fbd95b957846360";
    private static final String privateKey = "5OCQrVnw9bmtYVfoG54EwNbGaSM6agU2";

    @Override
    public List<String> process(ProcessContext context) throws Exception {
        List<String> result = new ArrayList<>(context.result.size());

        for (String section : context.result) {
            String salt = String.valueOf(System.currentTimeMillis());

            Map map = new HashMap();
            map.put("q", section);
            map.put("from", "EN");
            map.put("to", "zh-CHS");
            map.put("appKey", appKey);
            map.put("salt", salt);
            map.put("sign", md5(appKey + section + salt + privateKey));

            String res = HttpUtils.doGet(getUrlWithQueryString("http://openapi.youdao.com/api", map));
            JSONObject jo = JSON.parseObject(res);
            Object translation = jo.get("translation");
            System.out.println(translation);
            if (translation != null)
                result.add(((JSONArray)translation).get(0).toString());
        }
        context.result = result;
        return go(context);
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        String query = "你好";
        String salt = String.valueOf(System.currentTimeMillis());
        String from = "zh-CHS";
        String to = "EN";
        String sign = md5(appKey + query + salt + privateKey);
        System.out.println(sign);
        Map params = new HashMap();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", appKey);
        System.out.println(HttpUtils.doGet(getUrlWithQueryString("http://openapi.youdao.com/api", params)));
    }

    /**
     * 生成32位MD5摘要
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = string.getBytes("utf-8");
            /** 获得MD5摘要算法的 MessageDigest 对象 */
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            /** 使用指定的字节更新摘要 */
            mdInst.update(btInput);
            /** 获得密文 */
            byte[] md = mdInst.digest();
            /** 把密文转换成十六进制的字符串形式 */
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据api地址和参数生成请求URL
     *
     * @param url
     * @param params
     * @return
     */
    public static String getUrlWithQueryString(String url, Map params) {
        if (params == null) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (Object key : params.keySet()) {
            String value = (String) params.get(key);
            if (value == null) { // 过滤空的key
                continue;
            }

            if (i != 0) {
                builder.append('&');
            }

            builder.append(key);
            builder.append('=');
            builder.append(encode(value));

            i++;
        }

        return builder.toString();
    }

    /**
     * 进行URL编码
     *
     * @param input
     * @return
     */
    public static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return input;
    }
}
