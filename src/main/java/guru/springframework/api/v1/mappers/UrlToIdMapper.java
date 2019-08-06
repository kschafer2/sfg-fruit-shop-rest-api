package guru.springframework.api.v1.mappers;

public abstract class UrlToIdMapper<DOMAIN, DTO> implements Mapper<DOMAIN, DTO> {

    protected Long getIdFromUrl(String url) {
        if(url == null) {
            return null;
        }

        return Long.valueOf(String.valueOf(url.charAt(url.length()-1)));
    }
}
