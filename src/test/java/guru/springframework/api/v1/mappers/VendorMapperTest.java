package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.VendorDto;
import guru.springframework.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    private static final Long ID = 1L;
    private static final String NAME = "name";
    private static final String VENDOR_BASE_URL = "/api/v1/vendors/";
    VendorMapper vendorMapper = new VendorMapper();

    @Test
    public void toDtoTest() throws Exception {
        //given
        Vendor vendor = new Vendor(ID, NAME);

        //when
        VendorDto vendorDto = vendorMapper.toDto(vendor);

        //then
        assertEquals(NAME, vendorDto.getName());
        assertEquals(VENDOR_BASE_URL + ID, vendorDto.getVendorUrl());
    }

    @Test
    public void toDomainTest() throws Exception {
        //given
        VendorDto vendorDto = new VendorDto(NAME, VENDOR_BASE_URL + ID);

        //when
        Vendor vendor = vendorMapper.toDomain(vendorDto);

        //then
        assertEquals(NAME, vendor.getName());
        assertEquals(ID, vendor.getId());
    }
}
