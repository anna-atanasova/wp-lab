package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookListServlet {
    private final BookService bookService;

    public BookListServlet(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/listBooks")
    public String listBooks(Model model) {
        List<Book> books = bookService.listBooks();
        model.addAttribute("books", books);
        model.addAttribute("selectedIsbn", "");

        return "listBooks";
    }

    @PostMapping("/selectBook")
    public String selectBook(@RequestParam("selectedIsbn") String isbn, Model model) {
        System.out.print(isbn); // You can access the selected ISBN directly
        // Do something with the selected ISBN
        return "redirect:/authorList";
    }
}