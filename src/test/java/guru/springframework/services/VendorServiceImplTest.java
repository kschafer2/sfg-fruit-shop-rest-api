package guru.springframework.services;

import guru.springframework.api.v1.mappers.VendorMapper;
import guru.springframework.api.v1.model.VendorDto;
import guru.springframework.domain.Vendor;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {
    private static final Long ID = 1L;
    private static final String NAME = "name";
    private static final String VENDOR_URL_1 = "/api/v1/vendors/1";
    private VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(
                new VendorMapper(), vendorRepository
        );
    }

    @Test
    public void getAllVendors() throws Exception {
        //given
        List<Vendor> vendors = Arrays.asList(
                new Vendor(), new Vendor()
                );

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDto> vendorDtos = vendorService.getAllVendors();

        //then
        assertEquals(2, vendorDtos.size());
    }

    @Test
    public void getVendorById() throws Exception {
        //given
        Vendor vendor = new Vendor(ID, NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        //when
        VendorDto vendorDto = vendorService.getVendorById(ID);

        //then
        assertEquals(NAME, vendorDto.getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getVendorByIdNotFound() throws Exception {
        //given
        given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        vendorService.getVendorById(ID);

        //then
        then(vendorRepository).should(times(1)).findById(anyLong());
    }

    @Test
    public void createNewVendorTest() throws Exception {
        //given
        VendorDto vendorDto = new VendorDto(NAME);
        Vendor savedVendor = new Vendor(ID, vendorDto.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDto savedDto = vendorService.createNewVendor(vendorDto);

        //then
        assertEquals(vendorDto.getName(), savedDto.getName());
        assertEquals(VENDOR_URL_1, savedDto.getVendorUrl());
    }

    @Test
    public void overwriteCustomerTest() throws Exception {
        //given
        VendorDto vendorDto = new VendorDto(NAME);
        Vendor savedVendor = new Vendor(ID, vendorDto.getName());

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(savedVendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDto savedDto = vendorService.overwriteVendor(ID, vendorDto);

        //then
        assertEquals(vendorDto.getName(), savedDto.getName());
        assertEquals(VENDOR_URL_1, savedDto.getVendorUrl());
    }

    @Test
    public void patchVendorTest() throws Exception {
        //given
        VendorDto vendorDto = new VendorDto(NAME);
        Vendor vendor = new Vendor(ID, NAME);

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        //when
        VendorDto savedDto = vendorService.patchVendor(ID, vendorDto);

        //then
        //'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should().findById(anyLong());
        assertThat(savedDto.getVendorUrl(), containsString("1"));
    }

    @Test
    public void deleteVendorByIdTest() throws Exception {
        vendorRepository.deleteById(ID);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}
