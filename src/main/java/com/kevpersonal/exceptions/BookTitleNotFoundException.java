package com.kevpersonal.exceptions;

public class BookTitleNotFoundException extends RuntimeException {

    public BookTitleNotFoundException(String msg) {
        super(msg);
    }
}
