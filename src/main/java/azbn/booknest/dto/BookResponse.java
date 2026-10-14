package azbn.booknest.dto;

import azbn.booknest.dto.BookAuthorResponse;

import java.util.List;

public record BookResponse(
        Long id,
        String title,
        String isbn,
        Integer publishedYear,
        Integer availableCopies,
        Long categoryId,
        String categoryName,
        List<BookAuthorResponse> authors
) {
}