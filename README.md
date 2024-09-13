
Message Distributor Using RabbitMQ
===================================
This assignment involves developing a simple message-driven application in core Java using a message queue. The application uses RabbitMQ, a widely used message broker that enables communication between distributed systems through message passing. Producers publish messages to a message queue, and consumers receive and process these messages.

The project consists of two main classes: Producer and Consumer, which will run individually to demonstrate their functionality.

Project Structure
=================
app.properties - Stores RabbitMQ configuration and messages.
Producer.java - Contains the logic to connect to RabbitMQ and send messages.
Consumer.java - Contains the logic to connect to RabbitMQ and receive messages.
ConsumerTest.java - Includes JUnit tests to validate the consumer functionality.

Producer.java
==============
The Producer class is responsible for sending messages to the RabbitMQ queue. It reads RabbitMQ connection details and messages from a properties file, then publishes each message to the queue. The class also logs the count of successfully sent messages. In case of an error, it logs the failure count along with exception details.

Properties (app.properties)
---------------------------
# RabbitMQ Configuration
rabbitmq.host=localhost
rabbitmq.port=5672
rabbitmq.username=guest
rabbitmq.password=guest

# Messages to pass (comma-separated)
messages.pass=hello world!!,hello all!!

In this configuration file, the RabbitMQ connection settings are defined, and multiple messages are provided in a comma-separated format.

Consumer.java
==============
The Consumer class connects to RabbitMQ and consumes the messages available in the queue. Like the Producer, it logs the count of successfully received messages and any potential errors.

ConsumerTest.java (JUnit Testing)
=================================

@BeforeEach: This annotation ensures that the sentMessage() method runs before each test case, allowing Producer.run() to send messages to the queue and prepare the environment for the Consumer.

Test Cases
----------
successScenario():

Purpose: To verify that the consumer successfully consumes two messages from the queue.
Process: The Consumer.run() method processes the messages, and the test asserts that Consumer.successCount equals 2, indicating two messages were successfully consumed.

failureScenario():

Purpose: To simulate a failure scenario or test incorrect message consumption.
Process: Similar to the success scenario, Consumer.run() is called to process messages. The test asserts that Consumer.successCount equals 1 to check the behavior when not all messages are consumed correctly.


Running the Application
========================
Environment Setup:
------------------

#IDE: Spring Tool Suite (STS) 4.25

#Java Version: JDK 21

#Download messagedistibuter folder into your local system and Import the Gradle project into STS 4.25.

#Configuration:
Set RabbitMQ connection details (host, port, username, password) in app.properties. Default values are for a local system setup.
Provide messages using the messages.pass property in a comma-separated format.

#Execution:
First, run the Producer class to send messages to the RabbitMQ queue.
Then, run the Consumer class to consume the messages from the queue.

#Testing:
Run the ConsumerTest class using JUnit to validate message consumption through test cases.




Note:-
--------
This structured approach simplifies the process of sending and receiving messages using RabbitMQ in a Java application. It also incorporates testing for robust message handling scenarios.







