create table if not exists public.users (
	id uuid primary key,
	full_name varchar(150) not null,
	email varchar(150) not null unique,
	phone_number varchar(50) not null unique,
	created_at timestamp not null,
	created_by varchar(150) not null,
	updated_at timestamp null,
	updated_by varchar(150) null
);