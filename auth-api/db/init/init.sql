show databases;
drop database if exists `authDBidir`;
create database `authDBidir`;
use `authDBidir`;

create table if not exists `users`
(
    `id`           int unsigned not null auto_increment,
    `username`     varchar(250) not null,
    `password`     varchar(250) not null,
    `email`        varchar(250) not null,
    `role`         varchar(10)  not null,
    `member_since` timestamp    not null default current_timestamp,

    primary key (`id`),

    constraint `email_uq` unique (`email`)
);

insert into `users`
(`username`,
 `password`,
 `role`,
 `email`)
values ('pete842',
        '$31$5$8f91zmgQBh7LUAM1-29me1KhHFQJekxK1872YYzNO2s',
        'admin',
        'pete842@mail.com'),
       ('jzaehrin',
        '$31$5$8f91zmgQBh7LUAM1-29me1KhHFQJekxK1872YYzNO2s',
        'admin',
        'jzaehrin@mail.com'),
       ('capito27',
        '$31$5$8f91zmgQBh7LUAM1-29me1KhHFQJekxK1872YYzNO2s',
        'user',
        'capito27@mail.com');

select * from `users`;

create sequence `hibernate_sequence`;
