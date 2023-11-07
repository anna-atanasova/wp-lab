package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IAuthorService implements AuthorService{
    private final AuthorRepository authorRepository;

    public IAuthorService(AuthorRepository ar){
        authorRepository = ar;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty()){
            throw new RuntimeException("The author does not exist");
        }
        return authorOptional.get();
    }
}
