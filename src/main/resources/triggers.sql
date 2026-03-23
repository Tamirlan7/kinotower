CREATE OR REPLACE FUNCTION fn_mark_seat_as_booked()
    RETURNS TRIGGER AS
$$
BEGIN
    -- Update the availability of the specific seat linked to the ticket
    UPDATE t_seat
    SET is_available = FALSE
    WHERE id = NEW.seat_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_on_ticket_purchase
    AFTER INSERT
    ON t_ticket
    FOR EACH ROW
EXECUTE FUNCTION fn_mark_seat_as_booked();


CREATE OR REPLACE FUNCTION fn_update_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_user_timestamp
    BEFORE UPDATE
    ON t_user
    FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();

CREATE TRIGGER trg_update_user_timestamp
    BEFORE UPDATE
    ON t_review
    FOR EACH ROW
EXECUTE FUNCTION fn_update_timestamp();


CREATE OR REPLACE FUNCTION fn_generate_seats_for_screening()
    RETURNS TRIGGER AS
$$
DECLARE
    v_rows     INT;
    v_cols     INT;
    v_row_char CHAR(1);
    v_base_price    NUMERIC(10, 2);
    v_final_price NUMERIC(10, 2);
    v_seat_type VARCHAR(20);
BEGIN
    -- 1. Get the hall dimensions
    SELECT rows_count, seats_per_row
    INTO v_rows, v_cols
    FROM t_hall
    WHERE id = NEW.hall_id;

    -- 2. Get the base film price to set for each seat
    SELECT base_price
    INTO v_base_price
    FROM t_film
    WHERE t_film.id = NEW.film_id;

    -- 3. Loop through rows (A, B, C...) and seat numbers (1, 2, 3...)
    FOR r IN 1..v_rows LOOP
            v_row_char := chr(64 + r);
            FOR col IN 1..v_cols LOOP
                IF r = v_rows THEN
                    v_final_price := v_base_price + 1500.00;
                    v_seat_type := 'VIP';
                ELSE
                    v_final_price := v_base_price;
                    v_seat_type := 'Standard';
                END IF;

                INSERT INTO t_seat (screening_id, price, row_number, seat_number, seat_type)
                VALUES (NEW.id, v_base_price, v_row_char, col, v_seat_type);
                END LOOP;
        END LOOP;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_auto_generate_seats
    AFTER INSERT
    ON t_screening
    FOR EACH ROW
EXECUTE FUNCTION fn_create_seats_for_screening();
