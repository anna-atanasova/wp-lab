package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    String isbn;
    String title;
    String genre;
    int year;
    List<Author> authors;

    public Book(String isbn, String title, String genre, int year, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
    }
    public void addAuthor(Author a){
        authors.add(a);
    }
}
