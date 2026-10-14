package azbn.booknest.service.impl;

import azbn.booknest.dto.BookResponse;
import azbn.booknest.dto.CreateBookRequest;
import azbn.booknest.entity.Book;
import azbn.booknest.exception.BookNotFoundException;
import azbn.booknest.exception.DuplicateIsbnException;
import azbn.booknest.mapper.BookMapper;
import azbn.booknest.repository.BookRepository;
import azbn.booknest.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookResponse> findAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    public BookResponse findBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toResponse(book);
    }

    @Override
    @Transactional
    public BookResponse create(CreateBookRequest request) {
        if (bookRepository.existsByIsbn(request.isbn())) {
            throw new DuplicateIsbnException(request.isbn());
        }
        Book saved = bookRepository.save(bookMapper.toEntity(request));
        return bookMapper.toResponse(saved);
    }

    @Override
    public List<BookResponse> search(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword).stream()
                .map(bookMapper::toResponse)
                .toList();
    }
}