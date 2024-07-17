-- Table for courses
create table course (
    id bigserial primary key,
    name varchar(100) not null,
    category varchar(100) not null
);

-- Table for profiles
create table profile (
    id bigserial primary key,
    name varchar(100) not null
);

-- Table for users
create table user_account (
    id bigserial primary key,
    name varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(100) not null,
    profile_id bigint not null,
    constraint fk_user_profile_id foreign key(profile_id) references profile(id)
);

-- Table for topics
create table topic (
    id bigserial primary key,
    title varchar(100) not null,
    message varchar(255) not null,
    creation_date timestamp not null default current_timestamp,
    status varchar(100) not null,
    author_id bigint not null,
    course_id bigint not null,
    constraint fk_topic_author_id foreign key(author_id) references user_account(id),
    constraint fk_topic_course_id foreign key(course_id) references course(id)
);

-- Table for responses
create table response (
    id bigserial primary key,
    message varchar(255) not null,
    topic_id bigint not null,
    creation_date timestamp not null default current_timestamp,
    author_id bigint not null,
    solution boolean not null,
    constraint fk_response_topic_id foreign key(topic_id) references topic(id),
    constraint fk_response_author_id foreign key(author_id) references user_account(id)
);
