package azbn.booknest.service.impl;

import azbn.booknest.dto.CreateLoanRequest;
import azbn.booknest.dto.LoanResponse;
import azbn.booknest.entity.Book;
import azbn.booknest.entity.Loan;
import azbn.booknest.entity.Member;
import azbn.booknest.enums.LoanStatus;
import azbn.booknest.exception.BookNotAvailableException;
import azbn.booknest.exception.BookNotFoundException;
import azbn.booknest.exception.LoanNotFoundException;
import azbn.booknest.exception.MemberNotFoundException;
import azbn.booknest.mapper.LoanMapper;
import azbn.booknest.repository.BookRepository;
import azbn.booknest.repository.LoanRepository;
import azbn.booknest.repository.MemberRepository;
import azbn.booknest.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LoanServiceImpl implements LoanService {

    private static final int DEFAULT_LOAN_DAYS = 14;
    private static final int MAX_ACTIVE_LOANS_PER_MEMBER = 3;

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanMapper loanMapper;

    public LoanServiceImpl(LoanRepository loanRepository,
                           BookRepository bookRepository,
                           MemberRepository memberRepository,
                           LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanMapper = loanMapper;
    }

    @Override
    public List<LoanResponse> findAll() {
        return loanRepository.findAll().stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    @Override
    public LoanResponse findById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
        return loanMapper.toResponse(loan);
    }

    @Override
    @Transactional
    public LoanResponse create(CreateLoanRequest request) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new BookNotFoundException(request.bookId()));

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new MemberNotFoundException(request.memberId()));

        if (!Boolean.TRUE.equals(member.getActive())) {
            throw new BookNotAvailableException("Member is not active");
        }

        if (book.getAvailableCopies() <= 0) {
            throw new BookNotAvailableException(
                    "No copies available for book: " + book.getTitle());
        }

        long activeLoans = loanRepository.countByMemberIdAndStatus(
                member.getId(), LoanStatus.ACTIVE);

        if (activeLoans >= MAX_ACTIVE_LOANS_PER_MEMBER) {
            throw new BookNotAvailableException(
                    "Member already has " + MAX_ACTIVE_LOANS_PER_MEMBER + " active loans");
        }

        int days = request.loanDays() != null ? request.loanDays() : DEFAULT_LOAN_DAYS;
        LocalDate today = LocalDate.now();

        Loan loan = new Loan(book, member, today, today.plusDays(days));
        member.addLoan(loan);

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        Loan saved = loanRepository.save(loan);
        return loanMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public LoanResponse returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new BookNotAvailableException("Loan already returned");
        }

        loan.markReturned();

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        return loanMapper.toResponse(loan);
    }

    @Override
    public List<LoanResponse> findOverdue() {
        return loanRepository.findOverdueLoans(LocalDate.now()).stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    @Override
    public List<LoanResponse> findByMember(Long memberId) {
        return loanRepository.findByMemberId(memberId).stream()
                .map(loanMapper::toResponse)
                .toList();
    }
}