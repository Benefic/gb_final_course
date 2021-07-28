CREATE DATABASE IF NOT EXISTS `cinema`;
USE `cinema`;

CREATE TABLE `durations`
(
    `idduration` int unsigned NOT NULL AUTO_INCREMENT,
    `duration`   int unsigned NOT NULL DEFAULT  '90' ,
    PRIMARY KEY (`idduration`),
    UNIQUE KEY `idduration_UNIQUE` (`idduration`)
);

INSERT INTO `durations`
VALUES (1, 60),
       (2, 90),
       (3, 120);

CREATE TABLE `movies`
(
    `idmovie`  int unsigned  NOT NULL AUTO_INCREMENT,
    `name`     varchar(1000) NOT NULL DEFAULT '< unnamed >',
    `duration` int unsigned  NOT NULL,
    PRIMARY KEY (`idmovie`),
    UNIQUE KEY `idmovie_UNIQUE` (`idmovie`),
    KEY `duration_idx` (`duration`),
    CONSTRAINT `duration` FOREIGN KEY (`duration`) REFERENCES `durations` (`idduration`)
);

INSERT INTO `movies`
VALUES (1, 'Залечь на дно в Брюгге', 2),
       (2, 'Доктор Стрейнджлав, или Как я перестал бояться и полюбил бомбу', 2),
       (3, 'Человек с Земли', 1),
       (4, 'Космическая одиссея 2001 года', 3),
       (5, 'Дракула Брэма Стокера', 3);

CREATE TABLE `price`
(
    `idprice` int unsigned            NOT NULL AUTO_INCREMENT,
    `price`   decimal(10, 2) unsigned NOT NULL DEFAULT ' 0.00 ',
    `session` int unsigned            NOT NULL,
    PRIMARY KEY (`idprice`),
    UNIQUE KEY `idprice_UNIQUE` (`idprice`),
    KEY `session_idx` (`session`),
    CONSTRAINT `session` FOREIGN KEY (`session`) REFERENCES `sessions` (`idsessions`)
);

INSERT INTO `price`
VALUES (1, 500.00, 1),
       (2, 500.00, 2),
       (3, 500.00, 3),
       (4, 500.00, 4),
       (5, 500.00, 5),
       (6, 700.00, 6),
       (7, 700.00, 7),
       (8, 700.00, 8),
       (9, 800.00, 9),
       (10, 800.00, 10),
       (11, 900.00, 11),
       (12, 900.00, 12),
       (13, 1000.00, 13),
       (14, 1000.00, 14),
       (15, 1000.00, 15);

CREATE TABLE `sessions`
(
    `idsessions` int unsigned NOT NULL AUTO_INCREMENT,
    `datetime`   datetime     NOT NULL,
    `movie`      int unsigned NOT NULL,
    PRIMARY KEY (`idsessions`),
    UNIQUE KEY `idsessions_UNIQUE` (`idsessions`),
    KEY `movie_idx` (`movie`),
    CONSTRAINT `movie` FOREIGN KEY (`movie`) REFERENCES `movies` (`idmovie`)
);

INSERT INTO `sessions`
VALUES (1, '2021-07-23 09:00:00', 1),
       (2, '2021-07-23 11:00:00', 2),
       (3, '2021-07-23 13:00:00', 3),
       (4, '2021-07-23 15:00:00', 4),
       (5, '2021-07-23 16:00:00', 5),
       (6, '2021-07-24 12:00:00', 1),
       (7, '2021-07-25 12:00:00', 2),
       (8, '2021-07-25 11:00:00', 3),
       (9, '2021-07-25 22:00:00', 4),
       (10, '2021-07-26 10:00:00', 5),
       (11, '2021-07-26 12:30:00', 1),
       (12, '2021-07-27 10:30:00', 2),
       (13, '2021-07-27 12:30:00', 3),
       (14, '2021-07-27 15:30:00', 4),
       (15, '2021-07-27 18:30:00', 5);

CREATE TABLE `tickets`
(
    `number`  int unsigned NOT NULL AUTO_INCREMENT,
    `session` int unsigned NOT NULL,
    PRIMARY KEY (`number`),
    UNIQUE KEY `number_UNIQUE` (`number`),
    KEY `session_idx` (`session`),
    CONSTRAINT `sessionid` FOREIGN KEY (`session`) REFERENCES `sessions` (`idsessions`)
);

INSERT INTO `tickets`
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 3),
       (5, 3),
       (6, 3),
       (7, 5),
       (8, 5),
       (9, 5),
       (10, 10),
       (11, 10),
       (12, 12),
       (13, 12);
