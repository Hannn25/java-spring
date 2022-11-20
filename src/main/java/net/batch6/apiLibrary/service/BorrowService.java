package net.batch6.apiLibrary.service;

import net.batch6.apiLibrary.model.dto.BorrowDto;
import net.batch6.apiLibrary.model.dto.ResponseData;


public interface BorrowService {
    ResponseData<Object> borrow(long id,BorrowDto request);

    ResponseData<Object> returnedBook(long id,BorrowDto request);
}
