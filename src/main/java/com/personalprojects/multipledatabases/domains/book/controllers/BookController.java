package com.personalprojects.multipledatabases.domains.book.controllers;

import com.personalprojects.multipledatabases.domains.book.dto.BookCreateDto;
import com.personalprojects.multipledatabases.domains.book.model.Book;
import com.personalprojects.multipledatabases.domains.book.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @PostMapping
    public ResponseEntity<Book>createBook(@RequestBody BookCreateDto book) {
        try {
            Book createdBook = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}


