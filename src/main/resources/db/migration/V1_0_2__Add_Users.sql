INSERT INTO users (id, firstname, lastname, age, username, password)
VALUES (1, 'Admin', 'Admin', 34, 'admin@admin', '$2a$12$UuWF9hz2DAJo54TlhyO4S.2y13xuRWKQwpR5iToAr6e6LcBLgSm9a');

INSERT INTO users (id, firstname, lastname, age, username, password)
VALUES (2, 'User', 'User', 34, 'user@user', '$2a$12$tAiAgkWU9hlOETJT6p7hbuXUw1lhflNWt.ksz3UgbLbOrBTZngX0a');

INSERT INTO users_roles (user_id, roles_id) VALUES (1,1);
INSERT INTO users_roles (user_id, roles_id) VALUES (1,2);
INSERT INTO users_roles (user_id, roles_id) VALUES (2,2);