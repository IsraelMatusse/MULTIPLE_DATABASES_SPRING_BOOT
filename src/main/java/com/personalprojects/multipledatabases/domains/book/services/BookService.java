package com.personalprojects.multipledatabases.domains.book.services;

import com.personalprojects.multipledatabases.domains.book.dto.BookCreateDto;
import com.personalprojects.multipledatabases.domains.book.model.Book;
import com.personalprojects.multipledatabases.domains.book.repos.BookRepo;
import com.personalprojects.multipledatabases.domains.user.model.User;
import com.personalprojects.multipledatabases.domains.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final UserService userService;

    public Book create(Book book) {
        return bookRepo.save(book);
    }

    @Transactional(value = "bookTransactionManager")
    public Book createBook(BookCreateDto dto){
        User user=userService.getUserByName(dto.author());
        Book book=new Book(dto,user.getName());
        return bookRepo.save(book);

    }


}
