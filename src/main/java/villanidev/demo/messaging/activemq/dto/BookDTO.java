package villanidev.demo.messaging.activemq.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record BookDTO(String isbn,
                      String title,
                      String author,
                      LocalDate publicationDate,
                      Double price) implements Serializable {
}
