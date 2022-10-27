insert into roles (role_name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_MODERATOR');

update roles
set creation_date = current_timestamp(6);