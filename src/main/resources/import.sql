
CREATE DATABASE IF NOT EXISTS ConferenceCalender;


create table t_conference
(
    id                   bigint       not null
        primary key,
    conference_date_time datetime(6)  null,
    conference_name      varchar(255) null,
    user_id              bigint       null,
    conference_topic     varchar(255) null,
    status               int          null,
    conference_duration  int          null,
    constraint FK9uao4k59iq65sm3st1bqup6iq
        foreign key (user_id) references conferencecalender.t_user (id),
    check (`status` between 0 and 2)
);

INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (1, '2024-02-14 09:00:00.000000', 'Details In Life', null, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (2, '2024-02-14 10:00:00.000000', 'Architecting Your Codebase', 1, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (3, '2024-02-14 15:00:00.000000', 'Overdoing it in Python', 1, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (4, '2024-02-14 16:30:00.000000', 'Flavors of Concurrency in Java', 1, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (5, '2024-02-14 15:45:00.000000', 'Ruby Errors from Mismatched Gem Versions', 1, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (6, '2024-02-14 11:00:00.000000', 'JUnit 5 - Shaping the Future of Testing on the JVM', 1, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (7, '2024-02-14 13:00:00.000000', 'Cloud Native Java', 1, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (8, '2024-02-14 14:00:00.000000', 'Communicating Over Distance', 1, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (9, '2024-02-14 14:45:00.000000', 'AWS Technical Essentials', null, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (10, '2024-02-14 09:00:00.000000', 'Continuous Delivery ', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (11, '2024-02-14 09:30:00.000000', 'Monitoring Reactive Applications ', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (12, '2024-02-14 15:30:00.000000', 'Pair Programming vs Noise', null, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (13, '2024-02-14 09:00:00.000000', 'Rails Magic', null, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (14, '2024-02-14 10:00:00.000000', 'Microservices "Just Right"', null, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (15, '2024-02-14 16:15:00.000000', 'Clojure Ate Scala (on my project) ', null, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (16, '2024-02-14 10:00:00.000000', 'Perfect Scalability ', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (17, '2024-02-14 10:30:00.000000', 'Apache Spark', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (18, '2024-02-14 11:00:00.000000', 'Async Testing on JVM', null, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (19, '2024-02-14 11:00:00.000000', 'A World Without HackerNews', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (20, '2024-02-14 11:30:00.000000', 'User Interface CSS in Apps', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (21, '2024-02-14 13:00:00.000000', 'Mockito library', null, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (22, '2024-02-14 14:00:00.000000', 'Microservices', 1, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (23, '2024-02-14 10:00:00.000000', 'Circuit Braket', 1, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (24, '2024-02-14 16:45:00.000000', 'Java 8', 1, null, 1, 5);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (25, '2024-02-14 10:45:00.000000', 'Java 11', 1, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (26, '2024-02-14 15:00:00.000000', 'Integration Test', 1, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (27, '2024-02-14 09:00:00.000000', 'React Boot Camp 1', 1, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (28, '2024-02-14 09:00:00.000000', 'React Boot Camp 2', 1, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (29, '2024-02-14 09:45:00.000000', 'React Boot Camp 3', null, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (30, '2024-02-14 16:50:00.000000', 'CI/CD ', null, null, 1, 5);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (31, '2024-02-14 11:30:00.000000', 'Grafana', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (32, '2024-02-14 10:30:00.000000', 'Kibana', null, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (33, '2024-02-14 13:00:00.000000', 'OpenSearch', null, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (34, '2024-02-14 14:00:00.000000', 'Elastic Search', null, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (35, '2024-02-14 11:15:00.000000', 'Rest API', null, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (36, '2024-02-14 14:45:00.000000', 'MySQL ', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (37, '2024-02-14 16:55:00.000000', 'Intellij IDEA', null, null, 1, 5);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (38, '2024-02-14 10:00:00.000000', 'SAGA Pattern', null, null, 1, 5);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (39, '2024-02-14 15:15:00.000000', 'Orchestration', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (40, '2024-02-14 15:45:00.000000', 'Create Spring Boot Application ', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (41, '2024-02-14 16:15:00.000000', 'MSSQL ', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (42, '2024-02-14 14:00:00.000000', 'SOLID ', null, null, 1, 45);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (43, '2024-02-14 15:00:00.000000', 'AutomationTest', null, null, 1, 60);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (44, '2024-02-14 09:00:00.000000', 'Java 17', null, null, 1, 30);
INSERT INTO ConferenceCalender.t_conference (id, conference_date_time, conference_name, user_id, conference_topic, status, conference_duration) VALUES (45, '2024-02-14 09:30:00.000000', 'Async operations', null, null, 1, 30);


create table t_conference_seq
(
    next_val bigint null
);

INSERT INTO ConferenceCalender.t_conference_seq (next_val) VALUES (201);


create table t_user
(
    id       bigint       not null
        primary key,
    country  varchar(255) null,
    job      varchar(255) null,
    lastname varchar(255) null,
    name     varchar(255) null
);

INSERT INTO ConferenceCalender.t_user (id, country, job, lastname, name) VALUES (1, 'Turkey', 'developer', 'imren', 'sena');
INSERT INTO ConferenceCalender.t_user (id, country, job, lastname, name) VALUES (2, null, null, null, null);
INSERT INTO ConferenceCalender.t_user (id, country, job, lastname, name) VALUES (3, null, null, null, null);
INSERT INTO ConferenceCalender.t_user (id, country, job, lastname, name) VALUES (4, 'r', null, 'w', 'q');
INSERT INTO ConferenceCalender.t_user (id, country, job, lastname, name) VALUES (52, 'h', null, 'f', 'rd');
INSERT INTO ConferenceCalender.t_user (id, country, job, lastname, name) VALUES (53, 'g', null, 'c', 'd');
INSERT INTO ConferenceCalender.t_user (id, country, job, lastname, name) VALUES (102, 'g', null, 'c', 'd');


create table t_user_seq
(
    next_val bigint null
);

INSERT INTO ConferenceCalender.t_user_seq (next_val) VALUES (201);

