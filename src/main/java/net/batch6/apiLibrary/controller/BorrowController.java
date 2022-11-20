package net.batch6.apiLibrary.controller;

import net.batch6.apiLibrary.model.dto.BorrowDto;
import net.batch6.apiLibrary.model.dto.ResponseData;
import net.batch6.apiLibrary.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    private ResponseData<Object> responseData;

    @PostMapping("/book/{id}")
    public ResponseEntity<Object> postBook(@PathVariable long id, @RequestBody BorrowDto request) {
        responseData = borrowService.borrow(id,request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
    @PostMapping("/returned/{id}")
    public ResponseEntity<Object> returend(@PathVariable long id, @RequestBody BorrowDto request) {
        responseData = borrowService.returnedBook(id,request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
