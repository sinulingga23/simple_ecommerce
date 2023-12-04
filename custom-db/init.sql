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


create schema if not exists products;

create table if not exists products.categories (
	id uuid primary key,
	name varchar(150) not null,	
	created_at timestamp not null,
	created_by varchar(150) not null,
	updated_at timestamp null,
	updated_by varchar(150) null
);

create table if not exists products.products (
	id uuid primary key,
	name varchar(150) not null,
	description text not null,
	qtty bigint not null,
	created_at timestamp not null,
	created_by varchar(150) not null,
	updated_at timestamp null,
	updated_by varchar(150) null
);


create table if not exists products.products_categories (
	product_id uuid null,
	category_id uuid null,
	foreign key (product_id) references products.products (id)
	on update cascade on delete cascade,
	foreign key (category_id) references products.categories (id)
	on update cascade on delete cascade
);
