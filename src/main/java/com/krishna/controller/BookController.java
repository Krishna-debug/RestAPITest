package com.krishna.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.model.Book;
import com.krishna.services.BookServices;

@RestController
public class BookController {

	@Autowired
	private BookServices bookServices;

	// Same as GetMapping
	// @RequestMapping(value = "/book", method = RequestMethod.GET)

	// Get all Book Handler
//	@GetMapping("/book")
//	public List<Book> getBooks() {//
//		return this.bookServices.getAllBooks();
//	}

	// Return Server Status code
	// Get all Book Handler
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getBooks() {

		List<Book> list = bookServices.getAllBooks();
		if (list.isEmpty()) {
			// Generate Status
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}

	// Get Single Book Handler
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = bookServices.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
//		return this.bookServices.getBookById(id);
		return ResponseEntity.of(Optional.of(book));
	}

//	// Create a new Book Handler
//	@PostMapping("/book")
//	public Book addBook(@RequestBody Book book) {
//		Book b = this.bookServices.addBook(book);
//		return b;
//	}

	// Return Sever Code
	// Create a new Book Handler
	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b = null;
		try {
			b = this.bookServices.addBook(book);
			System.out.println(book);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	// Delete Book Handler
//	@DeleteMapping("/book/{bookId}")
//	public void deleteBook(@PathVariable("bookId") int bookId) {
//		this.bookServices.deleteBook(bookId);
//	}

	// Return Server Code
	// Delete Book Handler
	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<?> deleteBook(@PathVariable("bookId") int bookId) {
		try {
			this.bookServices.deleteBook(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Update Book Handler
//	@PutMapping("/book/{bookId}")
//	public Book updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
//		this.bookServices.updateBook(book, bookId);
//		return book;
//	}

	@PutMapping("/book/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
		try {
			this.bookServices.updateBook(book, bookId);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
