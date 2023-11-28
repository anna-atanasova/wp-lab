package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public class BookStoreServlet {
    private final BookStoreService bookStoreService;

    public BookStoreServlet(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }
}
