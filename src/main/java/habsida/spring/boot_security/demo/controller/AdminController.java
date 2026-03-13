package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping({ "", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", userService.getDto());
        return "admin/index";
    }
}
