create table users
(
    id serial,
    username varchar(20) not null unique,
    password varchar(255) not null,
    firstname varchar(25) not null,
    lastname varchar(25) not null,
    email varchar(255) not null unique,
    dob varchar(10) not null,
    phone numeric(255),
    constraint pk_user primary key (id)
);

create table accounts
(
	id serial,
	userid int not null,
	balance numeric not null,
	accounttype varchar(255) not null,
	name varchar(255) not null,
	constraint pk_account primary key (id),
	constraint fk_user_account foreign key (userid) references users on delete cascade
);

create table transactions
(
	id serial,
	accountid int not null,
	amount numeric not null,
	transactiontype varchar(255) not null,
	datetime timestamp not null,
	constraint pk_transaction primary key (id),
	constraint fk_transaction foreign key (accountid) references accounts on delete cascade
);
