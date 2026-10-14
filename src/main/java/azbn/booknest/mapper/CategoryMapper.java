package azbn.booknest.mapper;

import azbn.booknest.dto.CreateCategoryRequest;
import azbn.booknest.dto.CategoryResponse;
import azbn.booknest.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }

    public Category toEntity(CreateCategoryRequest request) {
        return new Category(request.name());
    }
}