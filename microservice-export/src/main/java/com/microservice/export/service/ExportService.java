package com.microservice.export.service;

import com.microservice.export.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Service
public class ExportService {
    @Autowired
    private RestTemplate restTemplate;

    public List<Book> fetchBooksFromBookService() {
        String url = "http://book-service/books/all";
        Book[] booksArray = restTemplate.getForObject(url, Book[].class);
        return Arrays.asList(booksArray);
    }

    public void exportBooksToCSV(String filename) throws IOException {
        List<Book> books = fetchBooksFromBookService();
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
