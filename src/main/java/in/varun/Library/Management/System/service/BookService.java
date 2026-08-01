package in.varun.Library.Management.System.service;

import in.varun.Library.Management.System.entity.Book;
import in.varun.Library.Management.System.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static in.varun.Library.Management.System.entity.Status.AVAILABLE;
import static in.varun.Library.Management.System.entity.Status.OUT_OF_STOCK;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book)
    {
        book.setDeleted(false);
        book.setAvailableCopies(book.getTotalCopies());
        book.setStatus(book.getAvailableCopies()>0?AVAILABLE:OUT_OF_STOCK);
        Book savedBook = bookRepository.save(book);
        return savedBook;

    }

    public List<Book> getAllBook()
    {
        List<Book> allBook = bookRepository.findByDeletedIsFalse();
        return allBook;

    }

    public Book getBookById(Integer id)
    {
        Optional<Book> resBook = bookRepository.findByIdAndDeletedIsFalse(id);

        if(resBook.isPresent())
        {
            return resBook.get();
        }
        return null;

    }

    public Book updateBook(Integer id, Book book)
    {
        Optional<Book> resBook = bookRepository.findByIdAndDeletedIsFalse(id);
        if(resBook.isEmpty())
        {
            return null;
        }

        Book updateBook = resBook.get();
        updateBook.setPrice(book.getPrice());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setCategory(book.getCategory());
        updateBook.setDescription(book.getDescription());
        updateBook.setIsbn(book.getIsbn());
        updateBook.setPublisher(book.getPublisher());
        updateBook.setTitle(book.getTitle());
        updateBook.setTotalCopies(book.getTotalCopies());
        updateBook.setAvailableCopies(book.getTotalCopies());
        updateBook.setStatus(updateBook.getAvailableCopies()>0?AVAILABLE:OUT_OF_STOCK);

        return bookRepository.save(updateBook);

    }

    public boolean deleteBook(Integer id)
    {
        Optional<Book> resBook = bookRepository.findByIdAndDeletedIsFalse(id);

        if (resBook.isEmpty())
        {
            return false;
        }

        Book deleteBook = resBook.get();
        deleteBook.setDeleted(true);
        bookRepository.save(deleteBook);

        return true;

    }
}
