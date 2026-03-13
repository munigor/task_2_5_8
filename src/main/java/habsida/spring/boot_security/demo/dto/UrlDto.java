package habsida.spring.boot_security.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UrlDto {
    private String name;
    private String url;
    private Set<String> roles;
}