package com.uop.store.Controller;

import com.uop.store.dto.BookStoreDto;
import com.uop.store.entity.Book;
import com.uop.store.entity.BookStore;
import com.uop.store.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class BookStoreController {

    @Autowired
    private BookStoreService bookStoreService;
    @PostMapping("/add")
    public BookStore addNewStore(@RequestBody BookStoreDto bookStoreDto) {
        return bookStoreService.addStore(bookStoreDto);
    }

    @GetMapping("/search")
    public List<BookStore> searchStore(@RequestParam String country, @RequestParam String city) {
        return bookStoreService.searchStore(country, city);
    }

    @GetMapping("/{id}/books")
    public List<Book> getBooksOfStore(@PathVariable int id){
        return bookStoreService.getBooksOfStore(id);
    }

    @GetMapping()
    public List<BookStore> getAllStores(){
        return bookStoreService.findAllStores();
    }
}
