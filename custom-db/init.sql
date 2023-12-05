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
	price numeric(12,2) not null,
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

create schema if not exists transactions;

create table if not exists transactions.transactions (
	id uuid primary key,
	user_id uuid null,
	status_payment varchar(100) null,
	total_payment numeric(12,2) null,
	transaction_time timestamp not null,
	created_at timestamp not null,
	created_by varchar(150) not null,
	updated_at timestamp null,
	updated_by varchar(150) null,
	foreign key (user_id) references public.users (id)
	on update cascade on delete restrict
);

create table if not exists transactions.transactions_detail (
	id uuid primary key,
	transaction_id uuid null,
	product_id uuid null,
	foreign key (transaction_id) references transactions.transactions (id)
	on update cascade on delete cascade,
	foreign key (product_id) references products.products (id)
	on update cascade on delete restrict
);
