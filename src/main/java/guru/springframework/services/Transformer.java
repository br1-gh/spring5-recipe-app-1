package guru.springframework.services;

public interface Transformer<T, N> {
    Iterable<N> transform(Iterable<T> entities);
}
