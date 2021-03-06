package graduation.project.hospitalbedsmanage.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 患者实体类
 */
public class Patient {
    private int id; //编号
    private int age;//年龄
    private int gender;//性别
    private int deptNo;//科室
    private int level;//优先级别
    private int patient_status;//出院状态
    private String bedNo;//床位号
    private String name; //姓名
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date in_hospital_date;//入院日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date out_hospital_date;//出院日期

    public Patient() {
    }

    public Patient(int id) {
        this.id = id;
    }

    public int getPatient_status() {
        return patient_status;
    }

    public void setPatient_status(int patient_status) {
        this.patient_status = patient_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getIn_hospital_date() {
        return in_hospital_date;
    }

    public void setIn_hospital_date(Date in_hospital_date) {
        this.in_hospital_date = in_hospital_date;
    }

    public Date getOut_hospital_date() {
        return out_hospital_date;
    }

    public void setOut_hospital_date(Date out_hospital_date) {
        this.out_hospital_date = out_hospital_date;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", age=" + age +
                ", gender=" + gender +
                ", patient_status=" + patient_status +
                ", deptNo=" + deptNo +
                ", level=" + level +
                ", bedNo=" + bedNo +
                ", name='" + name + '\'' +
                ", in_hospital_date=" + in_hospital_date +
                ", out_hospital_date=" + out_hospital_date +
                '}';
    }
}
