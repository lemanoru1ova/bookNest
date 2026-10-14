package azbn.booknest.repository;

import azbn.booknest.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {

    Optional<BookDetail> findByBookId(Long bookId);

    boolean existsByBookId(Long bookId);
}