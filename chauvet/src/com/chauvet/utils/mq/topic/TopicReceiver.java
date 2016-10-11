package com.chauvet.utils.mq.topic;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/***
 * 
 * 第三种方式 
 * Topic主题发布和订阅消息
 *  该模式下  订阅者必须一直开启着才可以正常使用，否则发布的消息接收不到
 * 
 * @author WXW
 *
 */
public class TopicReceiver {
	
	// MQ tcp 地址
    public static final String MQ_TCP_URL = "tcp://localhost:61616";
    // 目标，在ActiveMQ管理员控制台创建 [http://localhost:8161/admin/queues.jsp]
    public static final String TOPIC_DESTINATION = "com.chauvet.mq.topic";

    
    public static void main(String[] args) {
		
    	TopicConnection connection = null;
        TopicSession session = null;
        try {
            // 创建链接工厂
            TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, MQ_TCP_URL);
            // 通过工厂创建一个连接
            connection = factory.createTopicConnection();
            // 启动连接
            connection.start();
            // 创建一个session会话
            session = connection.createTopicSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Topic topic = session.createTopic(TOPIC_DESTINATION);
            // 创建消息制作者
            TopicSubscriber subscriber = session.createSubscriber(topic);
            
            subscriber.setMessageListener(new MessageListener() { 
                public void onMessage(Message msg) { 
                    if (msg != null) {
                        MapMessage map = (MapMessage) msg;
                        try {
                            System.out.println(map.getLong("time") + "接收#" + map.getString("text"));
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                } 
            }); 
            // 休眠100ms再关闭
            Thread.sleep(1000 * 100); 
            // 提交会话
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭释放资源
            if (session != null) {
                try {
					session.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
            }
            if (connection != null) {
                try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
            }
        }
    	
	}
}
