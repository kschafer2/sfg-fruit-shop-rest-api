package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.VendorDto;
import guru.springframework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static guru.springframework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {

    private static final String VENDOR_BASE_URL = "/api/v1/vendors/";
    private static final String NAME = "name";
    private static final String VENDOR_URL_1 = VENDOR_BASE_URL + "1";
    private static final String NAME_JSON = "$.name";
    private static final String VENDOR_URL_JSON = "$.vendor_url";
    private static final String VENDORS_JSON = "$.vendors";

    @MockBean
    private VendorService vendorService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
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
    public void getVendorByIdTest() throws Exception {
        VendorDto vendor1 = new VendorDto(NAME, VENDOR_URL_1);

        when(vendorService.getVendorById(anyLong())).thenReturn(vendor1);

        mockMvc.perform(get(VENDOR_URL_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(NAME_JSON, equalTo(NAME)));
    }

    @Test
    public void createNewVendorTest() throws Exception {
        VendorDto vendor = new VendorDto(NAME);
        VendorDto returnDto = new VendorDto(vendor.getName(), VENDOR_URL_1);

        when(vendorService.createNewVendor(vendor)).thenReturn(returnDto);

        mockMvc.perform(post(VENDOR_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(NAME_JSON, equalTo(NAME)))
                .andExpect(jsonPath(VENDOR_URL_JSON, equalTo(VENDOR_URL_1)));
    }

    @Test
    public void overwriteCustomerTest() throws Exception {
        VendorDto vendor = new VendorDto(NAME);
        VendorDto returnDto = new VendorDto(vendor.getName(), VENDOR_URL_1);

        when(vendorService.overwriteVendor(anyLong(), any(VendorDto.class)))
                .thenReturn(returnDto);

        mockMvc.perform(put(VENDOR_URL_1)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(NAME_JSON, equalTo(NAME)))
                .andExpect(jsonPath(VENDOR_URL_JSON, equalTo(VENDOR_URL_1)));

    }

    @Test
    public void patchVendorTest() throws Exception {
        VendorDto vendorDto = new VendorDto(NAME, VENDOR_URL_1);

        given(vendorService.patchVendor(anyLong(), any(VendorDto.class)))
                .willReturn(vendorDto);

        mockMvc.perform(patch(VENDOR_URL_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(NAME_JSON, equalTo(vendorDto.getName())));
    }

    @Test
    public void deleteVendor() throws Exception {
        mockMvc.perform(delete(VENDOR_URL_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());
    }
}
