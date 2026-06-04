package habsida.spring.boot_security.demo.controller.api;

import habsida.spring.boot_security.demo.dto.UrlDto;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/ui")
public class UiController {

    private final SiteService siteService;

    @GetMapping("/user")
    public User getUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/side")
    public Set<UrlDto> getSide(@AuthenticationPrincipal User user) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(user.getAuthorities());
        return siteService.getSide("side", authorities);
    }
}
