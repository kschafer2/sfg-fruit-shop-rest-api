package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "name";
    public static final long ID = 1L;
    categoryMapper categoryMapper = new categoryMapper();

    @Test
    public void categoryToCategoryDTO() {
        //given
        Category category = new Category(ID, NAME);

        //when
        CategoryDTO categoryDTO = categoryMapper.map(category);

        //then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());

    }
}