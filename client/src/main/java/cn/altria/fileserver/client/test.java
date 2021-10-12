package cn.altria.fileserver.client;

import  cn.altria.fileserver.client.HttpURLConnectionUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.junit.Test;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;

public class test {

    //测试上传图片
    @Test
    public  void testUpadate() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:8080/update");// 请求地址
        MultipartEntityBuilder meb = MultipartEntityBuilder.create();
        ContentType strContent = ContentType.create("application/zip", Charset.forName("UTF-8"));
        meb.addBinaryBody("file", new File("./files/icon.jpg"));// 文件路径
        meb.addTextBody("fileName", "icon.jpg", strContent);// 文件名
        HttpEntity httpEntity = meb.build();
        httpPost.setEntity(httpEntity);
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            HttpResponse httpResponse = httpClient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String rspMsg = URLDecoder.decode(sb.toString(),"UTF-8");
            System.err.println(rspMsg); // {"resp_desc":"SUCCESS","resp_code":"0000"}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试下载
    @Test
    public void testSearch(){
        String msg = HttpURLConnectionUtil.doGet("http://localhost:8080/search?UUid=9e6ea9ae-23c8-421c-b217-e986354e535e");
        System.out.println(msg);
    }

    //测试查询
    @Test
    public void testDownLoad(){
        String msg = HttpURLConnectionUtil.doGet("http://localhost:8080/download?UUid=9e6ea9ae-23c8-421c-b217-e986354e535e");
        System.out.println(msg);
    }

}
