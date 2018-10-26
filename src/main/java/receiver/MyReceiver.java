package receiver;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyReceiver {

	public static void main(String[] args) {
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");
			
			/* Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			 * 
			 * A QueueConnection object is an active connection to a point-to-point JMS provider.
			 */
			QueueConnection connection = factory.createQueueConnection();
			/* Open a session
			 * 
			 * A QueueSession object provides methods for creating QueueReceiver, QueueSender, QueueBrowser, and TemporaryQueue objects.
			 */
			QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			/* Start the connection
			 * 
			 * A Connection object is a client's active connection to its JMS provider. It typically allocates provider resources outside the Java virtual machine (JVM).
			 */
			connection.start();
			/* Create a receive
			 * 
			 * A client uses a QueueReceiver object to receive messages that have been delivered to a queue.
			 */
			QueueReceiver receiver = session.createReceiver(queue);
			/*Receive the message
			 * 
			 * The Message interface is the root interface of all JMS messages.
			 */
			Message message = receiver.receive();
			System.out.println(message);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
