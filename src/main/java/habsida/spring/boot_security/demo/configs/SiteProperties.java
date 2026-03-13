package habsida.spring.boot_security.demo.configs;

import habsida.spring.boot_security.demo.dto.UrlDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Configuration("siteBean")
@ConfigurationProperties("site")
@Data
public class SiteProperties {
    private Map<String, Set<UrlDto>> nav;
}
