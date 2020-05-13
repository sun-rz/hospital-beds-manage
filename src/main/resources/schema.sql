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

INSERT INTO doctor(name, password, mobile,deptno,email,job) VALUE ('系统管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin',1,'admin@qq.com',1) ON DUPLICATE KEY UPDATE mobile= 'admin';
INSERT INTO doctor(name, password, mobile,deptno,email,job) VALUE ('测试账号', 'E10ADC3949BA59ABBE56E057F20F883E', 'test',1,'test@qq.com',1) ON DUPLICATE KEY UPDATE mobile= 'test';

create table IF NOT EXISTS beds
(
    id        int auto_increment PRIMARY KEY,
    deptNo    int,
    patientID int,
    roomNo    int,
    status    int default 0 null,
    bedNo     varchar(32) NOT NULL unique,
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
    `rooms`       int          DEFAULT NULL,
    `freeBeds`    int          DEFAULT NULL,
    `borrowBeds`  int          DEFAULT NULL,
    `borrowLevel` int          DEFAULT NULL,
    `deptName`    varchar(255) NOT NULL unique,
    `deptService` varchar(255) DEFAULT NULL,
    `usage`       float        DEFAULT NULL,
    PRIMARY KEY (`ID`)
);

insert into department (deptName, deptService, rooms) values ('神经内科', '主要以神经系统为主，具体疾病诸如脑梗死脑出血偏头痛等',1) ON DUPLICATE KEY UPDATE deptName= '神经内科';
insert into department (deptName, deptService, rooms) values ('呼吸内科', '呼吸系统为主，具体疾病诸如急性上呼吸道感人肺炎、COPD、支气管炎、呼吸衰竭、 肺结核等',1) ON DUPLICATE KEY UPDATE deptName= '呼吸内科';
insert into department (deptName, deptService, rooms) values ('心血管科', '心脏大血管为主，具体疾病如高血压、肺心病、心肌梗死、冠心病、心肌病、感染性心 内膜炎等',1) ON DUPLICATE KEY UPDATE deptName= '心血管科';
insert into department (deptName, deptService, rooms) values ('消化内科', '主要是消化系统，具体疾病如胃炎、胃溃疡、肝硬化、消化道癌症等',1) ON DUPLICATE KEY UPDATE deptName= '消化内科';
insert into department (deptName, deptService, rooms) values ('肾内科', '主要是泌尿系统。具体疾病如肾小球肾炎、肾病综合征、慢性肾病、尿路感染等',1) ON DUPLICATE KEY UPDATE deptName= '肾内科';
insert into department (deptName, deptService, rooms) values ('血液科', '主要是血液系统和淋巴系统。具体疾病如贫血、白血病、紫癜、淋巴瘤等',1) ON DUPLICATE KEY UPDATE deptName= '血液科';
insert into department (deptName, deptService, rooms) values ('内分泌科', '主要是内分泌系统。具体疾病如糖尿病、高钾血症、甲亢等',1) ON DUPLICATE KEY UPDATE deptName= '内分泌科';
insert into department (deptName, deptService, rooms) values ('风湿代谢科', '主要是免疫系统和代谢性疾病。具体疾病如类风湿性关节炎、系统性红斑狼疮、高尿 酸血症、痛风、雷诺病等等',1) ON DUPLICATE KEY UPDATE deptName= '风湿代谢科';
insert into department (deptName, deptService, rooms) values ('老干科', '这个科室以老年病为主，其实就是呼吸科心血管科等科室的综合体',1) ON DUPLICATE KEY UPDATE deptName= '老干科';
insert into department (deptName, deptService, rooms) values ('传染科', '主要是以传染性疾病为主。具体疾病如乙肝大小三阳、肺结核、艾滋病等有传染性的疾病',1) ON DUPLICATE KEY UPDATE deptName= '传染科';
insert into department (deptName, deptService, rooms) values ('肿瘤内科', '肿瘤的保守治疗，诸如放疗、化疗等',1) ON DUPLICATE KEY UPDATE deptName= '肿瘤内科';
insert into department (deptName, deptService, rooms) values ('神经外科（脑外科）', '主要以脑部手术为主。具体疾病如脑疝、蛛网膜下出血、脑部肿瘤等',1) ON DUPLICATE KEY UPDATE deptName= '神经外科（脑外科）';
insert into department (deptName, deptService, rooms) values ('眼科', '现在有眼视光学，眼科和视光学。具体疾病如：角膜病、白内障、青光眼、近视远视、视网 膜脱离、泪道疾病、眼部外伤等',1) ON DUPLICATE KEY UPDATE deptName= '眼科';
insert into department (deptName, deptService, rooms) values ('耳鼻喉科', '主要包括耳、鼻、喉部的手术。具体疾病如扁桃体肿大、鼻中隔扭曲、喉部异物、鼻咽 癌等',1) ON DUPLICATE KEY UPDATE deptName= '耳鼻喉科';
insert into department (deptName, deptService, rooms) values ('口腔科', '主要包括口腔颌面部。具体疾病如牙周炎、龋齿、口腔部肿瘤及外伤等',1) ON DUPLICATE KEY UPDATE deptName= '口腔科';
insert into department (deptName, deptService, rooms) values ('心胸外科', '主要包括肺。心脏等具体疾病如肋骨骨折、气胸、先天性心脏病、肺癌、心脏瓣膜病等',1) ON DUPLICATE KEY UPDATE deptName= '心胸外科';
insert into department (deptName, deptService, rooms) values ('普通外科', '相互之间没这么具体，都可以收',1) ON DUPLICATE KEY UPDATE deptName= '普通外科';
insert into department (deptName, deptService, rooms) values ('甲乳科', '主要包括乳腺和甲状腺这两个部位。具体疾病如甲状腺肿瘤、乳腺癌等',1) ON DUPLICATE KEY UPDATE deptName= '甲乳科';
insert into department (deptName, deptService, rooms) values ('胃肠外科', '主要包括胃、十二指肠、盲肠、结肠等。具体疾病如胃穿孔、胃癌、肠梗阻、阑尾炎等',1) ON DUPLICATE KEY UPDATE deptName= '胃肠外科';
insert into department (deptName, deptService, rooms) values ('肝胆胰脾外科', '主要是肝胆胰脾。具体疾病如肝癌、肝囊肿、胆囊炎、胆囊结石、胰腺炎、胰腺癌脾出血等',1) ON DUPLICATE KEY UPDATE deptName= '肝胆胰脾外科';
insert into department (deptName, deptService, rooms) values ('肛肠科', '主要是下段消化道。具体疾病有直肠息肉、肛周脓肿、痔疮等',1) ON DUPLICATE KEY UPDATE deptName= '肛肠科';
insert into department (deptName, deptService, rooms) values ('泌尿外科', '主要是泌尿系统。具体疾病如肾结石、尿道结石、肾癌、膀胱癌、尿道损伤、阴茎癌等',1) ON DUPLICATE KEY UPDATE deptName= '泌尿外科';
insert into department (deptName, deptService, rooms) values ('骨科', '主要是全身骨骼。具体疾病如各处骨折、关节损伤、脱臼、骨癌、断指等',1) ON DUPLICATE KEY UPDATE deptName= '骨科';
insert into department (deptName, deptService, rooms) values ('整形科', '主要是各个部位的修复整形。具体手术有激光祛斑、重睑手术、隆胸拉皮等',1) ON DUPLICATE KEY UPDATE deptName= '整形科';
insert into department (deptName, deptService, rooms) values ('肿瘤外科', '主要是各个器官的肿瘤',1) ON DUPLICATE KEY UPDATE deptName= '肿瘤外科';
insert into department (deptName, deptService, rooms) values ('烧伤科', '主要是皮肤烧伤，像俞灏明和selina那种就需要入住此科',1) ON DUPLICATE KEY UPDATE deptName= '烧伤科';
insert into department (deptName, deptService, rooms) values ('血管外科', '新兴科室，具体疾病如下肢静脉曲张等',1) ON DUPLICATE KEY UPDATE deptName= '血管外科';
insert into department (deptName, deptService, rooms) values ('皮肤科', '包括皮肤病和性病相关',1) ON DUPLICATE KEY UPDATE deptName= '皮肤科';
insert into department (deptName, deptService, rooms) values ('妇科', '主要以女性疾病为主，具体如阴道炎、子宫内膜炎、宫颈癌、卵巢癌等等',1) ON DUPLICATE KEY UPDATE deptName= '妇科';
insert into department (deptName, deptService, rooms) values ('产科', '简而言之，生孩子的，大家都懂',1) ON DUPLICATE KEY UPDATE deptName= '产科';
insert into department (deptName, deptService, rooms) values ('儿科', '主要针对胎儿到青春期的小朋友各种疾病。有儿童内科和外科，具体分类和成人差不多',1) ON DUPLICATE KEY UPDATE deptName= '儿科';
insert into department (deptName, deptService, rooms) values ('中医科', '祖国医学为主。大家都懂，主要治疗以中药、针灸，中医院分的细还有中医骨科、推拿科针灸科等',1) ON DUPLICATE KEY UPDATE deptName= '中医科';
insert into department (deptName, deptService, rooms) values ('移植专科', '主要进行器官移植',1) ON DUPLICATE KEY UPDATE deptName= '移植专科';
insert into department (deptName, deptService, rooms) values ('生殖科', '主要治疗不孕不育和一些生殖咨询',1) ON DUPLICATE KEY UPDATE deptName= '生殖科';
insert into department (deptName, deptService, rooms) values ('营养科', '主要是调理搭配营养，健康饮食为主',1) ON DUPLICATE KEY UPDATE deptName= '营养科';
insert into department (deptName, deptService, rooms) values ('康复科', '主要是病人复健为主，中风后调理等',1) ON DUPLICATE KEY UPDATE deptName= '康复科';
insert into department (deptName, deptService, rooms) values ('精神科', '主要收治各种心理疾病患者，如抑郁症、躁狂症、精神分裂症等',1) ON DUPLICATE KEY UPDATE deptName= '精神科';
insert into department (deptName, deptService, rooms) values ('麻醉科', '主要是病人手术麻醉',1) ON DUPLICATE KEY UPDATE deptName= '麻醉科';
insert into department (deptName, deptService, rooms) values ('心电图室', '心电图室',1) ON DUPLICATE KEY UPDATE deptName= '心电图室';
insert into department (deptName, deptService, rooms) values ('CT室', 'CT室',1) ON DUPLICATE KEY UPDATE deptName= 'CT室';
insert into department (deptName, deptService, rooms) values ('B超', 'B超',1) ON DUPLICATE KEY UPDATE deptName= 'B超';
insert into department (deptName, deptService, rooms) values ('磁共振室', '磁共振室',1) ON DUPLICATE KEY UPDATE deptName= '磁共振室';
insert into department (deptName, deptService, rooms) values ('脑电图室', '脑电图室',1) ON DUPLICATE KEY UPDATE deptName= '脑电图室';
insert into department (deptName, deptService, rooms) values ('PET-CT', 'PET-CT',1) ON DUPLICATE KEY UPDATE deptName= 'PET-CT';
insert into department (deptName, deptService, rooms) values ('检验科', '抽血化验、尿、粪的检验等',1) ON DUPLICATE KEY UPDATE deptName= '检验科';
insert into department (deptName, deptService, rooms) values ('病理科', '各种组织切片的病理学检查、细胞学检查等',1) ON DUPLICATE KEY UPDATE deptName= '病理科';
insert into department (deptName, deptService, rooms) values ('体检科', '各类体检',1) ON DUPLICATE KEY UPDATE deptName= '体检科';
insert into department (deptName, deptService, rooms) values ('疼痛科', '新兴科室',1) ON DUPLICATE KEY UPDATE deptName= '疼痛科';
insert into department (deptName, deptService, rooms) values ('预防保健科', '预防疾病为主，宣传工作比较多',1) ON DUPLICATE KEY UPDATE deptName= '预防保健科';
insert into department (deptName, deptService, rooms) values ('急诊科', '主要收治各种危急发病的患者',1) ON DUPLICATE KEY UPDATE deptName= '急诊科';

