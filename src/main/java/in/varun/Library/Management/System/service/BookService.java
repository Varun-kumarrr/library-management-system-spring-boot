package in.varun.Library.Management.System.service;

import in.varun.Library.Management.System.dto.request.CreateBookRequestDto;
import in.varun.Library.Management.System.dto.request.UpdateBookRequestDto;
import in.varun.Library.Management.System.dto.response.BookResponseDto;
import in.varun.Library.Management.System.entity.Book;
import in.varun.Library.Management.System.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public BookResponseDto createBook(CreateBookRequestDto bookReq)
    {
        Book savedBook = bookRepository.save(mapCreateDtoToEntity(bookReq));
        BookResponseDto bookResp = mapToResponseDto(savedBook);
        return bookResp;

    }

    public List<BookResponseDto> getAllBook()
    {
        List<Book> allBook = bookRepository.findByIsDeletedFalse();
        List<BookResponseDto> allBookResp = new ArrayList<>();
        for (Book book : allBook) {
            allBookResp.add(mapToResponseDto(book));
        }
        return allBookResp;

    }

    public BookResponseDto getBookById(Integer id)
    {
        Optional<Book> resBook = bookRepository.findByIdAndIsDeletedFalse(id);
        if(resBook.isEmpty())
        {
            return null;
        }
        BookResponseDto bookResp = mapToResponseDto(resBook.get());
        return bookResp;

    }

    public BookResponseDto updateBook(Integer id, UpdateBookRequestDto bookReq)
    {
        Optional<Book> resBook = bookRepository.findByIdAndIsDeletedFalse(id);
        if(resBook.isEmpty())
        {
            return null;
        }

        Book book = resBook.get();
        book.setTitle(bookReq.getTitle());
        book.setPublisher(bookReq.getPublisher());
        book.setCategory(bookReq.getCategory());
        book.setDescription(bookReq.getDescription());
        book.setAuthor(bookReq.getAuthor());
        book.setPrice(bookReq.getPrice());
        book.setUpdatedAt(LocalDateTime.now());

        Book updatedBook = bookRepository.save(book);

        return mapToResponseDto(updatedBook);
    }

    public boolean deleteBook(Integer id)
    {
        Optional<Book> resBook = bookRepository.findByIdAndIsDeletedFalse(id);

        if (resBook.isEmpty())
        {
            return false;
        }

        Book deleteBook = resBook.get();
        deleteBook.setUpdatedAt(LocalDateTime.now());
        deleteBook.setDeleted(true);
        bookRepository.save(deleteBook);

        return true;

    }

    private Book mapCreateDtoToEntity(CreateBookRequestDto bookReq)
    {
        Book book = new Book();

        book.setDeleted(false);
        book.setTotalCopies(bookReq.getTotalCopies());
        book.setAvailableCopies(book.getTotalCopies());
        book.setStatus(book.getAvailableCopies()>0?AVAILABLE:OUT_OF_STOCK);
        book.setTitle(bookReq.getTitle());
        book.setIsbn(bookReq.getIsbn());
        book.setPublisher(bookReq.getPublisher());
        book.setDescription(bookReq.getDescription());
        book.setCategory(bookReq.getCategory());
        book.setAuthor(bookReq.getAuthor());
        book.setPrice(bookReq.getPrice());
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());

        return book;
    }

    private BookResponseDto mapToResponseDto(Book book)
    {
        BookResponseDto bookResp = new BookResponseDto();

        bookResp.setId(book.getId());
        bookResp.setAuthor(book.getAuthor());
        bookResp.setAvailableCopies(book.getAvailableCopies());
        bookResp.setCategory(book.getCategory());
        bookResp.setDescription(book.getDescription());
        bookResp.setIsbn(book.getIsbn());
        bookResp.setStatus(book.getStatus());
        bookResp.setTitle(book.getTitle());
        bookResp.setPrice(book.getPrice());
        bookResp.setTotalCopies(book.getTotalCopies());
        bookResp.setPublisher(book.getPublisher());
        bookResp.setCreatedAt(book.getCreatedAt());
        bookResp.setUpdatedAt(book.getUpdatedAt());

        return bookResp;

    }
}
