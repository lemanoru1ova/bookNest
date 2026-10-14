package azbn.booknest.repository;

import azbn.booknest.entity.BookAuthor;
import azbn.booknest.enums.AuthorRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {

    List<BookAuthor> findByBookId(Long bookId);

    List<BookAuthor> findByAuthorId(Long authorId);

    List<BookAuthor> findByBookIdAndRole(Long bookId, AuthorRole role);

    boolean existsByBookIdAndAuthorIdAndRole(Long bookId, Long authorId, AuthorRole role);

    void deleteByBookIdAndAuthorId(Long bookId, Long authorId);
}