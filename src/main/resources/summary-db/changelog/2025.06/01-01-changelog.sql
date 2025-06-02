CREATE TABLE report
(
    id              UUID         PRIMARY KEY,
    date            DATE         NOT NULL ,
    pressure        BIGINT       DEFAULT 0,
    voltage         BIGINT       DEFAULT 0,
    temperature     BIGINT       DEFAULT 0,
    humidity        BIGINT       DEFAULT 0,
    overall         BIGINT       DEFAULT 0
);
