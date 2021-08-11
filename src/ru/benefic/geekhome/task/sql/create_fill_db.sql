create table movie
(
    id       int auto_increment primary key,
    name     varchar not null,
    duration int     not null
);
create unique index UI_movie_name on movie (name);

create table session
(
    id         int auto_increment primary key,
    movie_id   int      not null,
    start_time datetime not null,
    price      decimal  not null,
    foreign key (movie_id) references movie (id)
);
create index I_Session_Movie_Id on session (movie_id);
create index I_Session_Start_Time on session (start_time);

create table ticket
(
    id         int auto_increment primary key,
    session_id int not null,
    place      int not null,
    foreign key (session_id) references session (id)
);
create unique index UI_Ticket_Session_Place on ticket (session_id, place);

INSERT INTO movie (name, duration)
VALUES ('Залечь на дно в Брюгге', 90),
       ('Доктор Стрейнджлав, или Как я перестал бояться и полюбил бомбу', 90),
       ('Человек с Земли', 60),
       ('Космическая одиссея 2001 года', 120),
       ('Дракула Брэма Стокера', 120);

INSERT INTO session (movie_id, start_time, price)
VALUES (1, '2021-07-23 09:00:00', 100),
       (2, '2021-07-23 11:00:00', 200),
       (3, '2021-07-23 13:00:00', 300),
       (4, '2021-07-23 15:00:00', 400),
       (5, '2021-07-23 16:00:00', 500),
       (1, '2021-07-24 12:00:00', 100),
       (2, '2021-07-25 12:00:00', 200),
       (3, '2021-07-25 11:00:00', 300),
       (4, '2021-07-25 22:00:00', 400),
       (5, '2021-07-26 10:00:00', 500),
       (1, '2021-07-26 12:30:00', 100),
       (2, '2021-07-27 10:30:00', 200),
       (3, '2021-07-27 12:30:00', 300),
       (4, '2021-07-27 15:30:00', 400),
       (5, '2021-07-27 18:30:00', 500);

insert into ticket (session_id, place)
select id, 1
from session
union
select id, 2
from session
union
select id, 3
from session
union
select id, 4
from session
union
select id, 5
from session
union
select id, 6
from session
union
select id, 7
from session
union
select id, 8
from session;
