package net.batch6.apiLibrary.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "books")
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 100)
    private String author;

    private boolean isDeleted = false;

    // many to one ke table category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // constructor
    public Book(String title, String author) {
    }
}