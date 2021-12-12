package com.study.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author hong.zheng
 * @Date: 12/11/21 9:58 AM
 **/
public class Producer {


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
                // 创建队列（返回一个消息目的地）
                //Destination destination = session.createQueue("testQueue");
                // 创建一个主题
                Destination destination = session.createTopic("firsTopic");

                // 创建消息生产者
                MessageProducer messageProducer = session.createProducer(destination);

                for (int i = 0; i <2 ; i++) {
                    // 创建TextMessage消息实体
                    TextMessage message = session.createTextMessage("hello activemq "+i);
                    messageProducer.send(message);
                }

            } catch (JMSException e) {
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