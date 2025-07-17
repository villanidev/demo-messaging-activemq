package villanidev.demo.messaging.activemq.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import villanidev.demo.messaging.activemq.dto.BookDTO;

@Component
public class BookProducer {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookProducer.class);
    private final JmsTemplate jmsTemplate;
    private final String queueName;

    public BookProducer(JmsTemplate jmsTemplate, @Value("${app.queue.name}") String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public void sendBook(BookDTO book) {
        jmsTemplate.convertAndSend(queueName, book);
        log.info("Sent book:{}", book);
    }
}