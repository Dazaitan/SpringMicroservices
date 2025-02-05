package com.microservice.book.controller;

import com.microservice.book.model.Book;
import com.microservice.book.services.BookService;
import com.microservice.book.services.ResourceNotFoundException;
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
    public ResponseEntity<?> addBook(@RequestBody Book book){
        try {
            Book savedBook = bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos del libro no válidos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al guardar el libro: " + e.getMessage());
        }
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeBook(@PathVariable Integer id) {
        try {
            bookService.removeBook(id);
            return ResponseEntity.ok().body("El libro ha sido eliminado exitosamente.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al intentar eliminar el libro.");
        }
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<?> searchBookById(@PathVariable Integer id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok().body(book);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al obtener la información del libro.");
        }
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
}
