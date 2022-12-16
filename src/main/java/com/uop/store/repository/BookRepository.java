package com.uop.store.repository;

import com.uop.store.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();

    Book findById(int id);

    @Query(value = "SELECT b.* FROM book b WHERE b.id IN (?1)" , nativeQuery = true)
    List<Book> findByIdList(List<Integer> bookIds);
}
