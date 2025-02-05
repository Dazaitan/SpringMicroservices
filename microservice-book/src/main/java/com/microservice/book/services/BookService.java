package com.microservice.book.services;

import com.microservice.book.model.Book;
import com.microservice.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void removeBook(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Libro no encontrado con el ID " + id);
        }
    }

    public List<Book> searchBooks(String author, String genre, String category) {
        if (author != null) {
            return bookRepository.findByAuthor(author);
        } else if (genre != null) {
            return bookRepository.findByGenre(genre);
        } else if (category != null) {
            return bookRepository.findByCategory(category);
        }
        return bookRepository.findAll();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con el ID " + id));
    }
}
