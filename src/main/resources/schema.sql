create table user
(
    name varchar(255) not null,
    score integer,
    bio varchar(255),
    max_price double not null,
    primary key(name)
);

create table wishlist_items
(
    user varchar(255) not null,
    name varchar(255) not null,
    url varchar(255),
    price double not null,
    primary key(user)
)