CREATE TABLE `actor`(
	actor_id int NOT NULL AUTO_INCREMENT,
    actor_full_name varchar(45) NOT NULL,
    actor_birth_date int NOT NULL,
    PRIMARY KEY (actor_id)
);

CREATE TABLE `film` (
	film_id int NOT NULL AUTO_INCREMENT,
    film_name varchar(45) NOT NULL,
    film_release_date NOT NULL,
    film_country NOT NULL,
    PRIMARY KEY (`film_id`)
);

CREATE TABLE `film_actor` (
	film_id int NOT NULL,
    actor_id int NOT NULL
);

CREATE TABLE `film_producer` (
	film_id int NOT NULL,
    producer_fullname varchar(45) NOT NULL
);

INSERT INTO `films` (film_id, film_name, film_release_date, film_country) VALUES (1, 'Film 1', 2010, 'A');
INSERT INTO `films` (film_id, film_name, film_release_date, film_country) VALUES (2, 'Film 2', 2010, 'B');
INSERT INTO `films` (film_id, film_name, film_release_date, film_country) VALUES (3, 'Film 3', 2010, 'C');
INSERT INTO `films` (film_id, film_name, film_release_date, film_country) VALUES (4, 'Film 4', 2010, 'D');
INSERT INTO `films` (film_id, film_name, film_release_date, film_country) VALUES (5, 'Film 5', 2010, 'E');

INSERT INTO `actors` (actor_id, actor_fullname, actor_birth_date) VALUES (1, 'Vasya 1', 1994);
INSERT INTO `actors` (actor_id, actor_fullname, actor_birth_date) VALUES (2, 'Vasya 2', 1977);
INSERT INTO `actors` (actor_id, actor_fullname, actor_birth_date) VALUES (3, 'Vasya 3', 1988);
INSERT INTO `actors` (actor_id, actor_fullname, actor_birth_date) VALUES (4, 'Vasya 4', 2000);
INSERT INTO `actors` (actor_id, actor_fullname, actor_birth_date) VALUES (5, 'Vasya 5', 1999);
INSERT INTO `actors` (actor_id, actor_fullname, actor_birth_date) VALUES (6, 'Vasya 6', 1984);
INSERT INTO `actors` (actor_id, actor_fullname, actor_birth_date) VALUES (7, 'Vasya 7', 1978);

INSERT INTO `film_actors` (film_id, actor_id) VALUES (1, 2);
INSERT INTO `film_actors` (film_id, actor_id) VALUES (1, 3);
INSERT INTO `film_actors` (film_id, actor_id) VALUES (1, 5);
INSERT INTO `film_actors` (film_id, actor_id) VALUES (2, 1);
INSERT INTO `film_actors` (film_id, actor_id) VALUES (2, 2);
INSERT INTO `film_actors` (film_id, actor_id) VALUES (3, 3);
INSERT INTO `film_actors` (film_id, actor_id) VALUES (3, 5);
INSERT INTO `film_actors` (film_id, actor_id) VALUES (3, 6);
INSERT INTO `film_actors` (film_id, actor_id) VALUES (4, 2);

INSERT INTO `film_producers` (film_id, producer_fullname) VALUES (1, 'Alex 1');
INSERT INTO `film_producers` (film_id, producer_fullname) VALUES (2, 'Alex 2');
INSERT INTO `film_producers` (film_id, producer_fullname) VALUES (2, 'Vasya 1');
INSERT INTO `film_producers` (film_id, producer_fullname) VALUES (3, 'Vasya 4');
INSERT INTO `film_producers` (film_id, producer_fullname) VALUES (4, 'Alex 5');
INSERT INTO `film_producers` (film_id, producer_fullname) VALUES (5, 'Vasya 3');
