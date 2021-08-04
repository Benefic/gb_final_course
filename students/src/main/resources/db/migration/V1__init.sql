create table students
(
    id   bigserial primary key,
    name varchar(255),
    age  integer
);

insert into students (name, age)
values ('Aleksandr', 25),
       ('Vladimir', 23),
       ('Dmitriy', 27),
       ('Aleksey', 31),
       ('Aleksandra', 34),
       ('Mariya', 28),
       ('Nataliya', 22),
       ('Vladislav', 23),
       ('Cucumber', 30);
