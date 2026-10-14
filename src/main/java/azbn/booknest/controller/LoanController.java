package azbn.booknest.controller;

import azbn.booknest.dto.CreateLoanRequest;
import azbn.booknest.dto.LoanResponse;
import azbn.booknest.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAll() {
        return ResponseEntity.ok(loanService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LoanResponse> create(
            @Valid @RequestBody CreateLoanRequest request) {
        LoanResponse created = loanService.create(request);
        return ResponseEntity
                .created(URI.create("/api/loans/" + created.id()))
                .body(created);
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<LoanResponse> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.returnBook(id));
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<LoanResponse>> getOverdue() {
        return ResponseEntity.ok(loanService.findOverdue());
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<LoanResponse>> getByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(loanService.findByMember(memberId));
    }
}