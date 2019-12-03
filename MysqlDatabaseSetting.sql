create table Questions(
	Time TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
	title varchar(255) not null,
	editor varchar(255) not null,
	detail varchar(255) not null,
	id int not null,
	foreign key (id) references IDsTable(id)
);
create table IDsTable(
	id int not null AUTO_INCREMENT,
	primary key (id)
);

# 测试数据
INSERT INTO IDsTable(id) VALUES (12);
INSERT INTO IDsTable(id) VALUES (13);

INSERT INTO Questions(title,editor,detail,id)VALUES("标题1","作者1","xxxx巴拉巴巴拉巴拉巴拉巴",12);
INSERT INTO Questions(title,editor,detail,id)VALUES("标题2","作者2","xxxx巴拉巴巴拉巴拉巴拉巴",12);

INSERT INTO Questions(title,editor,detail,id)VALUES("标题3","作者3","xxxx巴拉巴巴拉巴拉巴拉巴",13);
INSERT INTO Questions(title,editor,detail,id)VALUES("标题4","作者4","xxxx巴拉巴巴拉巴拉巴拉巴",13);