package com.krishna.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.krishna.model.Book;

@Component
public class BookServices {

	private static List<Book> list = new ArrayList<>();

	static {
		list.add(new Book(1, "Java", "XYZ"));
		list.add(new Book(2, "C++", "A"));
		list.add(new Book(3, "C", "B"));
		list.add(new Book(4, "Python", "C"));
		list.add(new Book(5, "React", "D"));
	}

	// get all books
	public List<Book> getAllBooks() {
		return list;
	}

//	// get single book by id
//	public Book getBookById(int id) {
//		Book book = null;
//		// Use stream, java provide in java8
//		book = list.stream().filter(e -> e.getId() == id).findFirst().get();
//		return book;
//	}

	// Handle Exception
	// get single book by id
	public Book getBookById(int id) {
		Book book = null;
		try {
			// Use stream, java provide in java8
			book = list.stream().filter(e -> e.getId() == id).findFirst().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	// Adding the Book
	public Book addBook(Book b) {
		list.add(b);
		return b;
	}

	// Delete Book
	public void deleteBook(int bId) {
		list = list.stream().filter(book -> book.getId() != bId).collect(Collectors.toList());
	}

	// Update Book
	public void updateBook(Book book, int bookId) {
		list = list.stream().map(b -> {
			if (b.getId() == bookId) {
				b.setTitle(book.getTitle());
				b.setAuthor(book.getAuthor());
			}
			return b;
		}).collect(Collectors.toList());
	}

}
