package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile(value = {"testdata"}) // 이 클래스를 testdata 프로파일에 할당한다. 이 클래스는 testdata 프로파일이 활성화 될 때만 로드된다.
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * ApplicationReadyEvent가 발생하면 테스트 데이터 생성이 시작된다.
     * 이 이벤트는 애플리케이션 시작 단계가 완료되면 발생한다.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        var book1 = new Book("1234567891", "Northern Lights", "Lyra Silverstar", 9.90);
        var book2 = new Book("1234567892", "Polar Journey", "Iorek Polarson", 12.90);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }

}
