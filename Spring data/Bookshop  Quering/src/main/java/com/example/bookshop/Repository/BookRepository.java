package com.example.bookshop.Repository;

import com.example.bookshop.entity.AgeRestriction;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesIsLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal price, BigDecimal price2);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllByTitleContains(String title);

    List<Book> findAllByAuthor_LastNameStartsWith(String author_lastName);

    @Query("SELECT count(b) FROM Book b WHERE length(b.title) > :param ")
    int countTittleSymbol(@Param(value = "param") int length);

    List<Book> findAllByTitle(String title);


}
