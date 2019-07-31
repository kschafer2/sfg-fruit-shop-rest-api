package guru.springframework.services;

import guru.springframework.api.v1.mappers.VendorMapper;
import guru.springframework.api.v1.model.VendorDto;
import guru.springframework.domain.Vendor;
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

    @Override
    public VendorDto createNewVendor(VendorDto vendorDto) {
        return saveAndReturnDto(vendorMapper.toDomain(vendorDto));
    }

    @Override
    public VendorDto overwriteVendor(Long id, VendorDto vendorDto) {
        vendorDto.setVendorUrl(getVendorById(id).getVendorUrl());

        return saveAndReturnDto(vendorMapper.toDomain(vendorDto));
    }

    @Override
    public VendorDto patchVendor(Long id, VendorDto vendorDto) {
        return vendorRepository
                .findById(id)
                .map(vendor -> {
                    if(vendorDto.getName() != null) {
                        vendor.setName(vendorDto.getName());
                    }
                    return saveAndReturnDto(vendor);

                }).orElseThrow(ResourceNotFoundException::new);
    }

    private VendorDto saveAndReturnDto(Vendor vendor) {
        return vendorMapper.toDto(vendorRepository.save(vendor));
    }
}
