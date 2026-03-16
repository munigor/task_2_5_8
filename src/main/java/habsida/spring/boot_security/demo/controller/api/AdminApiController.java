package habsida.spring.boot_security.demo.controller.api;

import habsida.spring.boot_security.demo.dto.OnCreate;
import habsida.spring.boot_security.demo.dto.OnUpdate;
import habsida.spring.boot_security.demo.dto.UserDto;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.response.Response;
import habsida.spring.boot_security.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminApiController {
    private final UserService userService;

    @PostMapping({"", "/", "/index"})
    public List<User> getUsers() {
        return  userService.findAll();
    }

    @PostMapping("/get/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }


    @PostMapping("/add")
    public Response<?> addUser(@Validated(OnCreate.class) @RequestBody UserDto user) {
        userService.add(user);
        return Response.builder()
            .success(true)
            .message(String.format("User %s %s successfully added", user.getLastname(), user.getFirstname()))
            .build();
    }

    @PostMapping("/edit")
    public Response<?> editUser(@Validated(OnUpdate.class) @RequestBody UserDto user) {
        userService.update(user);
        return Response.builder()
            .success(true)
            .message(String.format("User with id = %d, successfully updated!", user.getId()))
            .build();
    }

    @PostMapping("/delete/{id}")
    public Response<?> remove(@PathVariable("id") Long userId) {
        userService.delete(userId);
        return Response.builder()
            .success(true)
            .message(String.format("User with id = %d, successfully deleted!", userId))
            .build();
    }
}
