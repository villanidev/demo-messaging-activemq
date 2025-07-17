package villanidev.demo.messaging.activemq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import villanidev.demo.messaging.activemq.dto.BookDTO;

@Component
public class BookConsumer {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookConsumer.class);

    @JmsListener(destination = "${app.queue.name}")
    public void receiveBook(BookDTO book) {
        log.info("Received book:{}", book);
    }
}
