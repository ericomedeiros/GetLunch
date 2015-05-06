package com.getLunch.backend.utils;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.NamingException;

import com.getLunch.backend.message.Restaurante;

public class Sender {
	
	private static final String QUEUE_LOOKUP 	   = "queue/MyQueue";
	private static final String TOPIC_LOOKUP 	   = "topic/MyTopic";
    private static final String CONNECTION_FACTORY = "ConnectionFactory";
	
	public static void sendMessageToQueue() {
        try {
            Context 				context 	= ClientUtility.getInitialContextForClient();
            QueueConnectionFactory 	factory 	= (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY);
            QueueConnection 		connection 	= factory.createQueueConnection();
            QueueSession 			session 	= connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            Queue 					queue 		= (Queue) context.lookup(QUEUE_LOOKUP);
            QueueSender 			sender 		= session.createSender(queue);
            TextMessage 			message 	= session.createTextMessage();
            
            message.setText("Welcome to EJB3");
            sender.send(message);
            session.close();
            
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
 
    public static void sendObjectMessageToQueue(Restaurante re) {
        try {
            Context 				context 	= ClientUtility.getInitialContextForClient();
            QueueConnectionFactory 	factory 	= (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY);
            QueueConnection 		connection 	= factory.createQueueConnection();
            QueueSession 			session 	= connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            Queue 					queue 		= (Queue) context.lookup(QUEUE_LOOKUP);
            QueueSender 			sender 		= session.createSender(queue);
            ObjectMessage 			message 	= session.createObjectMessage();
            
            message.setObject(re);
            sender.send(message);
            session.close();
            connection.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    public static void sendObjectMessageToTopic(Restaurante re) {
        try {
            Context 				context 		= ClientUtility.getInitialContextForClient();
            TopicConnectionFactory 	factory 		= (TopicConnectionFactory) context.lookup(CONNECTION_FACTORY);
            TopicConnection 		connection 		= factory.createTopicConnection();
            TopicSession 			session 		= connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
            Topic 					topic 			= (Topic) context.lookup(TOPIC_LOOKUP);
            TopicPublisher 			topicPublisher 	= session.createPublisher(topic);
            ObjectMessage 			message 		= session.createObjectMessage();
            
            connection.start();
            message.setObject(re);
            topicPublisher.send(message);
            topicPublisher.publish(message);
            topicPublisher.close();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
