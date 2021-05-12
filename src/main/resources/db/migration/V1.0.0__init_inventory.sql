drop table if exists items;
create table items(

    id uuid,
    title text

);

alter table items add constraint item_pk primary key(id);