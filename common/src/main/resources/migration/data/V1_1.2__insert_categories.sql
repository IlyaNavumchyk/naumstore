insert into categories (category_name)
values ('SMARTPHONES_AND_GADGETS'),
       ('TVS_AND_VIDEOS'),
       ('LAPTOPS_AND_COMPUTERS'),
       ('KITCHEN_APPLIANCES'),
       ('HOME_APPLIANCES');

update categories
set creation_date = current_timestamp(6);