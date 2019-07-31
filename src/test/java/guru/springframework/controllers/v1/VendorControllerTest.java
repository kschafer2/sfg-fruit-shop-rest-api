package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.VendorDto;
import guru.springframework.controllers.RestResponseEntityExceptionHandler;
import guru.springframework.services.VendorService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    private static final String VENDOR_BASE_URL = "/api/v1/vendors/";
    private static final String NAME = "name";
    private static final String VENDOR_URL_1 = VENDOR_BASE_URL + "1";
    private static final String NAME_JSON = "$.name";
    private static final String VENDOR_URL_JSON = "$.vendor_url";
    private static final String VENDORS_JSON = "$.vendors";

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllVendorsTest() throws Exception {
        VendorDto vendor1 = new VendorDto(NAME);
        VendorDto vendor2 = new VendorDto("Foo");
        List<VendorDto> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get(VENDOR_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(VENDORS_JSON, hasSize(2)));
    }

    @Test
    public void getVendorByIdTEst() throws Exception {
        VendorDto vendor1 = new VendorDto(NAME, VENDOR_URL_1);

        when(vendorService.getVendorById(anyLong())).thenReturn(vendor1);

        mockMvc.perform(get(VENDOR_URL_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(NAME_JSON, equalTo(NAME)));
    }
}
