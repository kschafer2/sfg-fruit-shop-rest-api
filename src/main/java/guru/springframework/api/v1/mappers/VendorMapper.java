package guru.springframework.api.v1.mappers;

import guru.springframework.api.v1.model.VendorDto;
import guru.springframework.domain.Vendor;

public class VendorMapper extends UrlToIdMapper<Vendor, VendorDto> {

    @Override
    public VendorDto toDto(Vendor vendor) {
        if(vendor == null) {
            return null;
        }

        return new VendorDto(vendor.getName(),
                    "/api/v1/vendors/" + vendor.getId()
        );
    }

    @Override
    public Vendor toDomain(VendorDto vendorDto) {
        if(vendorDto == null) {
            return null;
        }

        return new Vendor(getIdFromUrl(vendorDto.getVendorUrl()),
                          vendorDto.getName()
        );
    }
}
