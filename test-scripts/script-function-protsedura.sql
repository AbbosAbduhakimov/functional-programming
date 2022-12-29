CREATE FUNCTION get_count_film(from_len BIGINT,to_len BIGINT)
RETURNS BIGINT
LANGUAGE plpgsql
as
    $$
    DECLARE
        film_count BIGINT;
    BEGIN
        SELECT COUNT(*)
        INTO film_count
        FROM film
        WHERE length BETWEEN from_len AND to_len;

    RETURN film_count;
    end;
    $$;


SELECT get_count_film(35,50);

-- alternatives
SELECT get_count_film(from_len := 35,to_len := 120);

SELECT get_count_film(from_len => 35,to_len := 50);

SELECT get_count_film(from_len := 35,to_len => 50);


