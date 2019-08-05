package guru.springframework.api.v1.mappers;

public interface Mapper<DOMAIN, DTO> {

    DTO toDto(DOMAIN domain);
    DOMAIN toDomain(DTO dto);
}
