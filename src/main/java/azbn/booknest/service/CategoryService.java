package azbn.booknest.service;

import azbn.booknest.dto.CategoryResponse;
import azbn.booknest.dto.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();

    CategoryResponse findById(Long id);

    CategoryResponse create(CreateCategoryRequest request);

    void delete(Long id);
}