
CREATE DATABASE IF NOT EXISTS ConferenceCalender;

create table t_conference
(
    id                   bigint       not null,
    conference_date_time datetime(6)  null,
    conference_name      varchar(255) null,
    time                 varchar(255) null,
    user_id              bigint       null,
    primary key (id),
    constraint FK9uao4k59iq65sm3st1bqup6iq
        foreign key (user_id) references conferencecalender.t_user (id)
);

create table t_conference_seq
(
    next_val bigint null
);

create table t_user
(
    id       bigint       not null,
    country  varchar(255) null,
    job      varchar(255) null,
    lastname varchar(255) null,
    name     varchar(255) null,
    primary key (id)
);

create table t_user_seq
(
    next_val bigint null
);


create table t_user
(
    id       bigint       not null,
    country  varchar(255) null,
    job      varchar(255) null,
    lastname varchar(255) null,
    name     varchar(255) null,
    primary key (id)
);

