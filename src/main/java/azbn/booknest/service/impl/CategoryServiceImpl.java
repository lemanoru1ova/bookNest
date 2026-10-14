package azbn.booknest.service.impl;

import azbn.booknest.dto.CreateCategoryRequest;
import azbn.booknest.dto.CategoryResponse;
import azbn.booknest.entity.Category;
import azbn.booknest.exception.CategoryNotFoundException;
import azbn.booknest.exception.DuplicateNameException;
import azbn.booknest.mapper.CategoryMapper;
import azbn.booknest.repository.CategoryRepository;
import azbn.booknest.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse create(CreateCategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new DuplicateNameException(
                    "Category already exists: " + request.name());
        }
        Category saved = categoryRepository.save(categoryMapper.toEntity(request));
        return categoryMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }
}