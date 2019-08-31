package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component        //runs code on application startup
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {

        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Fruits"),
                new Category("Dried"),
                new Category("Fresh"),
                new Category("Exotic"),
                new Category("Nuts")
        );

        categories.forEach(categoryRepository::save);

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

        customers.forEach(customerRepository::save);

        System.out.println("Loaded Customer Count: " + customerRepository.count());
    }

    private void loadVendors() {
        List<Vendor> vendors = Arrays.asList(
                new Vendor("Amazing Berry Farms"),
                new Vendor("Exotic Fruit Inc."),
                new Vendor("Fresh Fruit Founders"),
                new Vendor("Nutty Brothers Corp."),
                new Vendor("Dried Fruit Mart")
        );

        vendors.forEach(vendorRepository::save);

        System.out.println("Loaded Vendor Count: " + vendorRepository.count());
    }
}
