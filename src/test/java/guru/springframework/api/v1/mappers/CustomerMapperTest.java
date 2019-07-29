package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final Long ID = 1L;
    public static final String FIRST = "first";
    public static final String LAST = "last";
    CustomerMapper customerMapper = new CustomerMapper();

    @Test
    public void toDtoTest() throws Exception {
        //given
        Customer customer = new Customer(ID, FIRST, LAST);

        //when
        CustomerDto customerDto = customerMapper.toDto(customer);

        //then
        assertEquals(ID, customerDto.getId());
        assertEquals(FIRST, customerDto.getFirstName());
        assertEquals(LAST, customerDto.getLastName());
    }

    @Test
    public void toDomainTest() throws Exception {
        //given
        CustomerDto customerDto = new CustomerDto(ID, FIRST, LAST);

        //when
        Customer customer = customerMapper.toDomain(customerDto);

        //then
        assertEquals(ID, customer.getId());
        assertEquals(FIRST, customer.getFirstName());
        assertEquals(LAST, customer.getLastName());
    }
}
