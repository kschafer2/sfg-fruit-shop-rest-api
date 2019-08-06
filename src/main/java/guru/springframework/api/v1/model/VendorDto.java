package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDto {

    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(hidden = true)
    @JsonProperty("vendor_url")
    private String vendorUrl;

    public VendorDto(String name) {
        this.name = name;
    }
}
