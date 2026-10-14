package azbn.booknest.repository;

import azbn.booknest.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNameIgnoreCase(String name);

    List<Author> findByNameContainingIgnoreCase(String keyword);

    List<Author> findByCountryIgnoreCase(String country);

    boolean existsByNameIgnoreCase(String name);
}