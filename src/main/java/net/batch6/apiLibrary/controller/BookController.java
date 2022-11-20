package net.batch6.apiLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.batch6.apiLibrary.model.dto.BookDto;
import net.batch6.apiLibrary.model.dto.ResponseData;
import net.batch6.apiLibrary.service.BookService;




@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    private ResponseData<Object> responseData;

    @GetMapping
    public ResponseEntity<Object> getBooks() {
        responseData = bookService.getAll();
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> postBook(@RequestBody BookDto request) {
        responseData = bookService.insertBook(request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        responseData = bookService.getById(id);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable long id, @RequestBody BookDto request) {
        responseData = bookService.updateBook(id, request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable long id) {
        responseData = bookService.deleteBook(id);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}