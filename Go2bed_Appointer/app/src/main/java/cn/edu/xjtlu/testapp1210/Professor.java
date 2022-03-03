package cn.edu.xjtlu.testapp1210;

public class Professor {
    private String teacher;
    private String location;
    private String workDay;
    private String StartTime;
    private String endTime;

    public Professor(String teacher, String location,String workDay,String startTime,String endTime){
        this.teacher = teacher;
        this.location = location;
        this.workDay = workDay;
        this.StartTime = startTime;
        this.endTime = endTime;
    }

    public Professor(String location,String workDay,String startTime,String endTime){
        this.teacher = LoginActivity.account;
        this.location = location;
        this.workDay = workDay;
        this.StartTime = startTime;
        this.endTime = endTime;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getLocation() {
        return location;
    }

    public String getWorkDay() {
        return workDay;
    }

    public String getStarTime() {
        return StartTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
