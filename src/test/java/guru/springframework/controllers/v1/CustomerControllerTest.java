package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.CustomerDto;
import guru.springframework.controllers.RestResponseEntityExceptionHandler;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest{

    private static final String FIRST = "first";
    private static final String LAST = "last";
    private static final String CUSTOMER_URL_1 = "/api/v1/customers/1";
    private static final String FIRST_JSON = "$.firstname";
    private static final String CUSTOMER_URL_JSON = "$.customer_url";
    private static final String LAST_JSON = "$.lastname";
    private static final String CUSTOMERS_JSON = "$.customers";
    private static final String CUSTOMER_BASE_URL = "/api/v1/customers/";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        CustomerDto customer1 = new CustomerDto(FIRST, LAST);
        CustomerDto customer2 = new CustomerDto("Jack", LAST);
        List<CustomerDto> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get(CUSTOMER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(CUSTOMERS_JSON, hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {
        CustomerDto customer1 = new CustomerDto(FIRST, LAST, CUSTOMER_URL_1);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get(CUSTOMER_URL_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(FIRST_JSON, equalTo(FIRST)));
    }

    @Test
    public void createNewCustomerTest() throws Exception {
        CustomerDto customer = new CustomerDto(FIRST, LAST);
        CustomerDto returnDto = new CustomerDto(customer.getFirstName(),
                                                customer.getLastName(),
                                                CUSTOMER_URL_1
        );

        when(customerService.createNewCustomer(customer)).thenReturn(returnDto);

        mockMvc.perform(post(CUSTOMER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(FIRST_JSON, equalTo(FIRST)))
                .andExpect(jsonPath(CUSTOMER_URL_JSON, equalTo(CUSTOMER_URL_1)));
    }

    @Test
    public void overwriteCustomerTest() throws Exception {
        CustomerDto customer = new CustomerDto(FIRST, LAST);
        CustomerDto returnDto = new CustomerDto(
                                        customer.getFirstName(),
                                        customer.getLastName(),
                                        CUSTOMER_URL_1
        );

        when(customerService
                .overwriteCustomer(anyLong(), any(CustomerDto.class)))
                .thenReturn(returnDto);

        mockMvc.perform(put(CUSTOMER_URL_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(FIRST_JSON, equalTo(FIRST)))
                .andExpect(jsonPath(LAST_JSON, equalTo(LAST)))
                .andExpect(jsonPath(CUSTOMER_URL_JSON, equalTo(CUSTOMER_URL_1)));
    }

    @Test
    public void patchCustomerTest() throws Exception {
        CustomerDto customer = new CustomerDto();
        customer.setFirstName(FIRST);

        CustomerDto returnDto = new CustomerDto(
                customer.getFirstName(),
                LAST,
                CUSTOMER_URL_1
        );

        when(customerService
                .patchCustomer(anyLong(), any(CustomerDto.class)))
                .thenReturn(returnDto);

        mockMvc.perform(patch(CUSTOMER_URL_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(FIRST_JSON, equalTo(FIRST)))
                .andExpect(jsonPath(LAST_JSON, equalTo(LAST)))
                .andExpect(jsonPath(CUSTOMER_URL_JSON, equalTo(CUSTOMER_URL_1)));
    }

    @Test
    public void deleteCustomer() throws Exception {
        mockMvc.perform(delete(CUSTOMER_URL_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }

    @Test
    public void notFoundExceptionTest() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CUSTOMER_BASE_URL + "123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}