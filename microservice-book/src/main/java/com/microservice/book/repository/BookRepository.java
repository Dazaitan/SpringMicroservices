package com.microservice.book.repository;

import com.microservice.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
    List<Book> findByCategory(String category);
}
