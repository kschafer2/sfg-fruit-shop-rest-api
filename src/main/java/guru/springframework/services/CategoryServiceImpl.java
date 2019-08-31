package guru.springframework.services;

import guru.springframework.api.v1.mappers.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDto;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryRepository
                .findByName(name)
                .map(categoryMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
