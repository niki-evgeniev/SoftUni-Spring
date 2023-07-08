package com.example.bookshop.Services.impl;

import com.example.bookshop.Repository.AuthorRepository;
import com.example.bookshop.Services.AuthorService;
import com.example.bookshop.entity.Author;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seed() throws IOException {

        if (authorRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of("src/main/resources/files/authors.txt"))
                .stream()
                .forEach(row -> {
                    String[] names = row.split("\\s+");
                    Author author = new Author(names[0], names[1]);
                    authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
        long count = authorRepository.count();
        long min = 1;
        long randomId = (int) (Math.random() * count);


        return authorRepository.findById(randomId)
                .orElse(null);
    }
}
