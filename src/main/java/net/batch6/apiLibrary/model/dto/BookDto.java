package net.batch6.apiLibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private String title;
    private String author;
    private String categoryName;
}