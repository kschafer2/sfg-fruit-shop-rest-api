package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.CategoryDto;
import guru.springframework.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    @Override
    public CategoryDto toDto(Category category) {
        if(category == null) {
            return null;
        }

        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    @Override
    public Category toDomain(CategoryDto categoryDto) {
        if(categoryDto == null) {
            return null;
        }

        return new Category(
                categoryDto.getId(),
                categoryDto.getName()
        );
    }
}
