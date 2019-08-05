package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.api.v1.model.CustomerListDto;
import guru.springframework.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is the Customer API")
@Slf4j
@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "View a list of all customers")
    @GetMapping
    public ResponseEntity<CustomerListDto> getAllCustomers() {
        log.info("Getting All Customers...");

        return new ResponseEntity<>(
                new CustomerListDto(customerService.getAllCustomers()),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "View a specific customer")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        log.info("Getting Customer: " + id);

        return new ResponseEntity<>(
                customerService.getCustomerById(id),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "Create a new customer")
    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CustomerDto customerDto) {
        log.info("Creating Customer: " + customerDto.getFirstName() + " " + customerDto.getLastName());

        return new ResponseEntity<>(
                customerService.createNewCustomer(customerDto),
                HttpStatus.CREATED
        );
    }

    @ApiOperation(value = "Overwrite an existing customer")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> overwriteCustomer(@PathVariable Long id,
                                                         @RequestBody CustomerDto customerDto) {
        log.info("Overwriting Customer: " + id);

        return new ResponseEntity<>(
                customerService.overwriteCustomer(id, customerDto),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "Update an existing customer's properties")
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id,
                                                      @RequestBody CustomerDto customerDto) {
        log.info("Patching Customer: " + id);

        return new ResponseEntity<>(
                customerService.patchCustomer(id, customerDto),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "Delete a customer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.info("Deleting Customer: " + id);

        customerService.deleteCustomerById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
