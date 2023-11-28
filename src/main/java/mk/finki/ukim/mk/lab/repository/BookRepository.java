package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> books;

    public BookRepository(){
        books = new ArrayList<>();

        Book book1 = new Book("ISBN1", "Book 1", "Fiction", 2023, new ArrayList<>());
        books.add(book1);

        Book book2 = new Book("ISBN2", "Book 2", "Science", 2022, new ArrayList<>());
        books.add(book2);

        Book book3 = new Book("ISBN3", "Book 3", "Art", 2021, new ArrayList<>());
        books.add(book3);

        Book book4 = new Book("ISBN4", "Book 4", "Biology", 2022, new ArrayList<>());
        books.add(book4);

        Book book5 = new Book("ISBN5", "Book 5", "History", 2019, new ArrayList<>());
        books.add(book5);
    }

    public List<Book> findAll(){
        return books;
    }

    public Book findByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public void addAuthorToBook(Author author, Book book){
        book.addAuthor(author);
    }

    public void save(Book book) {
        var foundBook = findByIsbn(book.getIsbn());

        if (foundBook == null) {
            this.books.add(book);
            return;
        }

        foundBook.setIsbn(book.getIsbn());
        foundBook.setTitle(book.getTitle());
        foundBook.setGenre(book.getGenre());
        foundBook.setYear(book.getYear());
    }

    public void delete(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }
}
