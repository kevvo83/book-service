package com.kevpersonal.repository;

import com.kevpersonal.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByTitle(String titleToSearch);

    Book findBookById(Long id);
}
