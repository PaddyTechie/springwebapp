package paddy.springframework.springwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import paddy.springframework.springwebapp.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
