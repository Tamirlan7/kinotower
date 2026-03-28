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


INSERT INTO t_hall (name, rows_count, seats_per_row, is_3d)
VALUES ('Hall A', 8, 8, false),
       ('Hall B', 5, 10, false),
       ('Hall C', 15, 10, false),
       ('Hall D', 6, 6, false),
       ('iMAX', 7, 10, true);

INSERT INTO t_screening (film_id, hall_id, screening_datetime)
VALUES
    (2, 2, '2026-03-28T13:00:00'),
    (11, 2, '2026-03-28T15:00:00');

INSERT INTO t_screening (film_id, hall_id, screening_datetime)
VALUES
    (11, 2, '2026-03-29T15:00:00');


WITH filteredByDayScreeningsCte AS (
    SELECT
        *
    FROM t_screening
    WHERE DATE_PART('day', screening_datetime) = '28'
)

SELECT
    s.id,
    s.screening_datetime,
    f.duration_min
FROM filteredByDayScreeningsCte AS s
LEFT JOIN t_film AS f ON s.film_id = f.id
ORDER BY s.screening_datetime
