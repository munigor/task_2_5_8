package habsida.spring.boot_security.demo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response <T> {
    private boolean success;
    private T payload;
    private Object error;
    private String message;

    public static <T> Response<T> success(T payload) {
        return new Response<>(true, payload, null, null);
    }

    public static <T> Response<T> success(T payload, String message) {
        return new Response<>(true, payload, null, message);
    }

    public static <T> Response<T> error(Object error) {
        return new Response<>(false, null, error, null);
    }

    public static <T> Response<T> error(Object error, String message) {
        return new Response<>(false, null, error, message);
    }
}
