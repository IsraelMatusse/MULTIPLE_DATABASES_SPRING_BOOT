package com.personalprojects.multipledatabases.domains.book.model;

import com.personalprojects.multipledatabases.domains.book.dto.BookCreateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subject;
    private String author;

    public Book(BookCreateDto dto, String author){
        this.author=author;
        this.subject=dto.subject();
        this.title=dto.title();
    }

}
