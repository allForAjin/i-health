package com.health.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName HttpClientUtil.java
 * @Description TODO
 * @createTime 2021-11-30 17:48:28
 */
public class HttpClientUtil {
    /**
     * 模拟客户端发送 post请求
     * 参数形式为 key-value
     *
     * @param url     要请求的 url
     * @param headers 请求头设置
     * @param params  请求参数
     * @return java.lang.String 返回 json格式数据
     * @author lmk
     * @Date 2021/11/30 19:39
     */
    public static String doPost(String url, Map<String, String> headers, Map params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //实例化post请求
        HttpPost httpPost = new HttpPost(url);


        //请求头设置
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpPost.setHeader(key, headers.get(key));
            }
        }
        //键值对结点类型list，用于在发送post请求时存放参数
        List<NameValuePair> nameValuePairList = new ArrayList<>();

        Iterator iterator = params.keySet().iterator();

        while (iterator.hasNext()) {
            String name = (String) iterator.next();
            String value = String.valueOf(params.get(name));
            System.out.println("params:" + "key=" + name + ",value=" + value);
              nameValuePairList.add(new BasicNameValuePair(name, value));
        }
        CloseableHttpResponse response = null;
        try {
            //消息体内容为 UrlEncode形式(key1=value1&key2=value2...)
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            httpPost.setEntity(entity);

            //发送请求
            response = httpClient.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String result = EntityUtils.toString(responseEntity);
                return result;
            } else {
                System.out.println("错误！状态码：" + code);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭请求
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 模拟客户端发送 post请求
     * json格式参数
     *
     * @param url  要请求的 url
     * @param data json格式数据
     * @return java.lang.String 服务端返回的数据
     * @author lmk
     * @Date 2021/11/30 19:46
     */
    public static String doPost(String url, String data) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        /*请求头*/
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");

        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(data, charSet);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String result = EntityUtils.toString(responseEntity);
                return result;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
