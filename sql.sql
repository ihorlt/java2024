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
    name        varchar(60) not null,
    description text        null,
    brand       varchar(20) null,
    photo       json        null,
    likes       int         null
);

create table webapp.types
(
    id         int auto_increment
        primary key,
    name       varchar(15) not null,
    deleted_at date        null
);

create table webapp.goods_types
(
    id      int auto_increment
        primary key,
    good_id int null,
    type_id int null,

    constraint goods_types_goods_id_fk
        foreign key (good_id) references webapp.goods (id)
            on update set null on delete set null,
    constraint goods_types_types_id_fk
        foreign key (type_id) references webapp.types (id)
            on update set null on delete set null
);

create table prices
(
    id            int auto_increment
        primary key,
    from_supplier double not null,
    for_client    double not null,
    created_at    date   not null,
    deleted_at    date   null,
    good_id       int    null,
    income        int    null,
    outcome       int    null,
    constraint prices_goods_id_fk
        foreign key (good_id) references goods (id)
            on update set null on delete set null
);

create table webapp.orders
(
    id         int auto_increment
        primary key,
    user_id    int              not null,
    price_id   int              not null,
    is_paid    bit default b'0' not null,
    created_at datetime         not null,
    deleted_at datetime         null
);



