package villanidev.demo.messaging.activemq.consumer;

import jakarta.jms.Message;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import villanidev.demo.messaging.activemq.entrypoint.BookDTO;
import villanidev.demo.messaging.activemq.persistence.BookEntity;
import villanidev.demo.messaging.activemq.persistence.BookRepository;

@Component
public class BookConsumer {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookConsumer.class);

    private final BookRepository repository;
    private final JmsTemplate jmsTemplate;
    //private final String dlqName;

    public BookConsumer(BookRepository repository, JmsTemplate jmsTemplate/*, @Value("${app.queue.dlq}") String dlqName*/) {
        this.repository = repository;
        this.jmsTemplate = jmsTemplate;
        //this.dlqName = dlqName;
    }

    @JmsListener(destination = "${app.queue.name}")
    @Transactional
    public void receiveBook(@Payload BookDTO bookDto, Message message) {
        try {
            log.info("Received book:{}", bookDto);
            persistBook(bookDto);
            // Manual acknowledgment
            message.acknowledge();
        } catch (Exception e) {
            //sendToDLQ(bookDto, e);
            throw new RuntimeException(e);
        }
    }

    private void persistBook(BookDTO bookDto) {
        if (repository.existsByIsbn(bookDto.isbn())) {
            throw new IllegalArgumentException("Book with ISBN " + bookDto.isbn() + " already exists");
        }

        BookEntity entity = bookDto.toEntity(bookDto);
        repository.save(entity);
        log.info("Saved book with ID: {}", entity.getId());
    }

    /*private void sendToDLQ(BookDTO bookDto, Exception e) {
        log.error("Error processing book: {}", e.getMessage());
        jmsTemplate.convertAndSend(dlqName, bookDto, processor -> {
            processor.setStringProperty("ERROR_CAUSE", e.getMessage());
            return processor;
        });
    }*/

    @JmsListener(destination = "${app.topic.name}", subscription = "bookStoreSubscription")
    public void receiveBroadcast(@Payload BookDTO bookDto) {
        log.info("Received broadcast for book: {}", bookDto);
        // TODO Process broadcast message (e.g., notify other systems)
    }
}
