package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    List<Author> authors;

    public AuthorRepository(){
        authors = new ArrayList<>();

        authors.add(new Author(1L, "John", "Doe", "Biography of John Doe"));
        authors.add(new Author(2L, "Jane", "Smith", "Biography of Jane Smith"));
        authors.add(new Author(3L, "Alice", "Johnson", "Biography of Alice Johnson"));
        authors.add(new Author(4L, "Bob", "Brown", "Biography of Bob Brown"));
        authors.add(new Author(5L, "Eve", "Williams", "Biography of Eve Williams"));
    }

    public List<Author> findAll(){
        return authors;
    }

    public Optional<Author> findById(Long id){
        return authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }
}
