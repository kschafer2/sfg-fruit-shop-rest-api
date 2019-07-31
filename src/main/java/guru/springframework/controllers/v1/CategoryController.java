package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.CategoryDto;
import guru.springframework.api.v1.model.CategoryListDto;
import guru.springframework.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getAllCategories() {
        log.info("Getting All Categories...");

        return new CategoryListDto(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryByName(@PathVariable String name) {
        log.info("Getting Category: " + name);

        return categoryService.getCategoryByName(name);
    }
}
