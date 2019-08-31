package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @JsonProperty("firstname")
    @ApiModelProperty(required = true)
    private String firstName;

    @JsonProperty("lastname")
    @ApiModelProperty(required = true)
    private String lastName;

    @ApiModelProperty(hidden = true)
    @JsonProperty("customer_url")
    private String customerUrl;

    public CustomerDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
