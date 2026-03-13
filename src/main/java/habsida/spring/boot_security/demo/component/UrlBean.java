package habsida.spring.boot_security.demo.component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("urlBean")
@RequiredArgsConstructor
public class UrlBean {
    private final HttpServletRequest request;

    public Boolean isActive(String url) {
        return request.getRequestURI().contains(url);
    }
}
