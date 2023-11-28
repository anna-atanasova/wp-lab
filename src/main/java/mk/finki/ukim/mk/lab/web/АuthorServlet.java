package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class АuthorServlet {
    private final AuthorService authorService;
    private final BookService bookService;

    private Book selectedBook;

    public АuthorServlet(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/authorList")
    public String listAuthors(Model model, RedirectAttributes redirectAttributes) {
        String selectedBookIsbn = (String)model.getAttribute("selectedBookIsbn");

        var authors = authorService.listAuthors();
        selectedBook = bookService.findBookByIsbn(selectedBookIsbn);
        model.addAttribute("authors", authors);
        model.addAttribute("book", selectedBook);

        return "authorList";
    }

    @PostMapping("/processSelectedAuthor")
    public String processSelectedAuthor(@RequestParam("selectedAuthor") Long selectedAuthorId, Model model, RedirectAttributes redirectAttributes) {
        var author = authorService.findById(selectedAuthorId);
        selectedBook.addAuthor(author);
        redirectAttributes.addFlashAttribute("selectedBookIsbn", selectedBook.getIsbn());
        return "redirect:/bookDetails";
    }
}
