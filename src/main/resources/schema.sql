create table user
(
    id integer not null,
    name varchar(255) not null,
    bio varchar(255),
    max_price double not null,
    primary key(id)
);

create table wishlist_items
(
    user varchar(255) not null,
    name varchar(255) not null,
    price double not null,
    primary key(user)
)