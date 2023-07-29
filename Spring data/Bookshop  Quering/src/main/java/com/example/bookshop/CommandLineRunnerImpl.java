package com.example.bookshop;

import com.example.bookshop.Services.AuthorService;
import com.example.bookshop.Services.BookService;
import com.example.bookshop.Services.CategoryService;
import com.example.bookshop.entity.AgeRestriction;
import com.example.bookshop.entity.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final Scanner scanner;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, Scanner scanner) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.scanner = scanner;
    }


    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
        printAllBook(2000);
        System.out.println("Select exercise number");
        int numberEx = Integer.parseInt(scanner.nextLine());

        switch (numberEx) {
            case 1 -> booksTittleByAge();
            case 2 -> goldenBook();
            case 3 -> BooksByPrice();
            case 4 -> NotReleasedBooks();
            case 5 -> BooksReleasedBeforeDate();
            case 6 -> AuthorsSearch();
            case 7 -> BooksSearch();
            case 8 -> BookTitlesSearch();
            case 9 -> CountBooks();
            case 10 -> TotalBookCopies();
            case 11 -> ReducedBook();

        }
    }

    private void ReducedBook() {
        System.out.println("Enter title");
        String title = scanner.nextLine();

        bookService.findAllBookByTitleName(title)
                .forEach(System.out::println);
    }

    private void TotalBookCopies() {
        authorService.findAllAuthorAndCountCopies().forEach(System.out::println);
    }

    private void CountBooks() {
        System.out.println("Enter number of symbol");
        int count = Integer.parseInt(scanner.nextLine());

        System.out.println(bookService.findAllBookWithCountInTitle(count));
    }

    private void BookTitlesSearch() {
        System.out.println("Enter string");
        String containString = scanner.nextLine();

        bookService.findAllBookByAuthorsEndWith(containString)
                .forEach(System.out::println);
    }

    private void BooksSearch() {
        System.out.println("Enter string");
        String containString = scanner.nextLine();

        bookService.findAllBookWithTitleContains(containString)
                .forEach(System.out::println);
    }

    private void AuthorsSearch() {
        System.out.println("Enter ends string");
        String end = scanner.nextLine();

        authorService.findAllAuthorsEndsWith(end)
                .forEach(System.out::println);
    }

    private void BooksReleasedBeforeDate() {
        System.out.println("Enter data in format day-month-year");
        LocalDate localDate = LocalDate.parse
                (scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        bookService.findAllBookBeerDate(localDate)
                .forEach(System.out::println);
    }

    private void NotReleasedBooks() {
        System.out.println("Enter year");
        int year = Integer.parseInt(scanner.nextLine());

        bookService.findAllBookNotReleasedInGivenYear(year)
                .forEach(System.out::println);
    }

    private void BooksByPrice() {
        bookService.findAllBookByPriceBetween()
                .forEach(System.out::println);
    }

    private void goldenBook() {
        bookService.findAllGoldenTitleWith5000Copies()
                .forEach(System.out::println);
    }

    private void booksTittleByAge() {
        System.out.println("Enter age restriction");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(scanner.nextLine().toUpperCase());

        bookService.findAllBookWithAgeRestriction(ageRestriction)
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
