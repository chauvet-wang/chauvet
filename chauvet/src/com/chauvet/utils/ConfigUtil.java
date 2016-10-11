package com.chauvet.utils;

import java.util.ResourceBundle;

/***
 * 获取config下的配置文件
 * @author WXW
 *
 */
public class ConfigUtil {
 	private static ResourceBundle bundle = ResourceBundle.getBundle("config");
    private static ConfigUtil configUtil = null;
    private ConfigUtil(){
        
    }
    public static ConfigUtil getInstance(){
        if(configUtil==null){
            configUtil = new ConfigUtil();
        }
        return configUtil;
    }

    public static void main(String[] args) {
		System.out.println(bundle.getString("memcache.server"));
	}
    
    public String getMemcacheServer(){
    	return bundle.getString("memcache.server");
    }
    
    public String getMailHost(){
    	return bundle.getString("mail.host");
    }
    
    public String getMailUser(){
    	return bundle.getString("mail.user");
    }
    
    public String getMailPwd(){
    	return bundle.getString("mail.pwd");
    }
    
    public String getMailFrom() {
    	return bundle.getString("mail.from");
    }
    
    public String getMailTo() {
    	return bundle.getString("mail.to");
    }
    
}
