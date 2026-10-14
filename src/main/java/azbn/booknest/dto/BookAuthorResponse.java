package azbn.booknest.dto;

import azbn.booknest.enums.AuthorRole;

public record BookAuthorResponse(
        Long authorId,
        String authorName,
        String country,
        AuthorRole role
) {
}