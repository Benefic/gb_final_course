SELECT
    m.name 'Фильм 1',
    s.datetime 'Время начала',
    d.duration 'Длительность',
    t2.name 'Фильм 2',
    t2.StartTime 'Время начала',
    t2.duration 'Длительность'
FROM
    movies m
        INNER JOIN
    sessions s ON s.movie = m.idmovie
        INNER JOIN
    durations d ON m.duration = d.idduration
        JOIN
    (SELECT
         m.name,
         s.datetime StartTime,
         DATE_ADD(s.datetime, INTERVAL d.duration MINUTE) StopTime,
         d.duration,
         s.idsessions
     FROM
         movies m
             INNER JOIN sessions s ON s.movie = m.idmovie
             INNER JOIN durations d ON m.duration = d.idduration) AS t2 ON s.datetime BETWEEN t2.StartTime AND t2.StopTime
        AND s.idsessions <> t2.idsessions
ORDER BY s.datetime
