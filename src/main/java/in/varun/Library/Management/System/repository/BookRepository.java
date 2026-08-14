package in.varun.Library.Management.System.repository;

import in.varun.Library.Management.System.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book , Integer> {
    List<Book> findByIsDeletedFalse();
    Optional<Book> findByIdAndIsDeletedFalse(Integer id);
    Boolean existsByIsbnAndIsDeletedFalse(String isbn);
}
