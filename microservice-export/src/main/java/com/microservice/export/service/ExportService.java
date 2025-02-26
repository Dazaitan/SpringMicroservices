package com.microservice.export.service;

import com.microservice.export.model.BookDTO;
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

    public List<BookDTO> fetchBooksFromBookService() {
        String url = "http://msvc-gateway:8070/api/library/all";
        BookDTO[] booksArray = restTemplate.getForObject(url, BookDTO[].class);
        return Arrays.asList(booksArray);
    }

    public void exportBooksToCSV(String filename) throws IOException {
        List<BookDTO> books = fetchBooksFromBookService();

        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Title,Author,Genre,Category,Rating\n");
            for (BookDTO book : books) {
                writer.append(book.getTitle()).append(",");
                writer.append(book.getAuthor()).append(",");
                writer.append(book.getGenre()).append(",");
                writer.append(book.getCategory()).append(",");
                writer.append(String.valueOf(book.getRating())).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Error writing to CSV file", e);
        }
    }
}
