CREATE TABLE film(
                     id BIGSERIAL PRIMARY KEY ,
                     title VARCHAR(128) NOT NULL ,
                     description VARCHAR(128) NOT NULL ,
                     release_year INT ,
                     length BIGINT,
                     rating VARCHAR(32),
                     rental_rate NUMERIC

;

INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title1','short',2000,50,'G',0.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title2','short',2000,50,'G-TCS',2.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title3','medium',2012,60,'ONE',0.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title4','medium',2015,70,'M',0.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title5','long',2019,150,'M',2.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title6','long',2013,170,'B-32',2.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title7','short',2005,45,'G',0.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title8','short',2007,55,'G-TCS',4.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title9','long',2018,180,'ONE',4.99);



-- examples to case when
SELECT title,length,
       CASE
           WHEN length > 0
               AND length <= 50 THEN 'short'
           WHEN length > 50
               AND length <= 120 THEN 'medium'
           WHEN length > 120 THEN 'long'
           END duration
FROM film
ORDER BY title;


-- examples to case when with aggregation
SELECT SUM(CASE
               WHEN rental_rate = 0.99 THEN 1
               ELSE 0
            END) AS "ECONOMY",
       SUM(CASE
               WHEN rental_rate = 2.99 THEN 1
               ELSE 0
           END) AS "Mass",
       SUM(CASE
               WHEN rental_rate = 4.99 THEN 1
               ELSE 0
           END) AS "Premium"
FROM film;



-- examples to case when
SELECT title,rating,
       CASE rating
           WHEN 'G' THEN 'g_category'
           WHEN 'G-TCS' THEN 'tcs_category'
           WHEN 'B-32' THEN 'b_32_category'
           WHEN 'TWO' THEN 'two_category'
           ELSE 'not_found'
           END "rating_description"
FROM film;


-- common_table_expression with usually used on subQuery and hard query complex one-time request(not only)
WITH cte_film AS(
    SELECT id,title,
           (CASE
                WHEN length  <= 50 THEN 'short'
                WHEN length > 50 AND length <= 120 THEN 'medium'
                ELSE 'long'
               END) avarage_length
    FROM film
)

SELECT id,title,avarage_length
FROM
    cte_film
WHERE avarage_length = 'long'
ORDER BY title DESC ;




-- examples for FETCH(LIMIT) sql not supporting (LIMIT) that's why used usually FETCH
SELECT id,title FROM film ORDER BY title
    FETCH FIRST ROWS ONLY ;

-- alternative example for FETCH
SELECT id,title FROM film ORDER BY title
    FETCH FIRST 1 ROW ONLY ;

SELECT id,title FROM film ORDER BY title
    FETCH FIRST 5 ROWS ONLY;

-- offset misses 5 rows and printed 2 row only
SELECT id,title FROM film ORDER BY title
OFFSET 5
    FETCH FIRST 2 ROWS ONLY ;





DROP TABLE film;
