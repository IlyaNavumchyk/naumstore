create table if not exists l_user_role
(
    id            bigserial primary key,
    user_id       bigint not null
        constraint l_user_role_users_id_fk
            references users,
    role_id       int    not null
        constraint l_user_role_roles_id_fk
            references roles,
    creation_date timestamp(6) default current_timestamp(6)
);

create unique index if not exists l_user_role_user_id_role_id_uindex
    on l_user_role (user_id, role_id);