package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component        //runs code on application startup
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();

    }

    private void loadCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Fruits"),
                new Category("Dried"),
                new Category("Fresh"),
                new Category("Exotic"),
                new Category("Nuts")
        );

        for(Category category : categories) {
            categoryRepository.save(category);
        }

        System.out.println("Loaded Category Count: " + categoryRepository.count());
    }

    private void loadCustomers() {
        List<Customer> customers = Arrays.asList(
                new Customer("Evan", "Johnson"),
                new Customer("Barack", "Obama"),
                new Customer("Hillary", "Clinton"),
                new Customer("Donald", "Duck"),
                new Customer("Mickey", "Mouse")
        );

        for(Customer customer : customers) {
            customerRepository.save(customer);
        }

        System.out.println("Loaded Customer Count: " + customerRepository.count());
    }
}
