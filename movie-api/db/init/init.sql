drop database if exists `movieDBidir`;
create database `movieDBidir`;
use `movieDBidir`;

create table `medias`
(
    `id`         int unsigned not null auto_increment,
    `title`      varchar(250) not null,
    `release`    timestamp,
    `duration`   int unsigned,
    `main_genre` varchar(250),
    `rating`     tinyint unsigned,

    primary key (`id`)
);

create table `users`
(
    `id`   int unsigned not null,
    `username`  varchar(250) not null,
    `firstname` varchar(250) not null,
    `lastname`  varchar(250) not null,
    `email`     varchar(250) not null,

    primary key (`id`),

    constraint `username_uq` unique (`username`),
    constraint `email_uq` unique (`email`)
);

create table `media_user`
(
    `id`       int unsigned not null auto_increment,
    `user_id`  int unsigned not null,
    `media_id` int unsigned not null,
    `rating`   tinyint unsigned,
    `watched`  timestamp    null default null,

    primary key (`id`),

    foreign key (`user_id`) references `users` (`id`),
    foreign key (`media_id`) references `medias` (`id`),

    constraint uq_association unique (`user_id`, `media_id`)
);

insert into `users`
    (`id`, `username`, `firstname`, `lastname`, `email`)
values (1, 'pete842', 'Pierre', 'Kohler', 'pete842@mail.com'),
       (2, 'jzaehrin', 'Jonathan', 'Zaehringer', 'jzaehrin@mail.com'),
       (3, 'capito27', 'Filipe', 'Fortunato', 'capito27@mail.com');

load data local infile '/home/pete/workspace/AMT/Teaching-HEIGVD-AMT-2019-Project-Two/movie-api/db/init/out.csv' into table `medias` fields terminated by '\t' lines terminated by '\n' (`title`, @release, `duration`, `main_genre`, `rating`) set `release` = FROM_UNIXTIME(@release);

drop procedure if exists insertMediaUser;
delimiter //

create procedure insertMediaUser()
begin
    declare counter int default 0;
    declare continue handler for sqlexception set counter = counter - 1;
    begin
        while counter < 10000
            do
                set counter = counter + 1;
                insert into `media_user`
                (`user_id`,
                 `media_id`)
                values (1,
                        floor(rand() * (1000000 - 1 + 1)) + 1);

                insert into `media_user`
                (`user_id`,
                 `media_id`,
                 `rating`,
                 `watched`)
                values (1,
                        floor(rand() * (1000000 - 1 + 1)) + 1,
                        floor(rand() * (100 - 1 + 1)) + 1,
                        now() - interval floor(rand() * 365) day);
            end while;
    end;
end;
//
delimiter ;

call insertMediaUser;