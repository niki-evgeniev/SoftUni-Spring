package com.example.bookshop.Services;

import com.example.bookshop.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seed() throws IOException;

    Author getRandomAuthor();

    List<String> findAllAuthorsEndsWith(String end);

    List<String> findAllAuthorAndCountCopies();
}
