insert into users (user_login, user_password, email,
                   user_name, surname, is_deleted, creation_date)
values ('GOOD_JOB', '$2a$06$hj.9K7jcEiNAvYmpTheB5OA.HmBGb9up8lv8fXUtc2./1McsbNNdy',
        'naum.st.123@gmail.com', 'Ilya', 'Navumchyk', false, current_timestamp(6));

insert into l_user_role (user_id, role_id, creation_date)
values (1, 1, current_timestamp(6)),
       (1, 2, current_timestamp(6)),
       (1, 3, current_timestamp(6));