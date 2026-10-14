package azbn.booknest.mapper;

import azbn.booknest.dto.LoanResponse;
import azbn.booknest.entity.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanResponse toResponse(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                loan.getBook().getId(),
                loan.getBook().getTitle(),
                loan.getMember().getId(),
                loan.getMember().getFullName(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStatus()
        );
    }
}