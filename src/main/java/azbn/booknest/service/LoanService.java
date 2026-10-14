package azbn.booknest.service;

import azbn.booknest.dto.CreateLoanRequest;
import azbn.booknest.dto.LoanResponse;

import java.util.List;

public interface LoanService {

    List<LoanResponse> findAll();

    LoanResponse findById(Long id);

    LoanResponse create(CreateLoanRequest request);

    LoanResponse returnBook(Long loanId);

    List<LoanResponse> findOverdue();

    List<LoanResponse> findByMember(Long memberId);
}