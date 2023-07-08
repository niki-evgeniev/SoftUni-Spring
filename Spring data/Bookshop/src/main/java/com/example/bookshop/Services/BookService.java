package com.example.bookshop.Services;

import com.example.bookshop.entity.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seed() throws IOException;
    List<Book> findAllBookAfterYear(int year);
    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);
}
