package graduation.project.hospitalbedsmanage.entity;

/**
 * 部门实体类
 */
public class Department {
    private int id;//部门编号
    private int totalBeds;//总床位
    private int rooms;//病房个数
    private int useBeds;//使用床位
    private int freeBeds;//剩余床位
    private int borrowBeds;//外借床位
    private int borrowLevel;//外借优先级
    private String deptName;//科室名
    private String deptService;//科室功能
    private float usage;//使用率

    public Department() {
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public int getUseBeds() {
        return useBeds;
    }

    public void setUseBeds(int useBeds) {
        this.useBeds = useBeds;
    }

    public int getFreeBeds() {
        return freeBeds;
    }

    public void setFreeBeds(int freeBeds) {
        this.freeBeds = freeBeds;
    }

    public int getBorrowBeds() {
        return borrowBeds;
    }

    public void setBorrowBeds(int borrowBeds) {
        this.borrowBeds = borrowBeds;
    }

    public int getBorrowLevel() {
        return borrowLevel;
    }

    public void setBorrowLevel(int borrowLevel) {
        this.borrowLevel = borrowLevel;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptService() {
        return deptService;
    }

    public void setDeptService(String deptService) {
        this.deptService = deptService;
    }

    public float getUsage() {
        return usage;
    }

    public void setUsage(float usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", totalBeds=" + totalBeds +
                ", rooms=" + rooms +
                ", useBeds=" + useBeds +
                ", freeBeds=" + freeBeds +
                ", borrowBeds=" + borrowBeds +
                ", borrowLevel=" + borrowLevel +
                ", deptName='" + deptName + '\'' +
                ", deptService='" + deptService + '\'' +
                ", usage=" + usage +
                '}';
    }
}
