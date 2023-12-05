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


INSERT INTO public.users (id,full_name,email,phone_number,created_at,created_by,updated_at,updated_by) VALUES
	 ('1af69247-98a2-4bc5-ae84-f085aa4992d0','Denny Rezky Sinulingga','sinulinggatwo@gmail.com','0280423423423','2023-12-05 14:10:41.838626','System',NULL,NULL),
	 ('f6ff5fe3-4048-467d-bee9-7edf619385ed','Denny','sinulingga@gmail.com','029302232323','2023-12-05 14:10:59.58101','System',NULL,NULL),
	 ('b75aae76-054b-45b1-beeb-a09c62330796','Denny Sinulingga','sinulinggaaaa@gmail.com','029302232323123','2023-12-05 14:11:27.939495','System',NULL,NULL);

INSERT INTO products.categories (id,"name",created_at,created_by,updated_at,updated_by) VALUES
	 ('d61fac5f-0302-4f61-8657-0dd346dd1cdf','Elektronik','2023-12-05 14:12:27.18322','System',NULL,NULL),
	 ('60cbf778-da49-4bf6-b1e2-aa726d7233f9','Kesehatan','2023-12-05 14:12:27.183338','System',NULL,NULL),
	 ('5dadec74-ef20-4358-b459-f0cf4019fb8b','Hiburan','2023-12-05 14:12:27.183359','System',NULL,NULL);


INSERT INTO products.products (id,"name",description,qtty,price,created_at,created_by,updated_at,updated_by) VALUES
	 ('1fbb4f51-1aaf-4eb6-a003-df2fb02a960f','Tiket Wisata Murah Meriah Bali - Bandung 2023','Butuh healing.',8,30000000.00,'2023-12-05 14:14:57.487849','System',NULL,NULL),
	 ('57a43076-cf16-4ebe-860a-fc8d624438bf','Laptop MBP M1 2021 8/256 GB','Laptop yang tangguh disegala kondisi.',8,20000000.00,'2023-12-05 14:13:44.098545','System',NULL,NULL);

INSERT INTO products.products_categories (product_id,category_id) VALUES
	 ('57a43076-cf16-4ebe-860a-fc8d624438bf','d61fac5f-0302-4f61-8657-0dd346dd1cdf'),
	 ('1fbb4f51-1aaf-4eb6-a003-df2fb02a960f','5dadec74-ef20-4358-b459-f0cf4019fb8b'),
	 ('1fbb4f51-1aaf-4eb6-a003-df2fb02a960f','60cbf778-da49-4bf6-b1e2-aa726d7233f9');

INSERT INTO transactions.transactions (id,user_id,status_payment,total_payment,transaction_time,created_at,created_by,updated_at,updated_by) VALUES
	 ('ae15bd4d-b91b-4cfa-8c0e-2276eaa964df','1af69247-98a2-4bc5-ae84-f085aa4992d0','NOT_PAID',80000000.00,'2023-12-05 14:16:35.544693','2023-12-05 14:16:35.545346','System',NULL,NULL),
	 ('2e81ab82-f1db-4c81-9801-cfc36db7c787','1af69247-98a2-4bc5-ae84-f085aa4992d0','PAID',20000000.00,'2023-12-05 14:16:00.169069','2023-12-05 14:16:00.169309','System',NULL,NULL);

INSERT INTO transactions.transactions_detail (id,transaction_id,product_id) VALUES
	 ('ae7cb478-5386-412c-92cf-9635150de1ee','2e81ab82-f1db-4c81-9801-cfc36db7c787','57a43076-cf16-4ebe-860a-fc8d624438bf'),
	 ('34fc53c1-813f-4457-b3ed-ca9b4964dbd2','ae15bd4d-b91b-4cfa-8c0e-2276eaa964df','1fbb4f51-1aaf-4eb6-a003-df2fb02a960f'),
	 ('7fff9c51-00ec-4f03-9e5a-411fdc296231','ae15bd4d-b91b-4cfa-8c0e-2276eaa964df','57a43076-cf16-4ebe-860a-fc8d624438bf');
