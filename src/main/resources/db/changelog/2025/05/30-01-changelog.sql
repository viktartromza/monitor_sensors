create table roles
(
    id      BIGSERIAL   PRIMARY KEY,
    name    VARCHAR(30) NOT NULL
);

create table users
(
    id      BIGSERIAL   PRIMARY KEY,
    username   VARCHAR(30) NOT NULL,
    password   VARCHAR(100) NOT NULL,
    role_id    BIGINT NOT NULL,
    constraint foreign_key_name
        foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('Administrator'),
       ('Viewer');

insert into users(username, password, role_id)
values ('admin', '$2a$10$A38ZwsOgysquyIcrXeO1Du2cYS1N69hviJd3obL4FZCvi4.FaC39y',1),
       ('user', '$2a$10$TSNb7XKUniaTYCYdP5yJ7OSNTykcr1tqw0yZCj7oHSPoob6TG4Nuy',2);