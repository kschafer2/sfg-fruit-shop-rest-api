package guru.springframework.services;

import guru.springframework.api.v1.mappers.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(RuntimeException::new);
                //todo implement better exception handling
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {
        return saveAndReturnDto(customerMapper.toDomain(customerDto));
    }

    @Override
    public CustomerDto overwriteCustomer(Long id, CustomerDto customerDto) {
        customerDto.setCustomerUrl(getCustomerById(id).getCustomerUrl());

        return saveAndReturnDto(customerMapper.toDomain(customerDto));
    }

    @Override
    public CustomerDto patchCustomer(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id).map(customer -> {

            if(customerDto.getFirstName() != null) {
                customer.setFirstName(customerDto.getFirstName());
            }

            if(customerDto.getLastName() != null) {
                customer.setLastName(customerDto.getLastName());
            }

            return customerMapper.toDto(customerRepository.save(customer));
        }).orElseThrow(RuntimeException::new);
        //todo implement better exception handling
    }

    private CustomerDto saveAndReturnDto(Customer customer) {
        return customerMapper.toDto(customerRepository.save(customer));
    }
}
