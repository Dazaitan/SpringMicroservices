package com.microservice.export.model;

import lombok.Data;

@Data
public class BookDTO {
    private int id;
    private String title;
    private String author;
    private String genre;
    private String category;
    private double rating;
}
