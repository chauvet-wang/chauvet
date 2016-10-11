package com.chauvet.utils.mail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.chauvet.utils.ConfigUtil;

public class MailSendUtil {

	private static MailSendUtil mailSendUtil = null;
	
	public static MailSendUtil getInstance() {
		if(null == mailSendUtil) {
			mailSendUtil = new MailSendUtil();
		}
		return mailSendUtil;
	}
	
	public void sendEmail(String from, String to, String subJect, String content) {
		SimpleMailMessage mailMessage  =   new SimpleMailMessage(); 
		// 设置收件人，寄件人 用数组发送多个邮件
		mailMessage.setTo( to ); 
		mailMessage.setFrom( from ); 
		mailMessage.setSubject( subJect ); 
		mailMessage.setText( content );
		sendEmail(mailMessage);
	}
	
	private void sendEmail(SimpleMailMessage mailMessage) {
		JavaMailSenderImpl senderImpl  =   new  JavaMailSenderImpl(); 
		ConfigUtil configUtil = ConfigUtil.getInstance();
		// 设定mail server
		senderImpl.setHost(configUtil.getMailHost() );
		senderImpl.setUsername( configUtil.getMailUser() ) ;  //  根据自己的情况,设置username
		senderImpl.setPassword( configUtil.getMailPwd() ) ;  //  根据自己的情况, 设置password
		Properties prop  =   new  Properties() ;
		prop.put( "mail.smtp.auth" ,  "true" ) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
		prop.put( "mail.smtp.timeout" ,  "25000" ) ; 
		senderImpl.setJavaMailProperties(prop);  
		senderImpl.send(mailMessage);
	}
	
	public static void main(String[] args) {
		mailSendUtil = MailSendUtil.getInstance();
		mailSendUtil.sendEmail(ConfigUtil.getInstance().getMailFrom(), ConfigUtil.getInstance().getMailTo(), "标题", "内容");
	}
}