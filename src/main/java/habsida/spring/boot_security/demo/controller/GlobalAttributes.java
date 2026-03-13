package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.dto.UrlDto;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;

@ControllerAdvice(basePackages = "habsida.spring.boot_security.demo.controller")
@RequiredArgsConstructor
public class GlobalAttributes {

    private final SiteService siteService;

    @ModelAttribute("authorizedUser")
    public User getUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @ModelAttribute("side")
    public Set<UrlDto> getSide(@AuthenticationPrincipal User user) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(user.getAuthorities());
        return siteService.getSide("side", authorities);
    }
}
