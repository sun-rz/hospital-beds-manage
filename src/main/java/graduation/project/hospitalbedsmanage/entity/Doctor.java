package graduation.project.hospitalbedsmanage.entity;

/**
 * 医生实体类
 */
public class Doctor {
    private int id;//自增ID
    private int age;//年龄
    private int gender;//性别
    private int job;//职务
    private int status;//状态
    private String name; //姓名
    private String password; //密码
    private String deptNo; //部门
    private String mobile;//手机
    private String email;//邮箱

    public Doctor() {
    }

    public Doctor(String name, String password) {
        this.name = name;
        this.password = password;
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

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", age:" + age +
                ", gender:" + gender +
                ", status:" + status +
                ", job:'" + job + '\'' +
                ", name:'" + name + '\'' +
                ", password:'" + password + '\'' +
                ", deptNo:'" + deptNo + '\'' +
                ", mobile:'" + mobile + '\'' +
                ", email:'" + email + '\'' +
                '}';
    }
}
