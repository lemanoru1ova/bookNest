package azbn.booknest.mapper;

import azbn.booknest.dto.CreateAuthorRequest;
import azbn.booknest.dto.AuthorResponse;
import azbn.booknest.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorResponse toResponse(Author author) {
        return new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getCountry()
        );
    }

    public Author toEntity(CreateAuthorRequest request) {
        return new Author(request.name(), request.country());
    }
}