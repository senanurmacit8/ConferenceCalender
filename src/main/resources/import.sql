
CREATE DATABASE IF NOT EXISTS ConferenceCalender;

create table t_conference
(
    id                   bigint       not null
        primary key,
    conference_date_time datetime(6)  null,
    conference_name      varchar(255) null,
    time                 varchar(255) null,
    user_id              bigint       null,
    duration             varchar(255) null,
    conference_topic     varchar(255) null,
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


--INSERT DUMMY DATA

Architecting Your Codebase 60min
Overdoing it in Python 45min
Flavors of Concurrency in Java 30min
Ruby Errors from Mismatched Gem Versions 45min
JUnit 5 - Shaping the Future of Testing on the JVM 45min
Cloud Native Java lightning
Communicating Over Distance 60min
AWS Technical Essentials 45min
Continuous Delivery 30min
Monitoring Reactive Applications 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Microservices "Just Right" 60min
Clojure Ate Scala (on my project) 45min
Perfect Scalability 30min
Apache Spark 30min
Async Testing on JVM 60min
A World Without HackerNews 30min
User Interface CSS in Apps 30min

