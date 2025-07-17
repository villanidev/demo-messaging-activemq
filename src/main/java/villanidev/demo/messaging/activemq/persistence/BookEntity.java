package villanidev.demo.messaging.activemq.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import villanidev.demo.messaging.activemq.entrypoint.BookDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "tb_books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    private BigDecimal price;

    BookDTO toDto(BookEntity entity) {
        return new BookDTO(entity.isbn,
                entity.title,
                entity.author,
                entity.publicationDate,
                entity.price);
    }
}
