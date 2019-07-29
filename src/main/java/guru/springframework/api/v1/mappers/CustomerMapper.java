package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.domain.Customer;

public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    @Override
    public CustomerDto toDto(Customer customer) {
        if(customer == null) {
            return null;
        }

        return new CustomerDto(
                customer.getFirstName(),
                customer.getLastName()
        );
    }

    @Override
    public Customer toDomain(CustomerDto customerDto) {
        if(customerDto == null) {
            return null;
        }

        return new Customer(
                customerDto.getFirstName(),
                customerDto.getLastName()
        );
    }
}
