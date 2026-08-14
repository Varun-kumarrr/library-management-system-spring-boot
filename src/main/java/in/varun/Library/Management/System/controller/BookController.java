package in.varun.Library.Management.System.controller;

import in.varun.Library.Management.System.dto.request.CreateBookRequestDto;
import in.varun.Library.Management.System.dto.request.UpdateBookRequestDto;
import in.varun.Library.Management.System.dto.response.BookResponseDto;
import in.varun.Library.Management.System.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody CreateBookRequestDto book)
    {
        BookResponseDto createdBook = bookService.createBook(book);
        return ResponseEntity
                .status(201)
                .body(createdBook);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks()
    {
        List<BookResponseDto> allBook = bookService.getAllBook();

        return ResponseEntity
                .ok(allBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Integer id)
    {
        BookResponseDto reqBook = bookService.getBookById(id);

        return ResponseEntity
                .ok(reqBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Integer id ,@Valid @RequestBody UpdateBookRequestDto book)
    {
        BookResponseDto updatedBook = bookService.updateBook(id , book);

        return ResponseEntity
                .ok(updatedBook);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id)
    {
        bookService.deleteBook(id);

        return ResponseEntity
                .ok("Deleted Successfully");
    }

}
