package com.praktikum_4.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class FirstTopic {

	public static void main(String[] args) throws Exception {
		 
		
		
		InitialContext initialContext = new InitialContext();
		Topic topic = (Topic) initialContext.lookup("topic/myTopic");
		
		ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
		Connection connection  = cf.createConnection();
		
		Session session = connection.createSession();
		MessageProducer producer = session.createProducer(topic);
		
		MessageConsumer consumer1 = session.createConsumer(topic);
		MessageConsumer consumer2 = session.createConsumer(topic);
		
		
		TextMessage message = session.createTextMessage("All the power is with in me. I can do anything and everything");
		producer.send(message);
		
		connection.start();
		
		TextMessage message2 = (TextMessage) consumer1.receive();
		TextMessage message3 = (TextMessage) consumer2.receive();
		
		System.out.println("Messege recived from message 1:  " + message2.getText());
		System.out.println("Messege recived from message 2:  " + message3.getText());
		
		connection.close();
		initialContext.close();
	}	
}
