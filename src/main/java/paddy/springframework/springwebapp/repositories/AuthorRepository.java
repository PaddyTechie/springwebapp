package paddy.springframework.springwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import paddy.springframework.springwebapp.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
