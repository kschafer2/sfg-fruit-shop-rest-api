package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    private static final Long ID = 1L;
    private static final String FIRST = "first";
    private static final String LAST = "last";
    private static final String CUSTOMER_BASE_URL = "/api/v1/customers/";
    CustomerMapper customerMapper = new CustomerMapper();

    @Test
    public void toDtoTest() throws Exception {
        //given
        Customer customer = new Customer(ID, FIRST, LAST);

        //when
        CustomerDto customerDto = customerMapper.toDto(customer);

        //then
        assertEquals(FIRST, customerDto.getFirstName());
        assertEquals(LAST, customerDto.getLastName());
        assertEquals(CUSTOMER_BASE_URL + ID, customerDto.getCustomerUrl());
    }

    @Test
    public void toDomainTest() throws Exception {
        //given
        CustomerDto customerDto = new CustomerDto(
                FIRST, LAST, CUSTOMER_BASE_URL + ID
        );

        //when
        Customer customer = customerMapper.toDomain(customerDto);

        //then
        assertEquals(FIRST, customer.getFirstName());
        assertEquals(LAST, customer.getLastName());
        assertEquals(ID, customer.getId());
    }
}
