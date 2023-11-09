insert into member(id, firstname, lastname, phone_number,
                   email_address, license_plate_number, issuing_country, registration_date, membership_level, streetName, streetNumber, postalCode, postalLabel)
values (nextval('member_seq'), 'Zineb', 'El tuti', '0123456789',
        'zineb.eltuti@mc.be', '1-ABC-111', 'BE', '2023-11-07', 'BRONZE', 'chien vert', '17A', 1150, 'Woluwe St Pierre');

insert into member(id, firstname, lastname, phone_number,
                   email_address, license_plate_number, issuing_country, registration_date, membership_level,streetName, streetNumber, postalCode, postalLabel)
values (nextval('member_seq'), 'Eli', 'kovic', '0987415266',
        'eli.kovic@mc.be', '1-BBB-111', 'BE', '2023-11-07', 'GOLD', 'chien rouge', '18', 1200, 'Woluwe St lambert');
insert into member(id, firstname, lastname, phone_number,
                   email_address, license_plate_number, issuing_country, registration_date, membership_level,streetName, streetNumber, postalCode, postalLabel)
values (nextval('member_seq'), 'aaa', 'bbb', '0123456789',
        'aaa.bbb@mc.be', '1-CFR-123', 'FR', '2023-11-07', 'SILVER', 'chien orange', '58', 1000, 'BRUSSEL');


insert into users(id,password,role,user_id,fk_member_id)
values (nextval('users_seq'), '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4' ,'MANAGER','manager',1);

insert into users(id,password,role,user_id,fk_member_id)
values (nextval('users_seq'), '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4' ,'MEMBER','member',2);

insert into users(id,password,role,user_id,fk_member_id)
values (nextval('users_seq'), '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4' ,'MEMBER','mem',3);