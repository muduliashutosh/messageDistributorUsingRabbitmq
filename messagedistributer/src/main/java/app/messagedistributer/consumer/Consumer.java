package app.messagedistributer.consumer;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Consumer {

	private final static String QUEUE_NAME = "testQueue";
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	public static int successCount = 0;
	public static int errorCount = 0;

	public static void main(String[] args) throws IOException, TimeoutException {
		run();
	}

	public static void run() throws IOException, TimeoutException {
		Properties properties = new Properties();

		// Load properties from file
		try (InputStream input = Consumer.class.getClassLoader().getResourceAsStream("app.properties")) {
			if (input == null) {
				logger.error("Sorry, unable to find app.properties");
				return;
			}
			properties.load(input);
		}

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(properties.getProperty("rabbitmq.host"));
		factory.setPort(Integer.parseInt(properties.getProperty("rabbitmq.port")));
		factory.setUsername(properties.getProperty("rabbitmq.username"));
		factory.setPassword(properties.getProperty("rabbitmq.password"));
		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			logger.info(" [*] Waiting for messages.");

			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String message = new String(delivery.getBody(), "UTF-8");
				try {
					logger.info(" [x] Received message: '" + message + "'");
					successCount++;
					logger.info(" [x] Processed '" + message + "' successfully.");
					// Acknowledge message after processing
					channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
				} catch (Exception e) {
					errorCount++;
					logger.error(" [x] Error processing message: " + e.getMessage(), e);
				} finally {
					logResults();
				}
			};
			
			channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
			});
		}catch(Exception e) {
			errorCount++;
			logger.error(" [x] Error processing message: " + e.getMessage());
			logResults();
		}
		
	}
	private static void logResults() {
		logger.info("Total messages processed successfully: " + successCount);
		logger.info("Total messages failed to process: " + errorCount);
	}
}
