package guru.springframework.services;

import guru.springframework.api.v1.mappers.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper,
                               CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDto customerDto = customerMapper.toDto(customer);
                    customerDto.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDto;
                })
                .collect(Collectors.toList());

    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(RuntimeException::new); //todo implement better exception handling
    }
}
