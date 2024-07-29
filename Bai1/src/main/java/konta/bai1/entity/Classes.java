package konta.bai1.entity;

public class Classes {
    private int classId;
    private String className;
    private Boolean status;

    public Classes() {
    }

    public Classes(int classId, String className, Boolean status) {
        this.classId = classId;
        this.className = className;
        this.status = status;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
