package com.example.bookshop.Services;

import com.example.bookshop.entity.Author;

import java.io.IOException;

public interface AuthorService {
    void seed() throws IOException;
    Author getRandomAuthor();
}
