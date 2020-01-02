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
    `rating`     int unsigned,

    primary key (`id`)
);

create table `users`
(
    `id`   int unsigned not null,

    primary key (`id`)
);

create table `media_user`
(
    `user_id`  int unsigned not null,
    `media_id` int unsigned not null,
    `rating`   int unsigned,
    `watched`  timestamp    null default null,

    primary key (`user_id`, `media_id`),

    foreign key (`user_id`) references `users` (`id`),
    foreign key (`media_id`) references `medias` (`id`)
);

insert into `users`
    (`id`)
values (1),
       (2),
       (3);

load data local infile '/docker-entrypoint-initdb.d/out.csv' into table `medias` fields terminated by '\t' lines terminated by '\n' (`title`, @release, `duration`, `main_genre`, `rating`) set `release` = FROM_UNIXTIME(@release);

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

create sequence `hibernate_sequence`;

SELECT "Init finished";