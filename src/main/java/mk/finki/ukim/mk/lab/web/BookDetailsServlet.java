package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookDetailsServlet {
    private final AuthorService authorService;
    private final BookService bookService;

    public BookDetailsServlet(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/bookDetails")
    public String listAuthors(Model model, RedirectAttributes redirectAttributes) {
        String selectedBookIsbn = (String) model.getAttribute("selectedBookIsbn");

        var book = bookService.findBookByIsbn(selectedBookIsbn);

        model.addAttribute("book", book);
        model.addAttribute("authors", book.getAuthors());

        return "bookDetails";
    }
}
