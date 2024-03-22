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

create table goods
(
    id          int auto_increment
        primary key,
    name        varchar(60)  not null,
    description varchar(220) null,
    brand       varchar(20)  null,
    photo       json         null,
    likes       int          null
);

