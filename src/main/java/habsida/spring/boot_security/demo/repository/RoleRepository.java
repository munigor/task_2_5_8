package habsida.spring.boot_security.demo.repository;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.UserRole;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<@NonNull Role, @NonNull Long> {
    Optional<@NonNull Role> findByName(UserRole name);
}
