package com.example.demo.dao;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByAuthor(String author);
	List<Book> findByAvailable(boolean available);
	List<Book> findByAuthorAndAvailable(String author, boolean available);
}