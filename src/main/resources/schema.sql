create table IF NOT EXISTS doctor
(
	id int auto_increment,
	name nvarchar(20) NOT NULL unique,
	age int null,
	gender int null,
	deptno nvarchar(50) null,
	job int null,
	ststus int null,
	mobile nvarchar(20) null,
	email nvarchar(50) null,
	password nvarchar(100) null,
	constraint doctor_pk
		primary key (id)
);

INSERT INTO doctor(name,password) VALUE('admin','E10ADC3949BA59ABBE56E057F20F883E') ON DUPLICATE KEY UPDATE name= 'admin';
