create table test2(
	Time TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
	title varchar(255) not null,
	editor varchar(255) not null,
	detail varchar(255) not null,
	id int not null,
	foreign key (id) references test3(id)
);
create table test3(
	id int not null AUTO_INCREMENT,
	primary key (id)
);