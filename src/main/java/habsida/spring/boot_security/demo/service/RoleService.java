package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.UserRole;
import habsida.spring.boot_security.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Set<Role> findAllByIds(List<Long> ids) {
        return new HashSet<>(roleRepository.findAllById(ids));
    }

    public Role findByName(UserRole role) {
        return roleRepository.findByName(role).orElseThrow(
            () -> new RuntimeException("Role with name " + role.name() + " not found!")
        );
    }
}
