create table medium_table
(
    x integer
);

create table medium_parent_table
(
    id   integer primary key,
    data text
);

create table medium_users_table
(
    id integer primary key
);

create table medium_profile_table
(
    id   integer primary key references medium_users_table (id),
    data text
);

create table medium_child_table
(
    id        integer primary key,
    parent_id integer references medium_parent_table (id),
    data      text
);

create table medium_first_partner_table
(
    id integer primary key
);

create table medium_second_partner_table
(
    id integer primary key
);

create table medium_business_table
(
    id             integer primary key,
    first_partner  integer references medium_first_partner_table (id),
    second_partner integer references medium_second_partner_table (id),
    data text
);

insert into medium_table (x)
select generate_series(1, 10000);

insert into medium_parent_table (id, data)
select generate_series,
       md5(random()::text)
from generate_series(1, 10000);

insert into medium_child_table (id, parent_id, data)
select generate_series,
       floor(random() * 10000) + 1,
       md5(random()::text)
from generate_series(1, 10000);

insert into medium_users_table (id)
select generate_series(1, 10000);

insert into medium_profile_table (id, data)
select generate_series,
       md5(random()::text)
from generate_series(1, 10000);

insert into medium_first_partner_table (id)
select generate_series(1, 10000);

insert into medium_second_partner_table (id)
select generate_series(1, 10000);

insert into medium_business_table (id, first_partner, second_partner, data)
select generate_series,
       floor(random() * 10000) + 1,
       floor(random() * 10000) + 1,
       md5(random()::text)
from generate_series(1, 10000);