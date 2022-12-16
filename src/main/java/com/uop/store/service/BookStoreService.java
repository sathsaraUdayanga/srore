package com.uop.store.service;

import com.uop.store.dto.BookStoreDto;
import com.uop.store.dto.CommonResponse;
import com.uop.store.entity.Book;
import com.uop.store.entity.BookStore;
import com.uop.store.entity.Location;
import com.uop.store.repository.BookRepository;
import com.uop.store.repository.BookStoreRepository;
import com.uop.store.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookStore addStore(BookStoreDto bookStoreDto) {

        Location location = new Location(
                bookStoreDto.getLocation().getCountry(),
                bookStoreDto.getLocation().getProvince(),
                bookStoreDto.getLocation().getStreet(),
                bookStoreDto.getLocation().getCity()
        );

        Location savedLocation = locationRepository.save(location);
        BookStore bookStore = new BookStore(bookStoreDto.getName(), savedLocation, 1);
        return bookStoreRepository.save(bookStore);
    }

    public List<BookStore> searchStore(String country, String city) {
        return bookStoreRepository.getStoreByCountryAndCity(country, city);
    }

    public List<Book> getBooksOfStore(int id) {
        return bookStoreRepository.getBooks(id);
    }

    public List<BookStore> findAllStores() {
        return bookStoreRepository.findAll();
    }

    public CommonResponse addBooksToStore(int storeId, List<Integer> bookIds) {
        List<Book> books = bookRepository.findByIdList(bookIds);
        if (books.isEmpty()) {
            return new CommonResponse(false, "books not found");
        }

        BookStore store = bookStoreRepository.findById(storeId);
        if (store == null) {
            return new CommonResponse(false, "invalid book store");
        } else {
            store.setBooks(books);
            bookStoreRepository.save(store);
            return new CommonResponse(true, "books added successfully");
        }
    }
}
