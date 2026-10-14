package azbn.booknest.mapper;

import azbn.booknest.dto.BookResponse;
import azbn.booknest.dto.CreateBookRequest;
import azbn.booknest.dto.BookAuthorResponse;
import azbn.booknest.entity.Book;
import azbn.booknest.entity.BookAuthor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {

    public BookResponse toResponse(Book book) {
        Long categoryId = book.getCategory() != null ? book.getCategory().getId() : null;
        String categoryName = book.getCategory() != null ? book.getCategory().getName() : null;

        List<BookAuthorResponse> authors = book.getBookAuthors().stream()
                .map(this::toBookAuthorResponse)
                .toList();

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublishedYear(),
                book.getAvailableCopies(),
                categoryId,
                categoryName,
                authors
        );
    }

    public Book toEntity(CreateBookRequest request) {
        return new Book(
                request.title(),
                request.isbn(),
                request.publishedYear(),
                request.initialCopies()
        );
    }

    private BookAuthorResponse toBookAuthorResponse(BookAuthor bookAuthor) {
        return new BookAuthorResponse(
                bookAuthor.getAuthor().getId(),
                bookAuthor.getAuthor().getName(),
                bookAuthor.getAuthor().getCountry(),
                bookAuthor.getRole()
        );
    }
}