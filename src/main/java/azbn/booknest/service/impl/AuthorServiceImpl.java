package azbn.booknest.service.impl;

import azbn.booknest.dto.CreateAuthorRequest;
import azbn.booknest.dto.AuthorResponse;
import azbn.booknest.entity.Author;
import azbn.booknest.exception.AuthorNotFoundException;
import azbn.booknest.mapper.AuthorMapper;
import azbn.booknest.repository.AuthorRepository;
import azbn.booknest.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorResponse> findAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    @Override
    public AuthorResponse findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        return authorMapper.toResponse(author);
    }

    @Override
    @Transactional
    public AuthorResponse create(CreateAuthorRequest request) {
        Author saved = authorRepository.save(authorMapper.toEntity(request));
        return authorMapper.toResponse(saved);
    }

    @Override
    public List<AuthorResponse> search(String keyword) {
        return authorRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }
        authorRepository.deleteById(id);
    }
}