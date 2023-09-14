create table ratings
(
    id        int primary key auto_increment,
    rating    smallint     not null,
    date      datetime,
    user_name varchar(100) not null,
    movie_id  int references movies (id)
)