/*insert into department (deptName, deptService, rooms) values ('院长', '行政部门') ON DUPLICATE KEY UPDATE deptName= '院长';
insert into department (deptName, deptService, rooms) values ('书记办公室', '行政部门') ON DUPLICATE KEY UPDATE deptName= '书记办公室';
insert into department (deptName, deptService, rooms) values ('行政办公室', '行政部门') ON DUPLICATE KEY UPDATE deptName= '行政办公室';
insert into department (deptName, deptService, rooms) values ('纪委办公室', '行政部门') ON DUPLICATE KEY UPDATE deptName= '纪委办公室';
insert into department (deptName, deptService, rooms) values ('医务科', '行政部门') ON DUPLICATE KEY UPDATE deptName= '医务科';
insert into department (deptName, deptService, rooms) values ('护理部', '行政部门') ON DUPLICATE KEY UPDATE deptName= '护理部';
insert into department (deptName, deptService, rooms) values ('财务部', '行政部门') ON DUPLICATE KEY UPDATE deptName= '财务部';
insert into department (deptName, deptService, rooms) values ('工会', '行政部门') ON DUPLICATE KEY UPDATE deptName= '工会';
insert into department (deptName, deptService, rooms) values ('门诊部', '临床门诊部部门') ON DUPLICATE KEY UPDATE deptName= '门诊部';
insert into department (deptName, deptService, rooms) values ('挂号收费室', '临床门诊部部门') ON DUPLICATE KEY UPDATE deptName= '挂号收费室';
insert into department (deptName, deptService, rooms) values ('注射输液室', '临床门诊部部门') ON DUPLICATE KEY UPDATE deptName= '注射输液室';
insert into department (deptName, deptService, rooms) values ('急诊科', '临床门诊部部门') ON DUPLICATE KEY UPDATE deptName= '急诊科';
insert into department (deptName, deptService, rooms) values ('供应室', '临床门诊部部门') ON DUPLICATE KEY UPDATE deptName= '供应室';
insert into department (deptName, deptService, rooms) values ('内科', '住院部部门') ON DUPLICATE KEY UPDATE deptName= '内科';
insert into department (deptName, deptService, rooms) values ('外科', '住院部部门') ON DUPLICATE KEY UPDATE deptName= '外科';
insert into department (deptName, deptService, rooms) values ('儿科', '住院部部门') ON DUPLICATE KEY UPDATE deptName= '儿科';
insert into department (deptName, deptService, rooms) values ('妇产科', '住院部部门') ON DUPLICATE KEY UPDATE deptName= '妇产科';
insert into department (deptName, deptService, rooms) values ('五官科', '住院部部门') ON DUPLICATE KEY UPDATE deptName= '五官科';
insert into department (deptName, deptService, rooms) values ('中医科', '住院部部门') ON DUPLICATE KEY UPDATE deptName= '中医科';
insert into department (deptName, deptService, rooms) values ('传染科', '住院部部门') ON DUPLICATE KEY UPDATE deptName= '传染科';
insert into department (deptName, deptService, rooms) values ('中药房', '药剂科部门') ON DUPLICATE KEY UPDATE deptName= '中药房';
insert into department (deptName, deptService, rooms) values ('西药房', '药剂科部门') ON DUPLICATE KEY UPDATE deptName= '西药房';
insert into department (deptName, deptService, rooms) values ('药库', '药剂科部门') ON DUPLICATE KEY UPDATE deptName= '药库';
insert into department (deptName, deptService, rooms) values ('检验科', '医技科部门') ON DUPLICATE KEY UPDATE deptName= '检验科';
insert into department (deptName, deptService, rooms) values ('放射科', '医技科部门') ON DUPLICATE KEY UPDATE deptName= '放射科';
insert into department (deptName, deptService, rooms) values ('心电图', '医技科部门') ON DUPLICATE KEY UPDATE deptName= '心电图';
insert into department (deptName, deptService, rooms) values ('B超室', '医技科部门') ON DUPLICATE KEY UPDATE deptName= 'B超室';
insert into department (deptName, deptService, rooms) values ('医疗器械维修部', '后勤部门') ON DUPLICATE KEY UPDATE deptName= '医疗器械维修部';
insert into department (deptName, deptService, rooms) values ('救护车组', '后勤部门') ON DUPLICATE KEY UPDATE deptName= '救护车组';
insert into department (deptName, deptService, rooms) values ('洗衣房', '后勤部门') ON DUPLICATE KEY UPDATE deptName= '洗衣房';
insert into department (deptName, deptService, rooms) values ('保卫科', '后勤部门') ON DUPLICATE KEY UPDATE deptName= '保卫科';
insert into department (deptName, deptService, rooms) values ('门卫', '后勤部门') ON DUPLICATE KEY UPDATE deptName= '门卫';
insert into department (deptName, deptService, rooms) values ('营养食堂', '后勤部门') ON DUPLICATE KEY UPDATE deptName= '营养食堂';
insert into department (deptName, deptService, rooms) values ('园艺环卫组', '后勤部门') ON DUPLICATE KEY UPDATE deptName= '园艺环卫组';
*/
CREATE TABLE IF NOT EXISTS `patient`
(
    `id`                int auto_increment,
    `age`               int         DEFAULT NULL,
    `gender`            int         DEFAULT NULL,
    `deptNo`            int         NOT NULL,
    `level`             int         DEFAULT NULL,
    `bedNo`             varchar(32)         DEFAULT NULL,
    `name`              varchar(24) DEFAULT NULL,
    `in_hospital_date`  datetime    DEFAULT NULL,
    `out_hospital_date` datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
);

