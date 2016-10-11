package com.chauvet.utils.mq.topic;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/***
 * 
 * 第三种方式 
 * Topic 主题发布 和 订阅消息
 * 该模式下  订阅者必须一直开启着才可以正常使用，否则发布的消息接收不到
 * 
 * @author WXW
 *
 */
public class TopicSender {
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
            // 创建消息发送者
            TopicPublisher publisher = session.createPublisher(topic);
            // 设置持久化模式
            publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            sendMessage(session, publisher);
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
    
    public static void sendMessage(TopicSession session, TopicPublisher publisher) throws Exception {
    	System.out.println("------------发送消息开始-----------");
    	MapMessage map = session.createMapMessage();
        map.setString("text", "【this is chauvet's TOPIC message】");
        map.setLong("time", System.currentTimeMillis());
        publisher.send(map);
        System.out.println("------------发送消息结束-----------");
    }
}
