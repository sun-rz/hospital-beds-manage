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

INSERT INTO doctor(name, password, mobile) VALUE ('系统管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin') ON DUPLICATE KEY UPDATE mobile= 'admin';

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
    `id`            int(11) NOT NULL,
    `patientID`     int(11)       DEFAULT NULL,
    `deptNo`        int(11)       DEFAULT NULL,
    `doctorID`      int(11)       DEFAULT NULL,
    `status`        int(11)       DEFAULT NULL,
    `description`   varchar(2000) DEFAULT NULL,
    `treatmentPlan` varchar(2000) DEFAULT NULL,
    `submitTime`    datetime      DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `department`
(
    `ID`          int(11) NOT NULL,
    `totalBeds`   int(11)      DEFAULT NULL,
    `useBeds`     int(11)      DEFAULT NULL,
    `freeBeds`    int(11)      DEFAULT NULL,
    `borrowBeds`  int(11)      DEFAULT NULL,
    `borrowLevel` int(11)      DEFAULT NULL,
    `deptName`    varchar(255) DEFAULT NULL,
    `deptService` varchar(255) DEFAULT NULL,
    `usage`       float        DEFAULT NULL,
    PRIMARY KEY (`ID`)
);

CREATE TABLE IF NOT EXISTS `patient`
(
    `id`                int(11) NOT NULL,
    `age`               int(11)     DEFAULT NULL,
    `gender`            int(11)     DEFAULT NULL,
    `deptNo`            int(11) NOT NULL,
    `level`             int(11)     DEFAULT NULL,
    `bedNo`             int(11)     DEFAULT NULL,
    `name`              varchar(24) DEFAULT NULL,
    `in_hospital_date`  datetime    DEFAULT NULL,
    `out_hospital_date` datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
);
