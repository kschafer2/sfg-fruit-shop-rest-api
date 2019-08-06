package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.VendorDto;
import guru.springframework.api.v1.model.VendorListDto;
import guru.springframework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is the Vendor API")
@Slf4j
@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "View a list of all vendors")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDto getAllVendors() {
        log.info("Getting All Vendors...");

        return new VendorListDto(vendorService.getAllVendors());
    }

    @ApiOperation(value = "View a specific vendor")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto getVendorById(@PathVariable Long id) {
        log.info("Getting Vendor: " + id);

        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "Create a new vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDto createNewVendor(@RequestBody VendorDto vendorDto) {
        log.info("Creating Vendor " + vendorDto.getName());

        return vendorService.createNewVendor(vendorDto);
    }

    @ApiOperation(value = "Overwrite an existing vendor")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto overwriteVendor(@PathVariable Long id,
                                     @RequestBody VendorDto vendorDto) {
        log.info("Overwriting Vendor: " + id);

        return vendorService.overwriteVendor(id, vendorDto);
    }

    @ApiOperation(value = "Update an existing vendor's properties")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto patchVendor(@PathVariable Long id,
                                 @RequestBody VendorDto vendorDto) {
        log.info("Patching Vendor: " + id);

        return vendorService.patchVendor(id, vendorDto);
    }

    @ApiOperation(value = "Delete a vendor")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendorById(@PathVariable Long id) {
        log.info("Deleting Vendor: " + id);

        vendorService.deleteVendorById(id);
    }
}
