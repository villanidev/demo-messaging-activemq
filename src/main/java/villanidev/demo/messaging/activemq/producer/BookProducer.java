package villanidev.demo.messaging.activemq.producer;

import jakarta.jms.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import villanidev.demo.messaging.activemq.entrypoint.BookDTO;

@Component
public class BookProducer {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookProducer.class);
    private final JmsTemplate jmsTemplate;
    private final String queueName;
    private final Topic bookTopic;

    public BookProducer(JmsTemplate jmsTemplate, @Value("${app.queue.name}") String queueName, Topic bookTopic) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
        this.bookTopic = bookTopic;
    }

    public void sendBook(BookDTO book) {
        jmsTemplate.convertAndSend(queueName, book);
        log.info("Sent book:{}", book);
    }

    public void broadcastToTopic(BookDTO book) {
        jmsTemplate.convertAndSend(bookTopic, book);
    }
}