package com.kevpersonal.repository;

import com.kevpersonal.entity.Book;
import jakarta.annotation.Nullable;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private PlatformTransactionManager hibernateTransactionManager;

    @Override
    public Optional<Book> findByTitle(String titleToSearch) {

        return titleToSearch.equals("Foundation") ?
                Optional.of(new Book("Arthur", "Foundation")) :
                Optional.empty();

    }

    @Override
    public Book findBookById(Long id) {
        return null;
    }

    @Override
    @NonNull
    public <S extends Book> S save(@NonNull S entity) {
        return entity;
    }

    @Override
    @NonNull
    public <S extends Book> Iterable<S> saveAll(@NonNull Iterable<S> entities) {
        return entities;
    }

    @Override
    @NonNull
    public Optional<Book> findById(@NonNull Long aLong) {
        return Optional.empty();
    }

    @Override
    @NonNull
    public boolean existsById(@NonNull Long aLong) {
        return false;
    }

    @Override
    @NonNull
    public Iterable<Book> findAll() {
        return new ArrayList<Book>();
    }

    @Override
    @NonNull
    public Iterable<Book> findAllById(@NonNull Iterable<Long> longs) {
        return new ArrayList<Book>();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(@NonNull Long aLong) {

    }

    @Override
    public void delete(@NonNull Book entity) {

    }

    @Override
    public void deleteAllById(@NonNull Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(@NonNull Iterable<? extends Book> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
