package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

// 완전한 스프링 웹 애플리케이션 콘텍스트와 임의의 포트를 듣는 서블릿 컨테이너를 로드한다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}


    @Test
    @DisplayName("POST 요청이 들어왔을 때 책이 생성된다.")
    void whenPostRequestThenBookCreated() {
        var expectedBook = Book.of("1231231231", "Title", "Author", 9.90);

        webTestClient
                .post()
                .uri("/books")// /books 엔드포인트로 요청을 보낸다
                .bodyValue(expectedBook)// 요청 본문에 Book 객체를 추가한다.
                .exchange()// 요청을 전송한다.
                .expectStatus().isCreated() // HTTP 응답이 201 Created 상태인지 확인한다.
                .expectBody(Book.class).value(actualBook -> {
                    // HTTP 응답의 본문이 null이 아닌지 확인한다.
                    Assertions.assertThat(actualBook).isNotNull();

                    // 생성된 객체가 예상과 동일한지 확인한다.
                    Assertions.assertThat(actualBook.isbn())
                            .isEqualTo(expectedBook.isbn());
                });
    }
}
