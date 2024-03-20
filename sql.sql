create table users
(
    id          int auto_increment,
    email       VARCHAR(40)  not null,
    password    VARCHAR(120) not null,
    displayName varchar(20)  null,
    options     json         null comment 'full user profile',
    created_at  date         null,
    deleted_at  date         null,
    constraint users_pk
        primary key (id),
    constraint users_pk_2
        unique (email)
);

