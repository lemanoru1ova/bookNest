package azbn.booknest.service;

import azbn.booknest.dto.BookResponse;
import azbn.booknest.dto.CreateBookRequest;

import java.util.List;

public interface BookService {

    List<BookResponse> findAllBooks();

    BookResponse findBookById(Long id);

    BookResponse create(CreateBookRequest request);

    List<BookResponse> search(String keyword);
}