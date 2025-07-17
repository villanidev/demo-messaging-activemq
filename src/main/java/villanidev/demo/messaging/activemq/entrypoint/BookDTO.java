package villanidev.demo.messaging.activemq.entrypoint;

import jakarta.validation.constraints.*;
import villanidev.demo.messaging.activemq.persistence.BookEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record BookDTO(
        @NotBlank
        @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|\n" +
                "(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$")
        String isbn,
        @NotBlank
        @Size(max = 100)
        String title,
        @NotBlank
        @Size(max = 50)
        String author,
        @PastOrPresent
        LocalDate publicationDate,
        @Positive
        BigDecimal price) implements Serializable {

    public BookEntity toEntity(BookDTO dto) {
       return BookEntity.builder()
               .isbn(dto.isbn())
               .title(dto.title())
               .author(dto.author())
               .publicationDate(dto.publicationDate())
               .price(dto.price())
               .build();
    }
}
