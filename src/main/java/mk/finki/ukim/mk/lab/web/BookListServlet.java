package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.dialect.SpringStandardDialect;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookListServlet {

    private final BookService bookService;

    public BookListServlet(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/listBooks")
    public String listBooks(Model model)
    {
        var books = bookService.listBooks();
        model.addAttribute("books", books);
        model.addAttribute("selectedBook", new Book("", "", "", 0, new ArrayList<>()));
        return "listBooks";
    }

    @PostMapping("/processSelectedBook")
    public String processSelectedBook(@RequestParam("selectedBook") String selectedBookIsbn, Model model) {
        Book selectedBook = bookService.findBookByIsbn(selectedBookIsbn);
        return "redirect:/listBooks";
    }
}