-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
SET search_path TO parkshark;

insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Top Level', 'Original Top Level', 'Director', null);
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Sub Level 1a', 'Original Sub Level 1a', 'Director Lvl1a', 1);
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Sub Level 1b', 'Original Sub Level 1b', 'Director Lvl1b', 1);
insert into division (id, name, originalname, director, fk_division_id)
values (nextval('division_seq'), 'Sub Level 2', 'Original Sub Level 2', 'Director Lvl2', 2);

