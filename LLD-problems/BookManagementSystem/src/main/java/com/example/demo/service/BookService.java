// src/main/java/com/example/demo/service/BookService.java
package com.example.demo.service;

import com.example.demo.dao.BorrowRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.BookRepository;
import com.example.demo.entity.Book;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookService {
	
	private final BookRepository bookRepo;
	private final UserRepository userRepo;
	private final BorrowRepository borrowRepo;
	
	public BookService(BookRepository bookRepo, UserRepository userRepo, BorrowRepository borrowRepo) {
		this.bookRepo = bookRepo;
		this.userRepo = userRepo;
		this.borrowRepo = borrowRepo;
	}
	
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	public Book getBook(Long id) {
		return bookRepo.findById(id).orElse(null);
	}
	
	public List<Book> listBooks(String author, Boolean available) {
		if (author != null && available != null)
			return bookRepo.findByAuthorAndAvailable(author, available);
		if (author != null)
			return bookRepo.findByAuthor(author);
		if (available != null)
			return bookRepo.findByAvailable(available);
		return bookRepo.findAll();
	}
	
	public User registerUser(User user) {
		return userRepo.save(user);
	}
	
	public User getUser(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	
	@Transactional
	public synchronized void borrowBooks(Long userId, List<Long> bookIds) {
		if (bookIds.size() > 3)
			throw new IllegalArgumentException("Cannot borrow more than 3 books at once");
		
		User user = userRepo.findById(userId).orElseThrow();
		List<Borrow> currentBorrows = borrowRepo.findByUserAndReturnedFalse(user);
		
		for (Long bookId : bookIds) {
			Book book = bookRepo.findById(bookId).orElseThrow();
			// Constraint 2: Only one book of same type
			boolean alreadyBorrowedType = currentBorrows.stream()
					.anyMatch(b -> b.getBook().getType().equals(book.getType()));
			if (alreadyBorrowedType)
				throw new IllegalStateException("User already borrowed a book of type: " + book.getType());
			if (!book.isAvailable())
				throw new IllegalStateException("Book not available: " + book.getTitle());
			
			book.setAvailable(false);
			bookRepo.save(book);
			borrowRepo.save(new Borrow(null, user, book, false));
		}
	}
	
	@Transactional
	public void returnBook(Long userId, Long bookId) {
		User user = userRepo.findById(userId).orElseThrow();
		Book book = bookRepo.findById(bookId).orElseThrow();
		Borrow borrow = borrowRepo.findByUserAndBookAndReturnedFalse(user, book)
				.orElseThrow(() -> new IllegalStateException("No such borrow record"));
		borrow.setReturned(true);
		book.setAvailable(true);
		borrowRepo.save(borrow);
		bookRepo.save(book);
	}
	
	public List<Borrow> listUserBorrows(Long userId) {
		User user = userRepo.findById(userId).orElseThrow();
		return borrowRepo.findByUserAndReturnedFalse(user);
	}
}