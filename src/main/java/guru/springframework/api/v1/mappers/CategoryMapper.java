package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDTO> {

    public CategoryDTO map(Category category) {
        if(category == null) {
            return null;
        }

        return new CategoryDTO(category.getId(), category.getName());
    }
}
