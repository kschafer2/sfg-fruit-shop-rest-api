package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.VendorDto;
import guru.springframework.api.v1.model.VendorListDto;
import guru.springframework.services.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDto getAllVendors() {
        log.info("Getting All Vendors...");

        return new VendorListDto(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto getVendorById(@PathVariable Long id) {
        log.info("Getting Vendor: " + id);

        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDto createNewVendor(@RequestBody VendorDto vendorDto) {
        log.info("Creating Vendor " + vendorDto.getName());

        return vendorService.createNewVendor(vendorDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto overwriteVendor(@PathVariable Long id,
                                     @RequestBody VendorDto vendorDto) {
        log.info("Overwriting Vendor: " + id);

        return vendorService.overwriteVendor(id, vendorDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto patchVendor(@PathVariable Long id,
                                 @RequestBody VendorDto vendorDto) {
        log.info("Patching Vendor: " + id);

        return vendorService.patchVendor(id, vendorDto);
    }
}
