CREATE TABLE film(
                     id BIGSERIAL PRIMARY KEY ,
                     title VARCHAR(128) NOT NULL ,
                     description VARCHAR(128) NOT NULL ,
                     release_year INT ,
                     length BIGINT,
                     rating VARCHAR(32),
                     rental_rate NUMERIC);




INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title1','short',2000,50,'G',0.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title2','short',2000,50,'G-TCS',2.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title3','medium',2012,60,'ONE',0.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title4','medium',2015,70,'M',0.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title5','long',2019,150,'M',2.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title6','long',2013,170,'B-32',2.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title7','short',2005,45,'G',0.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title8','short',2007,55,'G-TCS',4.99);
INSERT INTO  film(title, description, release_year, length, rating,rental_rate) VALUES ('title9','long',2018,180,'ONE',4.99);


CREATE TABLE film_category(
    id BIGSERIAL PRIMARY KEY ,
    last_update DATE,
    film_id BIGINT REFERENCES film(id)
    );


--
SELECT MAX(length)
FROM film
INNER JOIN film_category
USING (id)
GROUP BY film_category.id;



-- subquery using ANY(SOME)
SELECT title
FROM film
WHERE length >= ANY(
    SELECT MAX( length )
    FROM film
             INNER JOIN film_category USING(id)
    GROUP BY film_category.id );



-- used ANY(SOME)
-- x <> ANY (a,b,c)


-- used NOY IN
-- x <> a OR <> b OR x <> c