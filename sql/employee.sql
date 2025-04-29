create table employee (
    seq bigint primary key auto_increment,
    account_id varchar(256) unique not null,
    password char(64),
    name varchar(256) not null,
    dept_code varchar(32) not null,
    position_code varchar(32) not null,
    start_date date not null,
    end_date date
)
;