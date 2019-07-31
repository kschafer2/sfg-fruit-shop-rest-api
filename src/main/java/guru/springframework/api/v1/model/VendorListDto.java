package guru.springframework.api.v1.model;

import guru.springframework.domain.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDto {

    private List<Vendor> vendors;
}
