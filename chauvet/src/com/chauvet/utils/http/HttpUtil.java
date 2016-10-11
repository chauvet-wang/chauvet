package com.chauvet.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * Http
 * @author wxw
 *
 */
public class HttpUtil {
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        StringBuffer result = new StringBuffer();
        BufferedReader in = null;
        try {
            String urlStr = url + "?" + param;
            URL realUrl = new URL(urlStr);
            URLConnection connection = realUrl.openConnection(); // 打开连接
            // 设置‘请求头’信息
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect(); // 建立连接
            Map<String, List<String>> map = connection.getHeaderFields();// 获取  ‘ 响应头’  信息
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));// 定义 BufferedReader输入流来读取URL的响应
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {//关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }
    
   /* public static String httpGet(String httpUrl) {  
        String result = "";  
        DefaultHttpClient httpclient = new DefaultHttpClient();// 创建http客户端  
        HttpGet httpget = new HttpGet(httpUrl);  
        HttpResponse response = null;  
        HttpParams params = httpclient.getParams(); // 计算网络超时用  
        HttpConnectionParams.setConnectionTimeout(params, 15 * 1000);  
        HttpConnectionParams.setSoTimeout(params, 20 * 1000);  
          
        try {  
            response = httpclient.execute(httpget);  
            HttpEntity entity = response.getEntity();// 得到http的内容  
            response.getStatusLine().getStatusCode();// 得到http的状态返回值  
            result = EntityUtils.toString(response.getEntity());// 得到具体的返回值，一般是xml文件  
            entity.consumeContent();// 如果entity不为空，则释放内存空间  
            httpclient.getCookieStore();// 得到cookis  
            httpclient.getConnectionManager().shutdown();// 关闭http客户端  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  */
    

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();// 打开连接
            // 设置‘请求头’信息
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());// 获取URLConnection对象对应的输出流
            out.print(param); // 发送请求参数
            out.flush();// flush输出流的缓冲
            
            Map<String, List<String>> map = conn.getHeaderFields();// 获取  ‘ 响应头’  信息
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));// 定义BufferedReader输入流来读取URL的响应
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        } finally{//关闭输出流、输入流
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
    
   /* public static String httpPost(String httpUrl, String data) {  
        String result = "";  
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        HttpPost httpPost = new HttpPost(httpUrl);  
        // httpclient.setCookieStore(DataDefine.mCookieStore);  
          
        HttpParams params = httpclient.getParams(); // 计算网络超时用  
        HttpConnectionParams.setConnectionTimeout(params, 15 * 1000);  
        HttpConnectionParams.setSoTimeout(params, 20 * 1000);  
        httpPost.setHeader("Content-Type", "text/xml");  
        StringEntity httpPostEntity;  
          
        try {  
            httpPostEntity = new StringEntity(data, "UTF-8");  
            httpPost.setEntity(httpPostEntity);  
            HttpResponse response = httpclient.execute(httpPost);  
            HttpEntity entity = response.getEntity();// 得到http的内容  
            response.getStatusLine().getStatusCode();// 得到http的状态返回值  
            result = EntityUtils.toString(response.getEntity());// 得到具体的返回值，一般是xml文件  
            entity.consumeContent();// 如果entity不为空，则释放内存空间  
            httpclient.getCookieStore();// 得到cookis  
            httpclient.getConnectionManager().shutdown();// 关闭http客户端  
        } catch (Exception e) {  
            e.printStackTrace();  
        }// base64是经过编码的字符串，可以理解为字符串  
            // StringEntity httpPostEntity = new StringEntity("UTF-8");  
        return result;  
    }  */
    
    /**
     * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
     * @param map
     * @return
     */
    public static String mapToGet(Map<? extends Object,? extends Object> map){
        String result = "" ;
        if(map == null || map.size() ==0){
            return result ;
        }
        Set<? extends Object> keys = map.keySet();
        for (Object key : keys ) {
            result += ((String)key + "=" + (String)map.get(key) + "&");
        }
        
        return (null == result || "".equals(result.trim())) ? result : result.substring(0,result.length() - 1);
    }
    
    
    /**
     * 字符串转urlcode
     * @param value
     * @return
     */
    public static String strToUrlcode(String value){
        try {
            value = URLEncoder.encode(value,"utf-8");
            return value ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }    
    }
    /**
     * urlcode转字符串
     * @param value
     * @return
     */
    public static String urlcodeToStr(String value){
        try {
            value = URLDecoder.decode(value,"utf-8");
            return value ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }  
    }
    
    
    
    public static void main(String[] args) {
    	 //发送 GET 请求
//        String s=HttpUtil.sendGet("http://www.baidu.com", "");
//        System.out.println(s);
        
//        //发送 POST 请求
//        String sr=HttpUtil.sendPost("http://www.baidu.com", "");
//        System.out.println(sr);
    	
	}

}
