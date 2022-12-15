package com.uop.store.service;

import com.uop.store.dto.BookDto;
import com.uop.store.dto.CommonResponse;
import com.uop.store.entity.Book;
import com.uop.store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(int id) {
        Book book = bookRepository.findById(id);
        return book;
    }

    public Book addBook(BookDto bookDto) {
        Book newBook = new Book(
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getYear(),
                bookDto.getDescription()
        );
        return bookRepository.save(newBook);
    }


    public CommonResponse deleteBook(int bookId) {
        Book book = bookRepository.findById(bookId);
        if (book != null) {
            bookRepository.delete(book);
            return new CommonResponse(true, "Successfully deleted the book :" + bookId);
        } else {
            return new CommonResponse(false, "Cannot find a book with id :" + bookId);
        }
    }

    public CommonResponse updateBook(int id, BookDto bookDto) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            book.setName(bookDto.getName());
            book.setAuthor(bookDto.getAuthor());
            book.setDescription(bookDto.getDescription());
            book.setYear(bookDto.getYear());
            bookRepository.save(book);
            return new CommonResponse(true, "Successfully updated the book :" + id);
        } else {
            return new CommonResponse(false, "Cannot find a book with id :" + id);
        }
    }
}
