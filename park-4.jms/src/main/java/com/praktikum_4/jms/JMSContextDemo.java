package com.praktikum_4.jms;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class JMSContextDemo {

	public static void main(String[] args) throws NamingException {
		

		InitialContext context =  new InitialContext(); 
		Queue queue = (Queue) context.lookup("queue/myQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {
			jmsContext.createProducer().send(queue, "Arise Awake and stop not till the goal is reached");
			
			String messageR = jmsContext.createConsumer(queue).receiveBody(String.class);
			
			System.out.println(messageR);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

}
