package guru.springframework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(required = true)
    private String name;
}
