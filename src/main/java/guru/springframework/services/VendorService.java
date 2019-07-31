package guru.springframework.services;

import guru.springframework.api.v1.model.VendorDto;

import java.util.List;

public interface VendorService {
    
    List<VendorDto> getAllVendors();

    VendorDto getVendorById(Long id);
}
