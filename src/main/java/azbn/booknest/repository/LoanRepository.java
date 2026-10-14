package azbn.booknest.repository;

import azbn.booknest.entity.Loan;
import azbn.booknest.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByMemberId(Long memberId);

    List<Loan> findByMemberIdAndStatus(Long memberId, LoanStatus status);

    List<Loan> findByBookId(Long bookId);

    List<Loan> findByStatus(LoanStatus status);

    @Query("SELECT l FROM Loan l WHERE l.status = 'ACTIVE' AND l.dueDate < :today")
    List<Loan> findOverdueLoans(@Param("today") LocalDate today);

    long countByMemberIdAndStatus(Long memberId, LoanStatus status);
}