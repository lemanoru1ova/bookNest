package azbn.booknest.service;

import azbn.booknest.dto.AuthorResponse;
import azbn.booknest.dto.CreateAuthorRequest;

import java.util.List;

public interface AuthorService {

    List<AuthorResponse> findAll();

    AuthorResponse findById(Long id);

    AuthorResponse create(CreateAuthorRequest request);

    List<AuthorResponse> search(String keyword);

    void delete(Long id);
}