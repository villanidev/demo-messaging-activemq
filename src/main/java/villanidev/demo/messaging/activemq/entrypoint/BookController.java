package villanidev.demo.messaging.activemq.entrypoint;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import villanidev.demo.messaging.activemq.producer.BookProducer;

import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookProducer bookProducer;

    public BookController(BookProducer bookProducer) {
        this.bookProducer = bookProducer;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> sendBook(@Valid @RequestBody BookDTO book) {
        bookProducer.sendBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", "Book sent to Artemis queue"));
    }

    @PostMapping("/broadcast")
    public ResponseEntity<Map<String, String>> broadcastToTopic(@Valid @RequestBody BookDTO book) {
        bookProducer.broadcastToTopic(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", "Book broadcasted to topic"));
    }
}
