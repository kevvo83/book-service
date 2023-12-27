package com.kevpersonal.controller;

import com.kevpersonal.entity.Book;
import com.kevpersonal.repository.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kevpersonal.exceptions.BookTitleNotFoundException;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepositoryImpl bookRepository;

    @GetMapping("/book/title/{title}")
    public Book findBookByTitle(@PathVariable String title) {
        Optional<Book> res = bookRepository.findByTitle(title);

        if (res.isEmpty()) { throw new BookTitleNotFoundException("Book Title \"" + title + "\" not found"); }
        else { return res.get(); }
    }

    @GetMapping("/book/id/{id}")
    public Book findBookById(@PathVariable Long id) {
        return bookRepository.findBookById(id);
    }

}
