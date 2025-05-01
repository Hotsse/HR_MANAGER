CREATE TABLE employee_work_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    account_id VARCHAR(256) NOT NULL,
    work_date DATE NOT NULL,
    clock_in TIMESTAMP NOT NULL,
    clock_out TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    reason VARCHAR(1024),
    created_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_datetime TIMESTAMP
)
;