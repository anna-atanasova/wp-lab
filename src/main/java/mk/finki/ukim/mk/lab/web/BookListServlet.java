package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring6.dialect.SpringStandardDialect;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookListServlet {
    private final BookService bookService;

    public BookListServlet(BookService bookService) {
        this.bookService = bookService;
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
}