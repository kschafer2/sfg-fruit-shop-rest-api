package guru.springframework.services;

import guru.springframework.api.v1.mappers.VendorMapper;
import guru.springframework.api.v1.model.VendorDto;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDto> getAllVendors() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDto getVendorById(Long id) {
        return vendorRepository
                .findById(id)
                .map(vendorMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
