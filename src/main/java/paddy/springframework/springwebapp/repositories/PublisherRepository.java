package paddy.springframework.springwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import paddy.springframework.springwebapp.domain.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
