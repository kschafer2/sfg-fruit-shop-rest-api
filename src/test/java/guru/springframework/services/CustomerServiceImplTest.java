package guru.springframework.services;

import guru.springframework.api.v1.mappers.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    private static final Long ID = 1L;
    private static final String FIRST = "first";
    private static final String LAST = "last";
    private CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(
                new CustomerMapper(), customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        List<Customer> customers = Arrays.asList(
                new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDto> customerDtos = customerService.getAllCustomers();

        //then
        assertEquals(2, customerDtos.size());
    }

    @Test
    public void getCustomerById() throws Exception {
        //given
        Customer customer = new Customer(ID, FIRST, LAST);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //when
        CustomerDto customerDto = customerService.getCustomerById(ID);

        //then
        assertEquals(FIRST, customerDto.getFirstName());
    }

    @Test
    public void createNewCustomerTest() throws Exception {
        //given
        CustomerDto customerDto = new CustomerDto(FIRST, LAST);
        Customer savedCustomer = new Customer(
                ID, customerDto.getFirstName(), customerDto.getLastName()
        );

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDto savedDto = customerService.createNewCustomer(customerDto);

        //then
        assertEquals(customerDto.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customers/1", savedDto.getCustomerUrl());
    }

    @Test
    public void overwriteCustomerTest() throws Exception {
        //given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Jim");

        Customer savedCustomer = new Customer(
                ID, customerDto.getFirstName(), customerDto.getLastName()
        );

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(savedCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDto savedDto = customerService.overwriteCustomer(ID, customerDto);

        //then
        assertEquals(customerDto.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customers/1", savedDto.getCustomerUrl());
    }
}
