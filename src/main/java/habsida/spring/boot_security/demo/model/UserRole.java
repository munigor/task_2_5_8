package habsida.spring.boot_security.demo.model;

public enum UserRole {
    ADMIN,
    USER;

    public String toAuthority() {
        return "ROLE_" + name();
    }
}

