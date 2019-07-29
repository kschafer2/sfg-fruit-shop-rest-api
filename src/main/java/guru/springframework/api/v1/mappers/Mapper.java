package guru.springframework.api.v1.mappers;

public interface Mapper<S, T> {

    T map (S source);
}
