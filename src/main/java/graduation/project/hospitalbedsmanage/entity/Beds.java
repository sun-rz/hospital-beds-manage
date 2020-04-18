package graduation.project.hospitalbedsmanage.entity;

import java.util.Date;

/**
 * 床位实体类
 */
public class Beds {
    private int id;//床位编号
    private int deptNo;//所属科室编号
    private int patientID;//患者ID
    private int status;//使用状态
    private String address;//地点
    private Date useDate;//申请使用日期
    private Date freeDate;//归还日期

    public Beds() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public Date getFreeDate() {
        return freeDate;
    }

    public void setFreeDate(Date freeDate) {
        this.freeDate = freeDate;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", deptNo=" + deptNo +
                ", patientID=" + patientID +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", useDate=" + useDate +
                ", freeDate=" + freeDate +
                '}';
    }
}
