CREATE TABLE IF NOT EXISTS t_file
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    path TEXT         NOT NULL,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS t_role
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) UNIQUE                 NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    authorities TEXT      DEFAULT ''
);

CREATE TABLE IF NOT EXISTS t_user
(
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(255)                                      NOT NULL UNIQUE,
    password   VARCHAR(100)                                      NOT NULL,
    role       INTEGER REFERENCES t_role (id) ON DELETE RESTRICT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP               NOT NULL,
    updated_at TIMESTAMP                                         NULL
);

CREATE TABLE IF NOT EXISTS t_genre
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS t_hall
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(50) UNIQUE    NOT NULL,
    rows_count    INTEGER               NOT NULL,
    seats_per_row INTEGER               NOT NULL,
    is_3d         BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE IF NOT EXISTS t_film
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(255)                           NOT NULL,
    description      TEXT                                   NULL,
    language         VARCHAR(20) DEFAULT 'Қазақша (Дубляж)' NOT NULL,
    age_rating       INTEGER     DEFAULT 18                 NOT NULL,
    base_price       NUMERIC(10, 2)                         NOT NULL,
    duration_min     INTEGER                                NOT NULL,
    preview_image_id INTEGER                                REFERENCES t_file (id) ON DELETE SET NULL,
    film_video_id    INTEGER                                NOT NULL REFERENCES t_file (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_genre_film
(
    film_id  INTEGER REFERENCES t_film (id) ON DELETE CASCADE,
    genre_id INTEGER REFERENCES t_genre (id) ON DELETE CASCADE,
    PRIMARY KEY (film_id, genre_id)
);

CREATE TABLE IF NOT EXISTS t_cast_person
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT         NULL,
    image_id    INTEGER      REFERENCES t_file (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS t_film_cast
(
    cast_person_id INTEGER REFERENCES t_cast_person (id) ON DELETE CASCADE,
    film_id        INTEGER REFERENCES t_film (id) ON DELETE CASCADE,
    role_name      VARCHAR(100) DEFAULT 'Actor', -- Optional: 'Actor', 'Director', 'Producer'
    PRIMARY KEY (cast_person_id, film_id)
);

CREATE TABLE IF NOT EXISTS t_screening
(
    id                 SERIAL PRIMARY KEY,
    film_id            INTEGER REFERENCES t_film (id) ON DELETE CASCADE,
    screening_datetime TIMESTAMP NOT NULL,
    hall_id            INTEGER   REFERENCES t_hall (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS t_seat
(
    id           SERIAL PRIMARY KEY,
    screening_id INTEGER REFERENCES t_screening (id) ON DELETE CASCADE,
    price        NUMERIC(10, 2)                 NOT NULL,
    seat_type    VARCHAR(20) DEFAULT 'Standard' NOT NULL, /* 'VIP', 'Standard', or something else*/
    row_number   VARCHAR(10)                    NOT NULL,
    seat_number  INTEGER                        NOT NULL,
    is_available BOOLEAN     DEFAULT TRUE       NOT NULL,
    UNIQUE (screening_id, row_number, seat_number),
    CHECK ( seat_type IN ('VIP', 'Standard', 'Sofa') )
);

CREATE TABLE IF NOT EXISTS t_ticket
(
    id           SERIAL PRIMARY KEY,
    screening_id INTEGER REFERENCES t_screening (id) ON DELETE CASCADE,
    user_id      INTEGER REFERENCES t_user (id) ON DELETE CASCADE,
    seat_id      INTEGER     NOT NULL REFERENCES t_seat (id) ON DELETE CASCADE, -- Changed from seat_number
    purchased_at TIMESTAMP            DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status       VARCHAR(20) NOT NULL DEFAULT 'BOOKED',
    CHECK ( status IN ('BOOKED', 'PAID', 'CANCELED') )
);

CREATE TABLE IF NOT EXISTS t_review
(
    id         SERIAL PRIMARY KEY,
    rating     INTEGER                             NOT NULL CHECK ( rating >= 1 AND rating <= 10 ),
    content    TEXT                                NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP                           NULL,
    user_id    INTEGER                             NOT NULL REFERENCES t_user (id) ON DELETE CASCADE,
    film_id    INTEGER                             NOT NULL REFERENCES t_film (id) ON DELETE CASCADE,
    UNIQUE (user_id, film_id)
);

CREATE TABLE IF NOT EXISTS t_payment
(
    id             SERIAL PRIMARY KEY,
    ticket_id      INTEGER                               NOT NULL REFERENCES t_ticket (id) ON DELETE CASCADE,
    amount         NUMERIC(10, 2)                        NOT NULL,
    payment_method VARCHAR(255)                          NULL,
    transaction_id VARCHAR(255) UNIQUE                   NOT NULL,
    paid_at        TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status         VARCHAR(50) DEFAULT 'PENDING'         NOT NULL,
    CHECK ( status IN ('PENDING', 'FAILED', 'SUCCESS', 'REFUNDED') )
);
