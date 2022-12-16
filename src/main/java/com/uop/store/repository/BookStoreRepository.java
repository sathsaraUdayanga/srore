package com.uop.store.repository;

import com.uop.store.entity.Book;
import com.uop.store.entity.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookStoreRepository extends JpaRepository<BookStore, Integer> {

    @Query("SELECT bs FROM BookStore bs WHERE bs.location.country=?1 AND bs.location.city=?2")
    List<BookStore> getStoreByCountryAndCity(String country, String city);

    @Query("SELECT bs.books FROM BookStore bs WHERE bs.id=?1")
    List<Book> getBooks(int id);

    BookStore findById(int id);
}
