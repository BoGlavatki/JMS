package com.praktikum_4.jms.messagestructure;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessagePriority {

	public static void main(String[] args) throws NamingException {
		
		
		InitialContext context =  new InitialContext(); 
		Queue queue = (Queue) context.lookup("queue/myQueue");
		
		try(
				ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				
			JMSContext jmsContext = cf.createContext()) {
			
			
			
			JMSProducer producer = jmsContext.createProducer();
			
			String[] messages = new String[3];
			messages[0] = "One";
			messages[1] = "Two";
			messages[2] = "Three";
			
			
			
			
			
			
			
			producer.setPriority(3);
			producer.send(queue, messages[0]);
			
			producer.setPriority(1);
			producer.send(queue, messages[1]);
			
			producer.setPriority(9);
			producer.send(queue, messages[2]);
			
			
			
			JMSConsumer consumer = jmsContext.createConsumer(queue);
			
			for(int i = 0; i< 3; i++) {
				System.out.println(consumer.receiveBody(String.class));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		


	}

}
