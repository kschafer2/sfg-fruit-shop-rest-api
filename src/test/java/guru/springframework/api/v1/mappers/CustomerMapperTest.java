package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    private static final String FIRST = "first";
    private static final String LAST = "last";
    private CustomerMapper customerMapper = new CustomerMapper();

    @Test
    public void toDtoTest() throws Exception {
        //given
        Customer customer = new Customer(FIRST, LAST);

        //when
        CustomerDto customerDto = customerMapper.toDto(customer);

        //then
        assertEquals(FIRST, customerDto.getFirstName());
        assertEquals(LAST, customerDto.getLastName());
    }

    @Test
    public void toDomainTest() throws Exception {
        //given
        CustomerDto customerDto = new CustomerDto(FIRST, LAST);

        //when
        Customer customer = customerMapper.toDomain(customerDto);

        //then
        assertEquals(FIRST, customer.getFirstName());
        assertEquals(LAST, customer.getLastName());
    }
}
