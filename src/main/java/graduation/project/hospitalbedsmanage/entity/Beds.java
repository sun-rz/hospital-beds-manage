package graduation.project.hospitalbedsmanage.entity;

import java.util.Date;

/**
 * 床位实体类
 */
public class Beds {
    private int id;//自增id
    private String bedNo;//床位编号=部门编号+病房号
    private int deptNo;//所属科室编号
    private int roomNo;//病房编号
    private int patientID;//患者ID
    private int status;//使用状态
    private String address;//地点
    private Date useDate;//申请使用日期
    private Date freeDate;//归还日期

    public Beds() {
    }

    public Beds(String bedNo, int deptNo,int roomNo,String address) {
        this.bedNo = bedNo;
        this.deptNo = deptNo;
        this.roomNo = roomNo;
        this.address = address;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
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
                ", bedNo=" + bedNo +
                ", deptNo=" + deptNo +
                ", roomNo=" + roomNo +
                ", patientID=" + patientID +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", useDate=" + useDate +
                ", freeDate=" + freeDate +
                '}';
    }
}
