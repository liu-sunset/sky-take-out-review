package utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {
    private  static int TIMEOUT_MSEC = 5*1000;

    public static String doPost(String URL, Map<String,String> map) throws IOException {

        //常见一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response =null;
        String resultString = "";

        //创建一个post请求
        HttpPost post = new HttpPost(URL);

        //将map封装成一个namevaluepair
        if(map!=null){
            List<NameValuePair> list = new ArrayList<>();
            for(Map.Entry<String,String> mapEntry : map.entrySet()){
                list.add(new BasicNameValuePair(mapEntry.getKey(),mapEntry.getValue()));
            }

            //模拟表单
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);

            post.setEntity(entity);
        }
        post.setConfig(buildRequestConfig());

        response = httpClient.execute(post);
        resultString = EntityUtils.toString(response.getEntity());
        response.close();
        httpClient.close();
        return resultString;
    }

    public static RequestConfig buildRequestConfig(){
        return RequestConfig.custom()
                .setConnectTimeout(TIMEOUT_MSEC)
                .setConnectionRequestTimeout(TIMEOUT_MSEC)
                .setSocketTimeout(TIMEOUT_MSEC).build();
    }
}
