package com.example.bookshop.Repository;

import com.example.bookshop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);
    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);


}
