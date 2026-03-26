INSERT INTO t_role (id, name, authorities)
VALUES (1, 'Администратор', 'will-assign-later'),
       (2, 'Пользователь', 'will-assign-later');


INSERT INTO t_user (email, password, role)
VALUES ('admin', 'admin1234', 1),
       ('user@gmail.com', 'user1234', 2);

INSERT INTO t_genre (id, name)
VALUES (1, 'Боевик'),
       (2, 'Драма'),
       (3, 'Фантастика'),
       (4, 'Фэнтези'),
       (5, 'Комедия'),
       (6, 'Триллер'),
       (7, 'Ужасы'),
       (8, 'Триллер'),
       (9, 'Мелодрама'),
       (10, 'Вестерн'),
       (12, 'Байопик'),
       (13, 'Мюзикл'),
       (14, 'Слэшер'),
       (15, 'Апокалипсис'),
       (16, 'Постапокалипсис'),
       (17, 'Нуар'),
       (18, 'Киберпанк'),
       (19, 'Документальное кино'),
       (20, 'Мокьюментари'),
       (21, 'Анимация (Мультфильмы)'),
       (22, 'Короткометражка'),
       (23, 'Артхаус'),
       (24, 'Приключения');


SELECT
    *
FROM t_film
LEFT JOIN t_file AS tf ON t_film.film_video_id = tf.id
LEFT JOIN t_file AS tff ON t_film.preview_image_id = tff.id
LEFT JOIN t_genre_film ON t_film.id = t_genre_film.film_id
LEFT JOIN t_genre ON t_genre_film.genre_id = t_genre.id
WHERE t_film.name ILIKE '%каж%'