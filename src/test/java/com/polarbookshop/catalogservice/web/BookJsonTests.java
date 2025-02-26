package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest // JSON 직렬화에 중점을 둔 테스트 클래스임을 나타낸다.
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json; // JSON 직렬화 및 역직렬화를 확인하기 위한 유틸리티 클래스

    @Test
    @DisplayName("직렬화를 테스트한다.")
    void testSerialize() throws Exception {
        var book = new Book("1234567890", "Title", "Author", 9.90);
        var jsonContent = json.write(book);

        // JsonPath 형식을 사용해 JSON 객체를 탐색하고 자바의 JSON 변환을 확인한다.
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        Assertions.assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    @DisplayName("역직렬화를 테스트한다.")
    void testDeserialize() throws Exception {
        // 자바의 텍스트 블록 기능을 사용해 JSON 객체를 정의한다.
        var content = """
                {
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": 9.90
                }
                """;

        Assertions.assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "Title", "Author", 9.90));
    }


}
