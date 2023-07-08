package com.example.bookshop.Services.impl;

import com.example.bookshop.Repository.AuthorRepository;
import com.example.bookshop.Repository.BookRepository;
import com.example.bookshop.Services.AuthorService;
import com.example.bookshop.Services.BookService;
import com.example.bookshop.Services.CategoryService;
import com.example.bookshop.entity.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seed() throws IOException {

        if (bookRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of("src/main/resources/files/books.txt"))
                .forEach(row -> {
                    String[] book = row.split("\\s+");

                    Book createBook = createBookFromFile(book);
                    bookRepository.save(createBook);

                });
    }

    @Override
    public List<Book> findAllBookAfterYear(int year) {
        return bookRepository.findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    private Book createBookFromFile(String[] book) {

        EditionType editionType = EditionType.values()[Integer.parseInt(book[0])];

        LocalDate localDate = LocalDate.parse(book[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        Integer copies = Integer.parseInt(book[2]);

        BigDecimal price = new BigDecimal(book[3]);

        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(book[4])];

        String title = Arrays.stream(book).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();

        Set<Category> categorySet = categoryService.getRandomCategory();

        return new Book(editionType, localDate, copies, price, ageRestriction, title, author, categorySet);

    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        List<String> collect = bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
        return collect;
    }
}
