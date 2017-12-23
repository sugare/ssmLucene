# 标题、作者、短文、时间、
CREATE TABLE blog (
    id int(10) auto_increment primary key,
    title varchar(20) default null,
    content text(100) default null
)DEFAULT CHARSET=utf8;