
-- insert into roles (id,name)
-- values ('2','ROLE_MODERATOR'),
--  ('3','ROLE_ADMIN');

-- insert into users (id, email, password, username)
-- values ('2','ivanov@gmail.com','$2a$10$AD0G0cklp9SGE3oDVb1uAeMx37/kpKmTx99NltRJyTyJSRn3ESVIO','Ivanov'),
--        ('3','sidorov@gmail.com','$2a$10$AD0G0cklp9SGE3oDVb1uAeMx37/kpKmTx99NltRJyTyJSRn3ESVIO','Sidorov');
--
-- insert into user_roles (user_id, role_id)
-- values ('2','2'),
--        ('3','3');


insert into users (id, date_created, email, password, username)
values ('1','2021-04-11T15:36:12.675Z','ivanov@gmail.com','$2a$10$AD0G0cklp9SGE3oDVb1uAeMx37/kpKmTx99NltRJyTyJSRn3ESVIO','Ivanov'),
       ('2','2021-04-11T15:36:12.675Z','sidorov@gmail.com','$2a$10$AD0G0cklp9SGE3oDVb1uAeMx37/kpKmTx99NltRJyTyJSRn3ESVIO','Sidorov');