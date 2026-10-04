package azbn.booknest.service;

import azbn.booknest.dto.BookResponse;
import azbn.booknest.dto.CreateBookRequest;
import azbn.booknest.entity.Book;
import azbn.booknest.exception.BookNotFoundException;
import azbn.booknest.exception.DuplicateIsbnException;
import azbn.booknest.mapper.BookMapper;
import azbn.booknest.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookResponse> findAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    public BookResponse findBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toResponse(book);
    }

    public BookResponse create(CreateBookRequest request) {
        if (bookRepository.existsByIsbn(request.isbn())) {
            throw new DuplicateIsbnException(request.isbn());
        }
        Book saved = bookRepository.save(bookMapper.toEntity(request));
        return bookMapper.toResponse(saved);
    }

    public List<BookResponse> search(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword).stream()
                .map(bookMapper::toResponse)
                .toList();
    }
}
