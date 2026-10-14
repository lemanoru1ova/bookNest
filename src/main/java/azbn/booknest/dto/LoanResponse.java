package azbn.booknest.dto;

import azbn.booknest.enums.LoanStatus;

import java.time.LocalDate;

public record LoanResponse(
        Long id,
        Long bookId,
        String bookTitle,
        Long memberId,
        String memberName,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnDate,
        LoanStatus status
) {
}