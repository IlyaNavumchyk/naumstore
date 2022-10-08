create table if not exists l_user_role
(
    id            bigserial primary key,
    user_id       bigint  not null
        constraint l_user_role_users_id_fk
            references users
            on update cascade on delete cascade,
    role_id       int not null
        constraint l_user_role_roles_id_fk
            references roles
            on update cascade on delete cascade,
    creation_date timestamp(6)
);

create unique index if not exists l_user_role_user_id_role_id_uindex
    on l_user_role (user_id, role_id);