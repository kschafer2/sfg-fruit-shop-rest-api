package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper extends UrlToIdMapper<Customer, CustomerDto> {

    @Override
    public CustomerDto toDto(Customer customer) {
        if(customer == null) {
            return null;
        }

        return new CustomerDto(
                customer.getFirstName(),
                customer.getLastName(),
                "/api/v1/customers/" + customer.getId()
        );
    }

    @Override
    public Customer toDomain(CustomerDto customerDto) {
        if(customerDto == null) {
            return null;
        }

        return new Customer(
                getIdFromUrl(customerDto.getCustomerUrl()),
                customerDto.getFirstName(),
                customerDto.getLastName()
        );
    }
}
