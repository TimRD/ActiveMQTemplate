package sender;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.QueueConnectionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySender {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");
			
			QueueConnection connection = factory.createQueueConnection();
	        
	           QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
	           
	           connection.start();
	           
	           QueueSender sender = session.createSender(queue);
	           
	           Message message = session.createTextMessage("this OIS MY TEST");
	           
	           sender.send(message);
	           
	           session.close();
	           
	           connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
