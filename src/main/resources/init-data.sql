INSERT INTO t_role (id, name, authorities)
VALUES (1, 'Администратор', 'will-assign-later'),
       (2, 'Пользователь', 'will-assign-later');


INSERT INTO t_user (email, password, role)
VALUES ('admin', 'admin1234', 1),
       ('user@gmail.com', 'user1234', 2);

