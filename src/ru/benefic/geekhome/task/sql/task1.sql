--ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
--Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
select M1.NAME       as `фильм 1`,
       S1.START_TIME as `время начала`,
       M1.DURATION   as `длительность`,
       M2.NAME       as `фильм 2`,
       S2.START_TIME as `время начала`,
       M2.DURATION   as `длительность`
from MOVIE M1
         inner join SESSION S1 on M1.ID = S1.MOVIE_ID
         inner join SESSION S2
                    on S2.START_TIME >= S1.START_TIME and dateadd(MINUTE, M1.DURATION, S1.START_TIME) > S2.START_TIME
         inner join MOVIE M2 on S2.ID = M2.ID
where M1.ID <> M2.ID
ORDER BY S1.START_TIME;


--перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
--Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
select M1.NAME                                                                      as `фильм 1`,
       S1.START_TIME                                                                as `время начала`,
       M1.DURATION                                                                  as `длительность`,
       S2.START_TIME                                                                as `время начала второго фильма`,
       datediff(MINUTE, dateadd(MINUTE, M1.DURATION, S1.START_TIME), S2.START_TIME) as `длительность перерыва`
from MOVIE M1
         inner join SESSION S1 on M1.ID = S1.MOVIE_ID
         inner join SESSION S2
                    on S2.ID = (
                        select id
                        from SESSION S
                        where S1.START_TIME < S.START_TIME
                        order by S.START_TIME
                        limit 1)
                        and datediff(MINUTE, dateadd(MINUTE, M1.DURATION, S1.START_TIME), S2.START_TIME) >= 30
         inner join MOVIE M2 on S2.ID = M2.ID
where M1.ID <> M2.ID
ORDER BY datediff(MINUTE, dateadd(MINUTE, M1.DURATION, S1.START_TIME), S2.START_TIME) desc;


--список фильмов, для каждого — с указанием общего числа посетителей за все время,
--среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
--Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
with result as (select M.NAME,
                       cast(count(t.ID) as int)                           as ticket_count,
                       cast(count(t.ID) as double) / count(distinct S.ID) as avg,
                       cast(sum(S.PRICE) as int)                          as sum_price
                from MOVIE M
                         inner join SESSION S on M.ID = S.MOVIE_ID
                         inner join TICKET T on S.ID = T.SESSION_ID
                group by M.NAME
                order by sum(S.PRICE) desc)
select *
from result
union all
select 'Итого', sum(ticket_count), avg(avg), sum(sum_price)
from result;


--число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
--с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
select '9:00-15:00' as `interval`, count(T.ID), cast(sum(S.PRICE) as int)
from SESSION S
         inner join TICKET T on S.ID = T.SESSION_ID
where hour(S.START_TIME) >= 9
  and hour(S.START_TIME) < 15
union all
select '15:00-18:00' as `interval`, count(T.ID), cast(sum(S.PRICE) as int)
from SESSION S
         inner join TICKET T on S.ID = T.SESSION_ID
where hour(S.START_TIME) >= 15
  and hour(S.START_TIME) < 18
union all
select '18:00-15:00' as `interval`, count(T.ID), cast(sum(S.PRICE) as int)
from SESSION S
         inner join TICKET T on S.ID = T.SESSION_ID
where hour(S.START_TIME) >= 18
  and hour(S.START_TIME) < 21
union all
select '21:00-00:00' as `interval`, count(T.ID), cast(sum(S.PRICE) as int)
from SESSION S
         inner join TICKET T on S.ID = T.SESSION_ID
where hour(S.START_TIME) >= 18
;
