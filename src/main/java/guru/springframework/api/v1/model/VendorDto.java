package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDto {

    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;

    public VendorDto(String name) {
        this.name = name;
    }
}
