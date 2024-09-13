package app.messagedistributer.testcases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.messagedistributer.consumer.Consumer;
import app.messagedistributer.producer.Producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {

	@BeforeEach
	public void sentMessage() throws Exception {
		Producer.run();
	}

	@Test // This is for the success scenario where i am sending two messages to the queue and it will r=consumed two messages so the successcount would be 2
	public void successScenario() throws IOException, TimeoutException {
		// Act: Call the run method
		Consumer.run();
		// Assert: successCount should be incremented
		Assertions.assertEquals(2, Consumer.successCount);
	}
	
	@Test // This is for the success scenario where i am sending two messages to the queue and it will consumed two messages so the successcount would be 2
	public void failureScenario() throws IOException, TimeoutException {
		// Act: Call the run method
		Consumer.run();
		// Assert: successCount should be incremented
		Assertions.assertEquals(1, Consumer.successCount);
	}
}
