package com.example.bookshop;

import com.example.bookshop.Services.AuthorService;
import com.example.bookshop.Services.BookService;
import com.example.bookshop.Services.CategoryService;
import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;

    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {
        seedDatabase();

//        printAllBook(2000);
        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);

    }
    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBook(int year) {
        bookService.findAllBookAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedDatabase() throws IOException {
        categoryService.seed();
        authorService.seed();
        bookService.seed();
    }
}
