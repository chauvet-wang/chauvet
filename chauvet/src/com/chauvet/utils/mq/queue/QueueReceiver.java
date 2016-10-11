package com.chauvet.utils.mq.queue;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/****
 * 
 * 第二种方式
 *  Queue队列方式发送点对点消息数据
 * 
 * @author WXW
 *
 */
public class QueueReceiver {

	// MQ tcp 地址
    public static final String MQ_TCP_URL = "tcp://localhost:61616";
    // 目标，在ActiveMQ管理员控制台创建 [http://localhost:8161/admin/queues.jsp]
    public static final String TOPIC_DESTINATION = "com.chauvet.mq.queue";
    
    public static void main(String[] args) {
    	QueueConnection connection = null;
        QueueSession session = null;
        try {
            // 创建链接工厂
            QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, MQ_TCP_URL);
            // 通过工厂创建一个连接
            connection = factory.createQueueConnection();
            // 启动连接
            connection.start();
            // 创建一个session会话
            session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Queue queue = session.createQueue(TOPIC_DESTINATION);
            // 创建消息制作者
            javax.jms.QueueReceiver receiver = session.createReceiver(queue);
            receiver.setMessageListener(new MessageListener() { 
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
