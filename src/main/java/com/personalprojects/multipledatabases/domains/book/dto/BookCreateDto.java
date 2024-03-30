package com.personalprojects.multipledatabases.domains.book.dto;

public record BookCreateDto(
    String title,
    String subject,
    String author
) {
}
