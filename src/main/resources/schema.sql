create table IF NOT EXISTS doctor
(
    id       int auto_increment,
    name     nvarchar(20)  null,
    age      int           null,
    gender   int           null,
    deptno   nvarchar(50)  null,
    job      int           null,
    ststus   int           null,
    mobile   nvarchar(20)  NOT NULL unique,
    email    nvarchar(50)  null,
    password nvarchar(100) null,
    constraint doctor_pk
        primary key (id)
);

INSERT INTO doctor(name, password, mobile) VALUE ('系统管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin')
ON DUPLICATE KEY UPDATE mobile= 'admin';

create table IF NOT EXISTS beds
(
    id        int auto_increment PRIMARY KEY,
    deptNo    int,
    patientID int,
    status    int,
    address   varchar(256),
    useDate   DATETIME,
    freeDate  DATETIME
);

CREATE TABLE IF NOT EXISTS `casehistory`
(
    `id`            int auto_increment,
    `patientID`     int           DEFAULT NULL,
    `deptNo`        int           DEFAULT NULL,
    `doctorID`      int           DEFAULT NULL,
    `status`        int           DEFAULT NULL,
    `description`   varchar(2000) DEFAULT NULL,
    `treatmentPlan` varchar(2000) DEFAULT NULL,
    `submitTime`    datetime      DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `department`
(
    `ID`          int auto_increment,
    `totalBeds`   int          DEFAULT NULL,
    `useBeds`     int          DEFAULT NULL,
    `freeBeds`    int          DEFAULT NULL,
    `borrowBeds`  int          DEFAULT NULL,
    `borrowLevel` int          DEFAULT NULL,
    `deptName`    varchar(255) NOT NULL unique,
    `deptService` varchar(255) DEFAULT NULL,
    `usage`       float        DEFAULT NULL,
    PRIMARY KEY (`ID`)
);

insert into department (deptName, deptService)
values ('院长', '行政部门')
ON DUPLICATE KEY UPDATE deptName= '院长';
insert into department (deptName, deptService)
values ('书记办公室', '行政部门')
ON DUPLICATE KEY UPDATE deptName= '书记办公室';
insert into department (deptName, deptService)
values ('行政办公室', '行政部门')
ON DUPLICATE KEY UPDATE deptName= '行政办公室';
insert into department (deptName, deptService)
values ('纪委办公室', '行政部门')
ON DUPLICATE KEY UPDATE deptName= '纪委办公室';
insert into department (deptName, deptService)
values ('医务科', '行政部门')
ON DUPLICATE KEY UPDATE deptName= '医务科';
insert into department (deptName, deptService)
values ('护理部', '行政部门')
ON DUPLICATE KEY UPDATE deptName= '护理部';
insert into department (deptName, deptService)
values ('财务部', '行政部门')
ON DUPLICATE KEY UPDATE deptName= '财务部';
insert into department (deptName, deptService)
values ('工会', '行政部门')
ON DUPLICATE KEY UPDATE deptName= '工会';
insert into department (deptName, deptService)
values ('门诊部', '临床门诊部部门')
ON DUPLICATE KEY UPDATE deptName= '门诊部';
insert into department (deptName, deptService)
values ('挂号收费室', '临床门诊部部门')
ON DUPLICATE KEY UPDATE deptName= '挂号收费室';
insert into department (deptName, deptService)
values ('注射输液室', '临床门诊部部门')
ON DUPLICATE KEY UPDATE deptName= '注射输液室';
insert into department (deptName, deptService)
values ('急诊科', '临床门诊部部门')
ON DUPLICATE KEY UPDATE deptName= '急诊科';
insert into department (deptName, deptService)
values ('供应室', '临床门诊部部门')
ON DUPLICATE KEY UPDATE deptName= '供应室';
insert into department (deptName, deptService)
values ('内科', '住院部部门')
ON DUPLICATE KEY UPDATE deptName= '内科';
insert into department (deptName, deptService)
values ('外科', '住院部部门')
ON DUPLICATE KEY UPDATE deptName= '外科';
insert into department (deptName, deptService)
values ('儿科', '住院部部门')
ON DUPLICATE KEY UPDATE deptName= '儿科';
insert into department (deptName, deptService)
values ('妇产科', '住院部部门')
ON DUPLICATE KEY UPDATE deptName= '妇产科';
insert into department (deptName, deptService)
values ('五官科', '住院部部门')
ON DUPLICATE KEY UPDATE deptName= '五官科';
insert into department (deptName, deptService)
values ('中医科', '住院部部门')
ON DUPLICATE KEY UPDATE deptName= '中医科';
insert into department (deptName, deptService)
values ('传染科', '住院部部门')
ON DUPLICATE KEY UPDATE deptName= '传染科';
insert into department (deptName, deptService)
values ('中药房', '药剂科部门')
ON DUPLICATE KEY UPDATE deptName= '中药房';
insert into department (deptName, deptService)
values ('西药房', '药剂科部门')
ON DUPLICATE KEY UPDATE deptName= '西药房';
insert into department (deptName, deptService)
values ('药库', '药剂科部门')
ON DUPLICATE KEY UPDATE deptName= '药库';
insert into department (deptName, deptService)
values ('检验科', '医技科部门')
ON DUPLICATE KEY UPDATE deptName= '放射科';
insert into department (deptName, deptService)
values ('放射科', '医技科部门')
ON DUPLICATE KEY UPDATE deptName= '放射科';
insert into department (deptName, deptService)
values ('心电图', '医技科部门')
ON DUPLICATE KEY UPDATE deptName= '心电图';
insert into department (deptName, deptService)
values ('B超室', '医技科部门')
ON DUPLICATE KEY UPDATE deptName= 'B超室';
insert into department (deptName, deptService)
values ('医疗器械维修部', '后勤部门')
ON DUPLICATE KEY UPDATE deptName= '医疗器械维修部';
insert into department (deptName, deptService)
values ('救护车组', '后勤部门')
ON DUPLICATE KEY UPDATE deptName= '救护车组';
insert into department (deptName, deptService)
values ('洗衣房', '后勤部门')
ON DUPLICATE KEY UPDATE deptName= '洗衣房';
insert into department (deptName, deptService)
values ('保卫科', '后勤部门')
ON DUPLICATE KEY UPDATE deptName= '保卫科';
insert into department (deptName, deptService)
values ('门卫', '后勤部门')
ON DUPLICATE KEY UPDATE deptName= '门卫';
insert into department (deptName, deptService)
values ('营养食堂', '后勤部门')
ON DUPLICATE KEY UPDATE deptName= '营养食堂';
insert into department (deptName, deptService)
values ('园艺环卫组', '后勤部门')
ON DUPLICATE KEY UPDATE deptName= '园艺环卫组';

CREATE TABLE IF NOT EXISTS `patient`
(
    `id`                int auto_increment,
    `age`               int         DEFAULT NULL,
    `gender`            int         DEFAULT NULL,
    `deptNo`            int         NOT NULL,
    `level`             int         DEFAULT NULL,
    `bedNo`             int         DEFAULT NULL,
    `name`              varchar(24) DEFAULT NULL,
    `in_hospital_date`  datetime    DEFAULT NULL,
    `out_hospital_date` datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
);
