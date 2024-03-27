package com.bookshop.web;

import com.bookshop.domain.Book;
import com.bookshop.domain.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping(value = "/list", produces = "application/json")
    public List<Book> getBooks() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {

        Book book = repository.findById(id).orElse(null);
        if(book == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<String>> addBook(@RequestBody Map<String, String> bookMap) {
        Book book = new Book();

        // Checking if book already exists
        if((!bookMap.get("isbn10").isEmpty() && repository.findByIsbn10(bookMap.get("isbn10")).isPresent()) || (!bookMap.get("isbn13").isEmpty() && repository.findByIsbn13(bookMap.get("isbn13")).isPresent())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of("Book already exists"));
        }

        // Setting required fields
        try {
            book.setTitle(bookMap.get("title"));
            book.setAuthor(bookMap.get("author"));
            book.setPublisher(bookMap.get("publisher"));
            book.setReleaseDate(Date.valueOf(bookMap.get("releaseDate")));
            book.setGenre(bookMap.get("genre"));
            book.setPageCount(Short.parseShort(bookMap.get("pageCount")));
            book.setDescription(bookMap.get("description"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of("Title, author, publisher, release date, genre, description, and page count are all required"));
        }

        // Setting optional fields
        book.setIsbn10(bookMap.getOrDefault("isbn10",null));
        book.setIsbn13(bookMap.getOrDefault("isbn13",null));
        book.setLanguage(bookMap.getOrDefault("language","English"));
        book.setImage(bookMap.getOrDefault("image","https://icons.getbootstrap.com/assets/icons/book.svg"));
        book.setHardcoverPrice(bookMap.get("hardcoverPrice") != null ? Double.parseDouble(bookMap.get("hardcoverPrice")): 0 );
        book.setPaperbackPrice(bookMap.get("paperbackPrice") != null ? Double.parseDouble(bookMap.get("paperbackPrice")): 0);
        book.setEbookPrice(bookMap.get("ebookPrice") != null ? Double.parseDouble(bookMap.get("ebookPrice")) : 0);
        book.setAudiobookPrice(bookMap.get("audiobookPrice") != null ? Double.parseDouble(bookMap.get("audiobookPrice")) : 0);
        book.setRatings(bookMap.get("ratings") != null ? Double.parseDouble(bookMap.get("ratings")) : 0.0);
        book.setRatingsCount(bookMap.get("ratingsCount") != null ? Integer.parseInt(bookMap.get("ratingsCount")) : 0);

        // Ensuring Price is set for at least one format
        if(book.getEbookPrice() == null && book.getAudiobookPrice() == null && book.getHardcoverPrice() == null && book.getPaperbackPrice() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of("Price of at least one format is required."));
        }

        repository.save(book);
        return ResponseEntity.status(HttpStatus.OK).body(List.of("Book added."));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<String>> updateBook(@PathVariable Long id, @RequestBody Map<String, String> bookMap) {
        Book book = repository.findById(id).isPresent() ? repository.findById(id).get() : null;

        // Checking if book exists
        if(book == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of("Book not found"));
        }

        // Updating fields
        book.setGenre(bookMap.getOrDefault("genre", book.getGenre()));
        book.setAuthor(bookMap.getOrDefault("author", book.getAuthor()));
        book.setPublisher(bookMap.getOrDefault("publisher", book.getPublisher()));
        book.setReleaseDate(bookMap.get("releaseDate") != null ? Date.valueOf(bookMap.get("releaseDate")) : book.getReleaseDate());
        book.setPageCount((bookMap.get("pageCount") != null && !bookMap.get("pageCount").isEmpty()) ? Short.parseShort(bookMap.get("pageCount")) : book.getPageCount());
        book.setDescription(bookMap.getOrDefault("description", book.getDescription()));
        book.setTitle(bookMap.getOrDefault("title", book.getTitle()));
        book.setIsbn10(bookMap.getOrDefault("isbn10", book.getIsbn10()));
        book.setIsbn13(bookMap.getOrDefault("isbn13", book.getIsbn13()));
        book.setLanguage(bookMap.getOrDefault("language", book.getLanguage()));
        book.setImage(bookMap.getOrDefault("image", book.getImage()));
        book.setHardcoverPrice((bookMap.get("hardcoverPrice") != null && !bookMap.get("hardcoverPrice").isEmpty()) ? Double.parseDouble(bookMap.get("hardcoverPrice")) : book.getHardcoverPrice());
        book.setPaperbackPrice((bookMap.get("paperbackPrice") != null && !bookMap.get("paperbackPrice").isEmpty()) ? Double.parseDouble(bookMap.get("paperbackPrice")) : book.getPaperbackPrice());
        book.setEbookPrice((bookMap.get("ebookPrice") != null && !bookMap.get("ebookPrice").isEmpty()) ? Double.parseDouble(bookMap.get("ebookPrice")) : book.getEbookPrice());
        book.setAudiobookPrice((bookMap.get("audiobookPrice") != null && !bookMap.get("audiobookPrice").isEmpty()) ? Double.parseDouble(bookMap.get("audiobookPrice")) : book.getAudiobookPrice());
        book.setRatings((bookMap.get("ratings") != null && !bookMap.get("ratings").isEmpty()) ? Double.parseDouble(bookMap.get("ratings")) : book.getRatings());
        book.setRatingsCount((bookMap.get("ratingsCount") != null && !bookMap.get("ratingsCount").isEmpty()) ? Integer.parseInt(bookMap.get("ratingsCount")) : book.getRatingsCount());

        repository.save(book);
        return ResponseEntity.status(HttpStatus.OK).body(List.of("Book updated"));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<List<String>> deleteBook(@PathVariable Long id) {
        if(repository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of("Book Not Found"));
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(List.of("Book deleted"));
    }
}
