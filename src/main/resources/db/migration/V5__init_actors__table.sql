create table actors
(
    id        int primary key auto_increment,
    name      varchar(100) not null,
    lastName  varchar(100) not null,
    birthDate date
);