create table IF NOT EXISTS job
(
    id int auto_increment,
    jobname nvarchar(50) NOT NULL unique,
    constraint job_pk
        primary key (id)
);
insert into job (jobname) values ('院长') ON DUPLICATE KEY UPDATE jobname= '院长';
insert into job (jobname) values ('副院长') ON DUPLICATE KEY UPDATE jobname= '副院长';
insert into job (jobname) values ('办公室主任') ON DUPLICATE KEY UPDATE jobname= '办公室主任';
insert into job (jobname) values ('科室主任') ON DUPLICATE KEY UPDATE jobname= '科室主任';
insert into job (jobname) values ('医生') ON DUPLICATE KEY UPDATE jobname= '医生';
insert into job (jobname) values ('主治医生') ON DUPLICATE KEY UPDATE jobname= '主治医生';
insert into job (jobname) values ('化验员') ON DUPLICATE KEY UPDATE jobname= '化验员';
insert into job (jobname) values ('治疗人员') ON DUPLICATE KEY UPDATE jobname= '治疗人员';
insert into job (jobname) values ('护士长') ON DUPLICATE KEY UPDATE jobname= '护士长';
insert into job (jobname) values ('护士') ON DUPLICATE KEY UPDATE jobname= '护士';
insert into job (jobname) values ('药剂师') ON DUPLICATE KEY UPDATE jobname= '药剂师';
insert into job (jobname) values ('后勤') ON DUPLICATE KEY UPDATE jobname= '后勤';
insert into job (jobname) values ('保安') ON DUPLICATE KEY UPDATE jobname= '保安';

