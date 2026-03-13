package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.dto.UserDto;
import habsida.spring.boot_security.demo.mapper.UserMapper;
import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.model.UserRole;
import habsida.spring.boot_security.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private User getUserFromDto(UserDto dto, User user) {
        userMapper.updateEntityFromDto(dto, user);
        if(dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        user.setRoles(roleService.findAllByIds(dto.getRoles()));
        return user;
    }

    public void add(UserDto dto) {
        userRepository.save(getUserFromDto(dto, new User()));
    }

    public void update(UserDto dto) {
        User user = findById(dto.getId());
        userRepository.saveAndFlush(getUserFromDto(dto, user));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public UserDto getDto() {
        UserDto dto = userMapper.toDto(new User());
        dto.setRoles(List.of(roleService.findByName(UserRole.USER).getId()));
        return dto;
    }

    public UserDto getDto(Long id) {
        User user = findById(id);
        UserDto dto = userMapper.toDto(user);
        dto.setRoles(user.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
        return dto;
    }

    public User findById(Long id) throws UsernameNotFoundException{
        return userRepository.findById(id).orElseThrow(() ->
             new UsernameNotFoundException("User with id = " + id + " not found")
        );
    }
}
