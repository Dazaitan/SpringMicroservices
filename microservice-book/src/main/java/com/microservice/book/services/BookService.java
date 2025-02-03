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
    public void addBook(Book book) {
        bookRepository.save(book);
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
    public Optional<Book> getBookById(Integer id){
        return bookRepository.findById(id);
    }
    public void exportToCSV(String filename) throws IOException {
        List<Book> books = bookRepository.findAll();
        FileWriter writer = new FileWriter(filename);

        writer.append("Title,Author,Genre,Category,Rating\n");
        for (Book book : books) {
            writer.append(book.getTitle()).append(",");
            writer.append(book.getAuthor()).append(",");
            writer.append(book.getGenre()).append(",");
            writer.append(book.getCategory()).append(",");
            writer.append(String.valueOf(book.getRating())).append("\n");
        }

        writer.flush();
        writer.close();
    }
}
