package com.getLunch.backend.utils;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.NamingException;

import com.getLunch.backend.ejb.QueueController;
import com.getLunch.ui.Cliente;

public class Listener {
	private static final String QUEUE_LOOKUP 		= "queue/MyQueue";
	private static final String TOPIC_LOOKUP 		= "topic/MyTopic";
    private static final String CONNECTION_FACTORY 	= "ConnectionFactory";
    
    
    public static void addQueueReceiver(QueueController qc){
    	
    	Context context;
		try {
									context 	= ClientUtility.getInitialContextForClient();
			QueueConnectionFactory 	factory 	= (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY);
	        QueueConnection 		connection 	= factory.createQueueConnection();
	        QueueSession 			session 	= connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
	        Queue 					queue 		= (Queue) context.lookup(QUEUE_LOOKUP);
	        
	        QueueReceiver receiver = session.createReceiver(queue);
	        receiver.setMessageListener(qc);
	        connection.start();
	        
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	
    }
    
    public static void addTopicListener(Cliente cli){
    	
    	Context context;
		try {
			
									context 		= ClientUtility.getInitialContextForClient();
			TopicConnectionFactory 	factory 		= (TopicConnectionFactory) context.lookup(CONNECTION_FACTORY);
	        TopicConnection 		connection 		= factory.createTopicConnection();
	        TopicSession 			session 		= connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
	        Topic 					topic 			= (Topic) context.lookup(TOPIC_LOOKUP);
	        TopicSubscriber 		topicSubscriber = session.createSubscriber(topic);
	        
	        topicSubscriber.setMessageListener(cli);
	        connection.start();
	        
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	
    }
}
