package com.chauvet.utils.mq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/***
 * 
 * 第一种方式
 * 使用JMS方式发送接收消息
 * 
 * 生产者/消费者 模式【 消费者】
 * 
 * @author WXW
 *
 */
public class JmsReceiver {
	
	// MQ tcp 地址
    public static final String MQ_TCP_URL = "tcp://localhost:61616";
    // 目标，在ActiveMQ管理员控制台创建 [http://localhost:8161/admin/queues.jsp]
    public static final String TOPIC_DESTINATION = "com.chauvet.mq.jms";
    
    
    public static void main(String[] args) {
    	Connection connection = null;
        Session session = null;
        try {
            // 创建链接工厂
            ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, MQ_TCP_URL);
            // 通过工厂创建一个连接
            connection = factory.createConnection();
            // 启动连接
            connection.start();
            // 创建一个session会话
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(TOPIC_DESTINATION);
            // 创建消息制作者
            MessageConsumer consumer = session.createConsumer(destination);
            
            while (true) {
                // 接收数据的时间（等待） 100 ms
                Message message = consumer.receive(1000 * 100);
                
                TextMessage text = (TextMessage) message;
                if (text != null) {
                    System.out.println("接收：" + text.getText());
                } else {
                    break;
                }
            }
            
            // 提交会话
            session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 关闭释放资源
            if (session != null) {
            	try {
            		session.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
            }
            if (connection != null) {
                try {
                	connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
            }
		}
	}

}
