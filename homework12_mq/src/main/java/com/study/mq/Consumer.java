package com.study.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author hong.zheng
 * @Date: 12/11/21 9:57 AM
 **/
public class Consumer {
    public static void main(String[] args)
    {
        // 连接工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        // 连接实例
        Connection connection = null;

        try {
            connection = factory.createConnection();

            connection.start();

            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            // Destination destination = session.createQueue("testQueue");
            Destination destination = session.createTopic("firsTopic");

            // 创建消息消费者
            MessageConsumer messageConsumer = session.createConsumer(destination);

            MessageListener listener = new MessageListener() {

                @Override
                public void onMessage(Message message) {
                    try {
                        // 打印所有的消息内容
                        // Thread.sleep();
                        System.out.println( " => receive from:" + message);
                        // message.acknowledge(); // 前面所有未被确认的消息全部都确认。

                    } catch (Exception e) {
                        e.printStackTrace(); // 不要吞任何这里的异常，
                    }
                }
            };
            // 绑定消息监听器
            messageConsumer.setMessageListener(listener);
            System.in.read();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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