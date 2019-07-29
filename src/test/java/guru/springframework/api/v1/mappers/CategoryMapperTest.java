package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.CategoryDto;
import guru.springframework.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "name";
    public static final Long ID = 1L;
    CategoryMapper categoryMapper = new CategoryMapper();

    @Test
    public void toDtoTest() throws Exception {
        //given
        Category category = new Category(ID, NAME);

        //when
        CategoryDto categoryDto = categoryMapper.toDto(category);

        //then
        assertEquals(ID, categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());

    }

    @Test
    public void toDomainTest() throws Exception {
        //given
        CategoryDto categoryDto = new CategoryDto(ID, NAME);

        //when
        Category category = categoryMapper.toDomain(categoryDto);

        //then
        assertEquals(ID, category.getId());
        assertEquals(NAME, category.getName());
    }
}