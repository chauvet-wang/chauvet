package com.chauvet.utils.mq.spring;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/***
 * 第四种
 * spring中集成
 * 
 * 消息发送者
 * 
 * @author WXW
 *
 */
public class Sender {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:/spring/applicationContext.xml");
	        JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
	        System.out.println("------------发送消息开始-----------");
	        jmsTemplate.send(new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	                MapMessage message = session.createMapMessage();
	                message.setString("message", "【this is chauvet's SPRING message】");
	                return message;
	            }
	        });
	        System.out.println("------------发送消息结束-----------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
