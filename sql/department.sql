create table department (
    code varchar(32) primary key,
    name varchar(256) not null,
    upper_code varchar(32),
    start_date date not null,
    end_date date
)
;