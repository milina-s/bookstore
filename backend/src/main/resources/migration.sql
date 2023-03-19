drop schema if exists public cascade;
create schema public;


create table users
(
    id         serial primary key,
    email      varchar(255) not null,
    password   varchar(255) not null,
    role       varchar(255) not null,
    first_name varchar(255),
    last_name  varchar(255),
    phone      varchar(255),
    address    text,
    created_at timestamp default now(),
    updated_at timestamp
);

create table categories
(
    id                 serial primary key,
    name_ua            varchar(255),
    name_en            varchar(255),
    parent_category_id int
);

-- create table subcategories
-- (
--     id          serial primary key,
--     name_ua     varchar(255) not null,
--     name_en     varchar(255),
--     category_id int          not null,
--     foreign key (category_id) references categories (id)
-- );

create table publishers
(
    id       serial primary key,
    name_en  varchar(255) not null,
    name_ua  varchar(255),
    about_ua text,
    about_en text,
    website  varchar(255)
);

create table authors
(
    id                 serial primary key,
    name_en            varchar(255) not null,
    name_ua            varchar(255),
    full_name_original varchar(255),
    birth_date         date,
    death_date         date,
    about_ua           text,
    about_en           text,
    image              varchar(255)
);

create table series
(
    id           serial primary key,
    name         varchar(255) not null,
    publisher_id int          not null,
    foreign key (publisher_id) references publishers (id)
);

create table books
(
    isbn                varchar(255) primary key,
    title               varchar(255)   not null,
    title_original      varchar(255),
    description         text,
    image               varchar(255),
    price               decimal(10, 2) not null,
    price_with_discount decimal(10, 2),
    year                int,
    type                varchar(255)   not null,
    language            varchar(255),
    cover               varchar(255),
    pages               int,
    format              varchar(255),
    quantity            int default 0,
    popularity          int default 0,
    translator          varchar(255),
    rating              decimal(5, 2),
    author_id           int,
    category_id         int,
    publisher_id        int,
    series_id           int,
    foreign key (author_id) references authors (id),
    foreign key (category_id) references categories (id),
    foreign key (publisher_id) references publishers (id),
    foreign key (series_id) references series (id)
);

create table orders
(
    id          serial primary key,
    customer_id int not null,
    manager_id  int,
    status      varchar(255) default 'CREATED',
    address     text,
    created_at  timestamp    default now(),
    updated_at  timestamp,
    foreign key (customer_id) references users (id),
    foreign key (manager_id) references users (id)
);

create table order_book
(
    order_id            int            not null,
    book_isbn           varchar(255)   not null,
    quantity            int default 1,
    price               decimal(10, 2) not null,
    price_with_discount decimal(10, 2),
    constraint order_book_key primary key (order_id, book_isbn),
    foreign key (order_id) references orders (id),
    foreign key (book_isbn) references books (isbn)
);

create table favorites
(
    user_id   int          not null,
    book_isbn varchar(255) not null,
    constraint favorites_key primary key (user_id, book_isbn),
    foreign key (user_id) references users (id),
    foreign key (book_isbn) references books (isbn)
);

create table reviews
(
    id         serial primary key,
    rating     int,
    text       text,
    user_id    int,
    book_isbn  varchar(255) not null,
    check ( rating >= 1 and rating <= 5 ),
    created_at timestamp default now(),
    foreign key (user_id) references users (id),
    foreign key (book_isbn) references books (isbn)
);

