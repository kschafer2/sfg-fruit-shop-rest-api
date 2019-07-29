package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component        //runs code on application startup
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        ArrayList<Category> categories =
                new ArrayList<>(Arrays.asList(
                        new Category("Fruits"),
                        new Category("Dried"),
                        new Category("Fresh"),
                        new Category("Exotic"),
                        new Category("Nuts")
                ));

        for(Category category : categories) {
            categoryRepository.save(category);
        }

        System.out.println("Loaded Category Count: " + categoryRepository.count());
    }
}
