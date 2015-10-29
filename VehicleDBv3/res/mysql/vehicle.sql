use jonv;
drop table if exists vehicle;
create table vehicle (
  vID int unsigned not null auto_increment,
  maker varchar(30) not null,
  model varchar(30) not null,
  mpg varchar(3) not null,
  hp varchar(6) not null,
  yearProd varchar (4) not null,
  primary key(vID)
);
