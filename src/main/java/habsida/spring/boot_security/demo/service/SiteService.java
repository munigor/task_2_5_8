package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.configs.SiteProperties;
import habsida.spring.boot_security.demo.dto.UrlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteProperties properties;

    public Set<UrlDto> getSide(String key, Set<String> authorities) {
        return properties
            .getNav()
            .get(key)
            .stream()
            .filter(url -> hasAnyRole(url.getRoles(), authorities))
            .collect(Collectors.toSet());
    }

    private boolean hasAnyRole(Set<String> urlRoles, Set<String> userRoles) {
        return urlRoles.stream().anyMatch(userRoles::contains);
    }
}
