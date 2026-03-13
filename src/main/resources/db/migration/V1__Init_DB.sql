DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name ENUM('ADMIN', 'USER') NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    lastname  VARCHAR(40) NULL,
    firstname  VARCHAR(40) NULL,
    age       INT NOT NULL,
    username  VARCHAR(50) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (user_id, roles_id)
);

ALTER TABLE roles ADD CONSTRAINT u_name UNIQUE(name);
ALTER TABLE users ADD CONSTRAINT u_username UNIQUE (username);

ALTER TABLE users_roles
ADD CONSTRAINT fk_users_roles_on_role FOREIGN KEY (roles_id) REFERENCES roles (id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE users_roles
ADD CONSTRAINT fk_users_roles_on_user FOREIGN KEY (user_id) REFERENCES users (id)
ON DELETE CASCADE ON UPDATE CASCADE;