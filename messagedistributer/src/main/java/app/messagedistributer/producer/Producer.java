package app.messagedistributer.producer;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Producer {

    private final static String QUEUE_NAME = "testQueue";
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private static int successCount;
	private static int errorCount;

    public static void main(String[] args) throws Exception {
    	run();
    }

	public static void run() throws Exception {

    	
    	Properties properties = new Properties();
        try (InputStream input = Producer.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (input == null) {
            	logger.error("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);
        }
    	
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(properties.getProperty("rabbitmq.host"));
        factory.setPort(Integer.parseInt(properties.getProperty("rabbitmq.port")));
        factory.setUsername(properties.getProperty("rabbitmq.username"));
        factory.setPassword(properties.getProperty("rabbitmq.password"));
        
        
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String[] message = properties.getProperty("messages.pass").split(",");
            for (String msg : message) {
            	channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            	logger.info(" [x] Successfully Sent '" + msg + "'");
            	successCount++;
            }
            logger.info("Total messages processed successfully: " + successCount);
        }catch(Exception e){
        	errorCount++;
			logger.error(" [x] Error processing message: " + e.getMessage());
        }
    		
	}
}
