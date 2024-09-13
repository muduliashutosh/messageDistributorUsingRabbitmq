package app.messagedistributer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.messagedistributer.consumer.Consumer;
import app.messagedistributer.producer.Producer;

@SpringBootApplication
public class MessagedistributerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MessagedistributerApplication.class, args);
	}

}
