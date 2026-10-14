package azbn.booknest.dto;

public record AuthorResponse(
        Long id,
        String name,
        String country
) {
}