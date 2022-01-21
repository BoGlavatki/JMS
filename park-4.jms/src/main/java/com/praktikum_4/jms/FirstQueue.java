package com.praktikum_4.jms;

import javax.jms.Queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FirstQueue {

	public static void main(String[] args) {

		InitialContext intInitialContext = null;
		Connection connection = null;

		try {
			intInitialContext = new InitialContext();
			ConnectionFactory cFactory = (ConnectionFactory) intInitialContext.lookup("ConnectionFactory");
			
			 connection = cFactory.createConnection();
			 Session session = connection.createSession();
			 
			Queue queue = (Queue) intInitialContext.lookup("queue/myQueue");
			MessageProducer producer = session.createProducer(queue);
			TextMessage message = session.createTextMessage("I am the creator of my distiny"); 
			producer.send(message);
		
			
			MessageConsumer consumer = session.createConsumer(queue);
			connection.start();
			TextMessage messageRecieved = (TextMessage) consumer.receive(5000);
			System.out.println("Message recieved: "+messageRecieved.getText());
		
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(intInitialContext != null) {
				try {
					intInitialContext.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

	}

}
