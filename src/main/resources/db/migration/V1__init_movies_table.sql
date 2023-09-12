drop table if exists movies;
create table movies (
                        id int primary key auto_increment,
                        title varchar(100) not null,
                        description varchar(100),
                        productionYear smallint
);