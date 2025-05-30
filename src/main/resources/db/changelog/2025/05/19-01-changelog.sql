CREATE TABLE type
(
    id              BIGSERIAL       PRIMARY KEY,
    name            VARCHAR(15)     NOT NULL
);

CREATE TABLE unit
(
    id              BIGSERIAL       PRIMARY KEY,
    name            VARCHAR(15)     NOT NULL,
    type            BIGINT     NOT NULL,
    constraint unit_fk_1
        foreign key (type) references type (id)
);

CREATE TABLE sensor
(
    id              UUID         PRIMARY KEY,
    name            VARCHAR(30)  NOT NULL,
    model           VARCHAR(15)  NOT NULL,
    range           int8range,
    type         BIGINT,
    unit         BIGINT,
    location        VARCHAR(40) NOT NULL,
    description     VARCHAR(200) NOT NULL,
    constraint sensor_fk_1
        foreign key (type) references type (id),
    constraint sensor_fk_2
        foreign key (unit) references unit (id)
);
