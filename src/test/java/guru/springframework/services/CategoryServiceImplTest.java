package guru.springframework.services;

import guru.springframework.api.v1.mappers.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDto;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "name";
    private CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(
                        new CategoryMapper(), categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception {
        //given
        List<Category> categories = Arrays.asList(
                new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();

        //then
        assertEquals(2, categoryDtos.size());
    }

    @Test
    public void getCategoryByName() throws Exception {
        //given
        Category category = new Category(ID, NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDto categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}