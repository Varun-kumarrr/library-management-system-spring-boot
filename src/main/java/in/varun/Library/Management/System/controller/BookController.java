package in.varun.Library.Management.System.controller;

import in.varun.Library.Management.System.entity.Book;
import in.varun.Library.Management.System.service.BookService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(201).body(createdBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> allBook = bookService.getAllBook();

        return ResponseEntity
                .ok(allBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id)
    {
        Book reqBook = bookService.getBookById(id);

        if(reqBook == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reqBook);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id , @RequestBody Book book)
    {
        Book updatedBook = bookService.updateBook(id , book);

        if(updatedBook == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity
                .ok(updatedBook);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id)
    {
        boolean isDeleted = bookService.deleteBook(id);
        if(!isDeleted)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Id Not Found");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Deleted Successfully");

    }

}
