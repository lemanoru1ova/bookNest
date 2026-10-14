package azbn.booknest.controller;

import azbn.booknest.dto.CreateAuthorRequest;
import azbn.booknest.dto.AuthorResponse;
import azbn.booknest.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> create(
            @Valid @RequestBody CreateAuthorRequest request) {
        AuthorResponse created = authorService.create(request);
        return ResponseEntity
                .created(URI.create("/api/authors/" + created.id()))
                .body(created);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AuthorResponse>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(authorService.search(keyword));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}