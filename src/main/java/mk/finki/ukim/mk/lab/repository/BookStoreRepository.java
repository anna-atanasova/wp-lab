package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookStoreRepository {
    private List<BookStore> bookStores;

    public BookStoreRepository() {
        bookStores = new ArrayList<>();

        bookStores.add(new BookStore("Book Warehouse", "Skopje", "Orce Nikolov"));
        bookStores.add(new BookStore("Book Garage", "Skopje", "Kapistec"));
        bookStores.add(new BookStore("Store of Books", "Skopje", "Nikola Tesla"));
        bookStores.add(new BookStore("Book House", "New York", "Times Square"));
        bookStores.add(new BookStore("Book Buy", "London", "South London"));
    }

    public List<BookStore> findAll(){
        return bookStores;
    }
}
