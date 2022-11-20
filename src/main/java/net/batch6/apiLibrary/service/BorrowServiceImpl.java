package net.batch6.apiLibrary.service;


import net.batch6.apiLibrary.model.dto.BorrowDto;
import net.batch6.apiLibrary.model.dto.ResponseData;
import net.batch6.apiLibrary.model.entity.Book;
import net.batch6.apiLibrary.model.entity.Borrow;
import net.batch6.apiLibrary.model.entity.User;
import net.batch6.apiLibrary.repository.BookRepository;
import net.batch6.apiLibrary.repository.BorrowRepository;
import net.batch6.apiLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    private ResponseData<Object> responseData;
    private Book book;
    private User user;
    private Borrow borrow;
    private Map<Object,Object> data;




    public ResponseData<Object> borrow(long id, BorrowDto request) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            borrow = new Borrow();
            book = bookRepository.findByTitle(request.getBookName());
            borrow.setUser(user);
            borrow.setBook(book);
            borrowRepository.save(borrow);
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Buku Berhasil Di Pinjam", borrow);

        }else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "user tidak ada", borrow);
        }
        return responseData;
    }



    @Override
    public ResponseData<Object> returnedBook(long id, BorrowDto request) {
        Optional<Borrow> borrowOpt = borrowRepository.findById(id);

        if (borrowOpt.isPresent()) {
            borrow = borrowOpt.get();
            borrow.setReturn_date(request.getReturnd());
            // update namenya
            borrow.setBorrowed(false);
            // save
            borrowRepository.save(borrow);

            // response data
            responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Buku Di Kembalikan", borrow);
        } else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "data not found", null);


        }
        return responseData;
    }


}