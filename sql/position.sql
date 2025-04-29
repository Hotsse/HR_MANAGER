create table position (
    code varchar(32) primary key,
    name varchar(256) not null,
    level int not null,
    start_date date not null,
    end_date date
)
;