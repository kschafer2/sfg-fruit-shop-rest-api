package guru.springframework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
}
