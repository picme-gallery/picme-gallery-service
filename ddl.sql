create sequence hibernate_sequence start with 1 increment by 1;
create table event
(
    event_id    bigint       not null,
    address     varchar(255),
    description varchar(255),
    event_time  timestamp    not null,
    latitude    double,
    longitude   double,
    name        varchar(255) not null,
    password    varchar(255) not null,
    updated     timestamp    not null,
    primary key (event_id)
);
create table photo
(
    photo_id  bigint not null,
    caption   varchar(255),
    latitude  double,
    longitude double,
    uploaded  timestamp,
    event_id  bigint not null,
    user_id   bigint not null,
    primary key (photo_id)
);
create table user_event
(
    user_id  bigint not null,
    event_id bigint not null
);
create table user_profile
(
    user_id      bigint not null,
    connected    timestamp,
    created      timestamp,
    display_name varchar(255),
    oauth_key    varchar(255),
    updated      timestamp,
    primary key (user_id)
);
create index IDXnhpa18bb0mxa83gpbas6eg38s on event (event_time);
create index IDX42lv5v1fuasicrdjkvf3cye5f on event (updated);
create index IDXs0oborte6qspqp6uwkmfmjlr7 on photo (uploaded);
create unique index UK6f815wi5o4jq8p1q1w63o4mhd on user_profile (oauth_key);
alter table photo
    add constraint FKdbadfit090kvl159gty4hl5so foreign key (event_id) references event;
alter table photo
    add constraint FK8djnj0mx8yc01e8wfecmku9yo foreign key (user_id) references user_profile;
alter table user_event
    add constraint FKspe8srtv69gubpphvrnd7wekt foreign key (event_id) references event;
alter table user_event
    add constraint FKpukmmpe4yd7rpwwfcyf7f8jtu foreign key (user_id) references user_profile;