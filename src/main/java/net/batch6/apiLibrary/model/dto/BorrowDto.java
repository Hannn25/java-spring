package net.batch6.apiLibrary.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowDto {
    private String bookName;
    private Date returnd;


}
