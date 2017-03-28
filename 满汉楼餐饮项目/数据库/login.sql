create table login(
empid nvarchar(30) foreign key references rszl(empid),
passwd nvarchar(30)
)

insert into login values('laixiaofeng','laixiaofeng')
select * from login where userId='laixiaofeng' and passwd='laixiaofeng' or 1='1'

create table rszl(
empid nvarchar(30) primary key,
empname nvarchar(30) not null,
empimage image,
sex nchar(1) check(sex in('男','女')) default '男' not null,
address nvarchar(50),
birthday datetime,
sfzid nchar(18) not null,
xl nchar(4),
zhiwei nchar(8) not null,
hf nchar(2) check(hf in('未婚','已婚')),
tel1 varchar(20),
tel2 varchar(20),
mail varchar(50),
zctime datetime,
bz ntext
)


insert into rslz(empid,empname,sex,address,birthday,sfzid,xl,zhiwei,hf,tel1,tel2,mail,zctime,bz) values('2227612','谢彦','男','北京朝阳','1980-12-01','110108198002276111','大专','经理','未婚','62561565','13013214561','xxxx@qq.com','2005-12-04','经理')