package net.batch6.apiLibrary.service;

import net.batch6.apiLibrary.model.dto.BookDto;
import net.batch6.apiLibrary.model.dto.ResponseData;
import net.batch6.apiLibrary.model.entity.Book;
import net.batch6.apiLibrary.model.entity.Category;
import net.batch6.apiLibrary.repository.BookRepository;
import net.batch6.apiLibrary.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private ResponseData<Object> responseData;
    private Book book;
    private List<Book> books;
    private Category category;

    @Override
    public ResponseData<Object> insertBook(BookDto requesDto) {
        // TODO Auto-generated method stub
        book = new Book(requesDto.getTitle(), requesDto.getAuthor());
        category = categoryRepository.findByName(requesDto.getCategoryName());
        // set category
        book.setCategory(category);
        // save to db
        bookRepository.save(book);

        // response data
        responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Buku Berhasil Di simpan", book);
        return responseData;
    }

    @Override
    public ResponseData<Object> getAll() {
        // TODO Auto-generated method stub
        // find all book
        books = bookRepository.findAll();

        // response data
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", books);
        return responseData;
    }

    @Override
    public ResponseData<Object> getById(long id) {
        // TODO Auto-generated method stub
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            book = bookOpt.get();
            responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", book);
        } else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "Not Found", null);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> updateBook(long id, BookDto request) {
        // TODO Auto-generated method stub
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            book = bookOpt.get();

            // update buku
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            book.setCategory(category);

            // save
            bookRepository.save(book);

            // response data
            responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", book);
        } else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> deleteBook(long id) {
        // TODO Auto-generated method stub
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            book = bookOpt.get();

            // delete / change property isDeleted
            book.setDeleted(true);

            // save
            bookRepository.save(book);

            // response data
            responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", null);
        } else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
        }
        return responseData;
    }
}