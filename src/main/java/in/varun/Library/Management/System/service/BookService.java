package in.varun.Library.Management.System.service;

import in.varun.Library.Management.System.dto.request.CreateBookRequestDto;
import in.varun.Library.Management.System.dto.request.UpdateBookRequestDto;
import in.varun.Library.Management.System.dto.response.BookResponseDto;
import in.varun.Library.Management.System.entity.Book;
import in.varun.Library.Management.System.exception.DuplicateResourceException;
import in.varun.Library.Management.System.exception.ResourceNotFoundException;
import in.varun.Library.Management.System.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        Boolean exist = bookRepository.existsByIsbnAndIsDeletedFalse(bookReq.getIsbn());
        if(exist)
        {
            throw new DuplicateResourceException("ISBN "+bookReq.getIsbn()+" already exist in DataBase");
        }
        Book savedBook = bookRepository.save(mapCreateDtoToEntity(bookReq));

        return mapToResponseDto(savedBook);

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
        Book resBook = bookRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book with id " + id + " not found"));

        return mapToResponseDto(resBook);

    }

    public BookResponseDto updateBook(Integer id, UpdateBookRequestDto bookReq)
    {
        Book resBook = bookRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Book with id " + id + " not found"));

        resBook.setTitle(bookReq.getTitle());
        resBook.setPublisher(bookReq.getPublisher());
        resBook.setCategory(bookReq.getCategory());
        resBook.setDescription(bookReq.getDescription());
        resBook.setAuthor(bookReq.getAuthor());
        resBook.setPrice(bookReq.getPrice());
        resBook.setUpdatedAt(LocalDateTime.now());

        Book updatedBook = bookRepository.save(resBook);

        return mapToResponseDto(updatedBook);
    }

    public void deleteBook(Integer id)
    {
        Book resBook = bookRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Book with id " + id + " not found"));

        resBook.setUpdatedAt(LocalDateTime.now());
        resBook.setDeleted(true);
        bookRepository.save(resBook);
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
