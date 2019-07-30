package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.api.v1.model.CustomerListDto;
import guru.springframework.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDto> getAllCustomers() {
        log.info("Getting All Customers...");

        return new ResponseEntity<>(
                new CustomerListDto(customerService.getAllCustomers()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        log.info("Getting Customer: " + id);

        return new ResponseEntity<>(
                customerService.getCustomerById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CustomerDto customerDto) {
        log.info("Creating Customer: " + customerDto.getFirstName() + " " + customerDto.getLastName());

        return new ResponseEntity<>(
                customerService.createNewCustomer(customerDto),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> overwriteCustomer(@PathVariable Long id,
                                                         @RequestBody CustomerDto customerDto) {
        log.info("Overwriting Customer: " + id);

        return new ResponseEntity<>(
                customerService.overwriteCustomer(id, customerDto),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id,
                                                      @RequestBody CustomerDto customerDto) {
        log.info("Patching Customer: " + id);

        return new ResponseEntity<>(
                customerService.patchCustomer(id, customerDto),
                HttpStatus.OK
        );
    }
}
