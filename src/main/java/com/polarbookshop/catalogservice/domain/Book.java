package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

/**
 * 스프링 데이터 JPA는 가변 객체를 사용하기 때문에 자바 레코드를 사용할 수 없다.
 * JPA 엔티티 클래스는 @Entity 어노테이션으로 표시하며 인수를 갖지 않는 기본 생성자를 가져야한다.
 * JPA 식별자는 org.springframework.data.annotation 대신 jakarta.persistence 패키지의 @Id 및 @Version 어노테이션을 사용한다.
 * @param id
 * @param isbn
 * @param title
 * @param author
 * @param price
 * @param version
 */
public record Book(

        // 이 필드를 엔티티에 대한 기본키로 식별한다 (대리키 / 기술적 키)
        @Id
        Long id,

        @NotBlank(message = "The book ISBN must be defined.")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The ISBN format must be valid."
        )
        String isbn,
        @NotBlank(message = "The book title must be defined.")
        String title,
        @NotBlank(message = "The book author must be defined.")
        String author,
        @NotNull(message = "The book price must be defined.")
                @Positive(message = "The book price must be greater than zero.")
        Double price,

        // 낙관적 락을 위해 사용되는 엔티티 버전
        @Version
        int version
) {
        public static Book of(String isbn, String title, String author, Double price) {
                // ID가 널이고 버전이 0이면 새로운 엔티티로 인식한다.
                return new Book(null, isbn, title, author, price, 0);
        }
}
