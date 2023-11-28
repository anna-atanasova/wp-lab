package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    private List<Author> authors;

    public AuthorRepository(){
        authors = new ArrayList<>();

        authors.add(new Author("John", "Doe", "Biography of John Doe"));
        authors.add(new Author("Jane", "Smith", "Biography of Jane Smith"));
        authors.add(new Author("Alice", "Johnson", "Biography of Alice Johnson"));
        authors.add(new Author("Bob", "Brown", "Biography of Bob Brown"));
        authors.add(new Author("Eve", "Williams", "Biography of Eve Williams"));
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
