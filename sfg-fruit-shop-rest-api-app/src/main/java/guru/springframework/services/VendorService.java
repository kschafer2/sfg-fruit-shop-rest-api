package guru.springframework.services;

import guru.springframework.api.v1.model.VendorDto;

import java.util.List;

public interface VendorService {
    
    List<VendorDto> getAllVendors();

    VendorDto getVendorById(Long id);

    VendorDto createNewVendor(VendorDto vendorDto);

    VendorDto overwriteVendor(Long id, VendorDto vendorDto);

    VendorDto patchVendor(Long id, VendorDto vendorDto);

    void deleteVendorById(Long id);
}
