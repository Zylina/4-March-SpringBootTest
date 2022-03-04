package com.org.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.org.demo.Book;
import com.org.repository.BookRepository;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BookController {
  @Autowired
  BookRepository BookRepository;
  @GetMapping("/Books")
  public ResponseEntity<List<Book>> getAllTutorials(@RequestParam(required = false) String title) {
    try {
      List<Book> tutorials = new ArrayList<Book>();
      if (title == null)
        BookRepository.findAll().forEach(tutorials::add);
      else
        BookRepository.findByTitleContaining(title).forEach(tutorials::add);
      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/Books/{id}")
  public ResponseEntity<Book> getTutorialById(@PathVariable("id") long id) {
    Optional<Book> BookData = BookRepository.findById(id);
    if (BookData.isPresent()) {
      return new ResponseEntity<>(BookData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/Books")
  public ResponseEntity<Book> createTutorial(@RequestBody Book book) {
    try {
      Book _b = BookRepository
          .save(new Book(book.getTitle(), book.getDescription(), false, null));
      return new ResponseEntity<>(_b, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/Books/{id}")
  public ResponseEntity<Book> updateTutorial(@PathVariable("id") long id, @RequestBody Book book) {
    Optional<Book> BookData = BookRepository.findById(id);
    if (BookData.isPresent()) {
      Book _b = BookData.get();
      _b.setTitle(book.getTitle());
      _b.setDescription(book.getDescription());
      _b.setPublished(book.isPublished());
      return new ResponseEntity<>(BookRepository.save(_b), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/Books/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    try {
      BookRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @DeleteMapping("/Books")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      BookRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/Books/published")
  public ResponseEntity<List<Book>> findByPublished() {
    try {
      List<Book> books = BookRepository.findByPublished(true);
      if (books.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
