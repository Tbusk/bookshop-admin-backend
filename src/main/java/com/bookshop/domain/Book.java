package com.bookshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "book_id")
    private Long bookID;

    @Column(unique = true, name = "isbn_10", length = 14)
    private String isbn10;

    @Column(unique = true, name = "isbn_13", length = 17)
    private String isbn13;

    @Column(nullable = false, length = 128)
    private String title;

    @Column(nullable = false, length = 5120)
    private String description;

    @Column(nullable = false, length = 64)
    private String genre;

    @Column(nullable = false, length = 128)
    private String author;

    @Column(nullable = false, length = 128)
    private String publisher;

    @Column(nullable = false, length = 128)
    private String image;

    @Column(nullable = false, length = 32)
    private String language;

    @Column(nullable = false, name = "page_count", length = 5)
    private Short pageCount;

    @Column(nullable = false, name = "release_date")
    private Date releaseDate;

    @Column(name = "hardcover_price")
    private Double hardcoverPrice;

    @Column(name = "paperback_price")
    private Double paperbackPrice;

    @Column(name = "ebook_price")
    private Double ebookPrice;

    @Column(name = "audiobook_price")
    private Double audiobookPrice;

    @Column(nullable = false)
    private Double ratings;

    @Column(nullable = false, length = 10, name = "ratings_count")
    private Integer ratingsCount;
}
