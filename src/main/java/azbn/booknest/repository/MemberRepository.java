package azbn.booknest.repository;

import azbn.booknest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailIgnoreCase(String email);

    List<Member> findByFullNameContainingIgnoreCase(String keyword);

    List<Member> findByActiveTrue();

    boolean existsByEmailIgnoreCase(String email);
}