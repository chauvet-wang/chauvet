package com.chauvet.utils.mq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


/***
 * 第一种方式
 * 使用JMS方式发送接收消息
 * 
 * 生产者/消费者 模式【 生产者】
 * 
 * @author WXW
 *
 */
public class JmsSender {
	
	// MQ tcp 地址
    public static final String MQ_TCP_URL = "tcp://localhost:61616";
    // 目标，在ActiveMQ管理员控制台创建 [http://localhost:8161/admin/queues.jsp]
    public static final String TOPIC_DESTINATION = "com.chauvet.mq.jms";
    

	public static void main(String[] args) {
		Connection connection = null;
		Session session = null;
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, MQ_TCP_URL);
			connection = factory.createConnection();// 通过工厂创建一个连接
            connection.start();// 启动连接
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE); // 创建一个session会话
            Destination destination = session.createQueue(TOPIC_DESTINATION);// 创建一个消息队列
            MessageProducer producer = session.createProducer(destination);// 创建消息制作者
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);// 设置持久化模式    不持久化
            sendMessage(session, producer);//发送消息/生产消息
            session.commit();// 提交会话
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

	/***
	 * 
	 * @param session
	 * @param producer
	 * @throws Exception
	 */
	public static void sendMessage(Session session, MessageProducer producer) throws Exception {
            System.out.println("------------发送消息开始-----------");
            TextMessage text = session.createTextMessage("【this is chauvet's JMS message】");
            producer.send(text);
            System.out.println("------------发送消息结束-----------");
    }

}
