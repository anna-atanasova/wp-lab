package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class АuthorServlet {
    private final AuthorService authorService;
    private final BookService bookService;

    public АuthorServlet(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/authorList")
    public String listAuthors(Model model, RedirectAttributes redirectAttributes) {
        String selectedBookIsbn = (String)model.getAttribute("selectedBookIsbn");

        var authors = authorService.listAuthors();
        var book = bookService.findBookByIsbn(selectedBookIsbn);
        model.addAttribute("authors", authors);
        model.addAttribute("book", book);

        return "authorList";
    }
}
