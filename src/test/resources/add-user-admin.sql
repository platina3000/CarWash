delete from carwashtest.user_role;
delete from carwashtest.users;

insert into carwashtest.users(id, active, create_date, email, lastname, name, password, phone, secondname) values (1,true,'98-12-31 11:30:45','admin@admin.admin','admin','admin','$2a$08$SITilNXD6Bl8nMfL/N8k6ORX6aG4asbj8xzsJ6fIfSmpaA6qS2yXi','123','qwe');
insert into carwashtest.user_role(user_id, roles) values (1,'ROLE_ADMIN');
