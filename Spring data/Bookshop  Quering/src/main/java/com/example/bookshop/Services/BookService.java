package com.example.bookshop.Services;

import com.example.bookshop.entity.AgeRestriction;
import com.example.bookshop.entity.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seed() throws IOException;

    List<Book> findAllBookAfterYear(int year);

    List<String> findAllBookWithAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllGoldenTitleWith5000Copies();

    List<String> findAllBookByPriceBetween();

    List<String> findAllBookNotReleasedInGivenYear(int year);

    List<String> findAllBookBeerDate(LocalDate localDate);

    List<String> findAllBookWithTitleContains(String containString);

    List<String> findAllBookByAuthorsEndWith(String containString);

    int findAllBookWithCountInTitle(int count);

    List<String> findAllBookByTitleName(String title);
}
