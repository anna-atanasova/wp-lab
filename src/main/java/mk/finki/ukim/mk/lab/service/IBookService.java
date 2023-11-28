package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBookService implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public IBookService(BookRepository br, AuthorRepository ar){
        bookRepository = br;
        authorRepository = ar;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(authorOptional.isEmpty()){
            throw new RuntimeException("The author does not exist");
        }
        Author a = authorOptional.get();
        Book b = bookRepository.findByIsbn(isbn);
        if(b == null) {
          throw new RuntimeException("The book does not exist");
        }
        bookRepository.addAuthorToBook(a, b);
        return a;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }
}
