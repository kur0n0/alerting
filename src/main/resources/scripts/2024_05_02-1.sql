create table user_group(
    user_group_id serial primary key,
    name varchar(255)
);

create table telegramuser(
    user_id serial primary key,
    telegramuser_name varchar(255) unique,
    chat_id varchar(255),
    firstname varchar(255),
    lastname varchar(255),
    user_group_id int4 references user_group(user_group_id)
);

create table message(
    message_id serial primary key,
    title varchar(255),
    message_text text
);

create table message_history(
    message_history_id serial primary key,
    message_id int4 references message(message_id),
    user_group_id int4 references user_group(user_group_id)
);