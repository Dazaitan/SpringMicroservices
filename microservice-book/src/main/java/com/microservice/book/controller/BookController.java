package com.microservice.book.controller;

import com.microservice.book.model.Book;
import com.microservice.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/library")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }
    @DeleteMapping("/remove/{id}")
    public void removeBook(@PathVariable Integer id) {
        bookService.removeBook(id);
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> searchBookById(@PathVariable Integer id) {
        Optional<Book> book= bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String author,
                                  @RequestParam(required = false) String genre,
                                  @RequestParam(required = false) String category) {
        return bookService.searchBooks(author, genre, category);
    }
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/export")
    public String exportInventory(@RequestParam String filename) {
        try {
            bookService.exportToCSV(filename);
            return "Exportacion exitosa a " + filename;
        } catch (IOException e) {
            return "Error durante la exportacion: " + e.getMessage();
        }
    }
}
