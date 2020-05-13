package graduation.project.hospitalbedsmanage.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 病历实体类
 */
public class CaseHistory {
    private int id;//病历编号
    private int patientID;//患者编号
    private int deptNo;//部门编号
    private int doctorID;//医生编号
    private int status;//病历状态
    private String description;//历史诊断记录
    private String treatmentPlan;//治疗方案
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date submitTime;//更新日期

    public CaseHistory() {
    }

    public CaseHistory(int patientID, int deptNo, int doctorID, int status, String description, String treatmentPlan, Date submitTime) {
        this.patientID = patientID;
        this.deptNo = deptNo;
        this.doctorID = doctorID;
        this.status = status;
        this.description = description;
        this.treatmentPlan = treatmentPlan;
        this.submitTime = submitTime;
    }

    public CaseHistory(int id, int patientID, int deptNo, int doctorID, int status, String description, String treatmentPlan, Date submitTime) {
        this.id = id;
        this.patientID = patientID;
        this.deptNo = deptNo;
        this.doctorID = doctorID;
        this.status = status;
        this.description = description;
        this.treatmentPlan = treatmentPlan;
        this.submitTime = submitTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", patientID=" + patientID +
                ", deptNo=" + deptNo +
                ", doctorID=" + doctorID +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", treatmentPlan='" + treatmentPlan + '\'' +
                ", submitTime=" + submitTime +
                '}';
    }
}
