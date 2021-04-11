package paddy.springframework.springwebapp.boostrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import paddy.springframework.springwebapp.domain.Author;
import paddy.springframework.springwebapp.domain.Book;
import paddy.springframework.springwebapp.domain.Publisher;
import paddy.springframework.springwebapp.repositories.AuthorRepository;
import paddy.springframework.springwebapp.repositories.BookRepository;
import paddy.springframework.springwebapp.repositories.PublisherRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(final AuthorRepository authorRepository, final BookRepository bookRepository, final PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        setAuthorAndBook("Kathy", "Sierra", "Head First Java", "8979172");
        setAuthorAndBook("Raoul-Gabriel", "Urma", "Modern Java in Action", "1617293563");
        final Set<Book> books = new HashSet<>();
        bookRepository.findAll().forEach(book -> books.add(book));

        final Publisher publisher = getPublisher("ABC Publisher", "some address", "Some city", "Some state", 1212121, books);
        books.forEach(book -> book.setPublisher(publisher));
        publisherRepository.save(publisher);

        System.out.println("total books in the repo : " + bookRepository.count());
        System.out.println("publisher count : " + publisherRepository.count());
        System.out.printf("Publisher " + publisher.getName() + " did pushlish a total of " + publisher.getBooks().size() + " Books");
    }

    private void setAuthorAndBook(final String authorFirstName, final String authorLastName,
                                  final String bookName, final String bookIsbn) {
        Author author = new Author(authorFirstName, authorLastName);
        Book book = new Book(bookName, bookIsbn);
        author.getBooks().add(book);
        book.getAuthors().add(author);
        authorRepository.save(author);
        bookRepository.save(book);
    }

    private Publisher getPublisher(final String name, final String address, final String city, final String state,
                                   final int zipCode, final Set<Book> books) {
        final Publisher publisher = new Publisher(name, address, city, state, zipCode);
        publisher.setBooks(books);
        return publisher;
    }
}
