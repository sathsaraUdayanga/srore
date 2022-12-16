package com.uop.store.Controller;

import com.uop.store.dto.BookDto;
import com.uop.store.dto.CommonResponse;
import com.uop.store.entity.Book;
import com.uop.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public List<Book> getAllBooks( ) {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") int bookId) {
        return bookService.findBookById(bookId);
    }

    @PostMapping("/add")
    public Book addNewBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteBook(@PathVariable("id") int bookId) {
        return bookService.deleteBook(bookId);
    }

    @PutMapping("/{id}")
    public CommonResponse updateBook(@PathVariable int id, @RequestBody BookDto bookDto) {
        return bookService.updateBook(id,bookDto);
    }
}
