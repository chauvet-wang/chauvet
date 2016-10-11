package com.chauvet.utils.mq.spring;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/***
 * 第四种
 * spring中集成
 * 
 * 消息消费者
 * 
 * @author WXW
 *
 */
public class Receiver {
	
	
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:/spring/applicationContext.xml");  
	        JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");  
	        while(true) {  
	            Map<String, Object> map =  (Map<String, Object>) jmsTemplate.receiveAndConvert();  
	            System.out.println("收到消息：" + map.get("message"));  
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
