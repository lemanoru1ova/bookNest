package azbn.booknest.controller;

import azbn.booknest.dto.CreateMemberRequest;
import azbn.booknest.dto.MemberResponse;
import azbn.booknest.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(
            @Valid @RequestBody CreateMemberRequest request) {
        MemberResponse created = memberService.create(request);
        return ResponseEntity
                .created(URI.create("/api/members/" + created.id()))
                .body(created);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MemberResponse>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(memberService.search(keyword));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        memberService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}