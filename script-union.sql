CREATE TABLE top_rated_films(
                                title VARCHAR NOT NULL,
                                release_year SMALLINT
);

CREATE TABLE most_popular_films(
                                   title VARCHAR NOT NULL,
                                   release_year SMALLINT
);



INSERT INTO
    top_rated_films(title,release_year)
VALUES
    ('The Shawshank Redemption',1994),
    ('The Godfather',1972),
    ('12 Angry Men',1957);


INSERT INTO
    most_popular_films(title,release_year)
VALUES
    ('An American Pickle',2020),
    ('The Godfather',1972),
    ('Greyhound',2020);


-- UNION skip duplicate
SELECT * FROM top_rated_films
UNION
SELECT * FROM most_popular_films;


-- UNION ALL print with duplicate
SELECT * FROM top_rated_films
UNION ALL
SELECT * FROM most_popular_films;


-- we can sorting used ORDER BY
SELECT * FROM top_rated_films
UNION ALL
SELECT * FROM most_popular_films
ORDER BY title




