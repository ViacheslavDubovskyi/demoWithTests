create table public.salary (
                               id serial primary key,
                               commission_rate double precision,
                               hourly_rate integer
);

create table public.users (
                              id serial primary key,
                              name character varying(255),
                              country character varying(255),
                              email character varying(255),
                              phone_number bigint,
                              is_updated boolean default false,
                              is_deleted boolean default false,
                              employee_id integer,
                              foreign key (employee_id) references public.salary (id)
                                  match simple on update no action on delete no action
);

create table public.addresses (
                                  id bigserial primary key,
                                  address_has_active boolean,
                                  city character varying(255),
                                  country character varying(255),
                                  house_number character varying(255),
                                  street character varying(255),
                                  employee_id integer,
                                  foreign key (employee_id) references public.users (id)
                                      match simple on update no action on delete no action
);


