package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class BookListServlet {
    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookListServlet(BookService bookService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    private Book book;

    @GetMapping("/listBooks")
    public String listBooks(Model model)
    {
        var books = bookService.listBooks();
        model.addAttribute("books", books);
        model.addAttribute("selectedBook", new Book("", "", "", 0, new ArrayList<>()));
        return "listBooks";
    }

    @PostMapping("/processSelectedBook")
    public String processSelectedBook(@RequestParam("selectedBook") String selectedBookIsbn, RedirectAttributes redirectAttributes) {
        Book selectedBook = bookService.findBookByIsbn(selectedBookIsbn);
        redirectAttributes.addFlashAttribute("selectedBookIsbn", selectedBookIsbn);
        return "redirect:/authorList";
    }

    @GetMapping("books/add")
    public String listSaveBookForm(Model model) {
        book = new Book("", "", "", 0, new ArrayList<Author>());
        model.addAttribute("book", book);
        var bookStores = bookStoreService.findAll();
        model.addAttribute("bookStores", bookStores);
        return "addBook";
    }

    @PostMapping("/processNewBook")
    public String saveBook(@ModelAttribute("book") Book book, Model model) {
        bookService.save(book);
        return "redirect:/listBooks";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam(value = "delete") String isbn) {
        bookService.delete(isbn);
        return "redirect:/listBooks";
    }

    @PostMapping("/editBook")
    public String editBook(@RequestParam(value="edit") String isbn, Model model) {
        book = bookService.findBookByIsbn(isbn);
        model.addAttribute("book", book);
        var bookStores = bookStoreService.findAll();
        model.addAttribute("bookStores", bookStores);
        return "addBook";
    }
}