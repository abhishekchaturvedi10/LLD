package com.example.demo.dao;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
	List<Borrow> findByUserAndReturnedFalse(User user);
	Optional<Borrow> findByUserAndBookAndReturnedFalse(User user, Book book);
}