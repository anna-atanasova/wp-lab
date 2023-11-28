package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.BookStoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBookStoreService implements BookStoreService {
    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;

    public IBookStoreService(BookRepository bookRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;

        // Initialize book stores
        var books = bookRepository.findAll();
        var bookStores = bookStoreRepository.findAll();
        for (int i = 0; i < books.size() && i < bookStores.size(); ++i) {
            books.get(i).setBookStoreId(bookStores.get(i).getId());
        }
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }
}
