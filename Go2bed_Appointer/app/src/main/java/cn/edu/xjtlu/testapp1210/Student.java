package cn.edu.xjtlu.testapp1210;

public class Student {
    private String student;
    private String location;
    private String workDay;
    private String startTime;
    private String endTime;

    public Student(String student,String location,String workDay,String startTime,String endTime){
        this.student = student;
        this.location = location;
        this.workDay = workDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getStudent() {
        return student;
    }

    public String getLocation() {
        return location;
    }

    public String getWorkDay() {
        return workDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public void setStartDay(String startDay) {
        this.startTime = startTime;
    }

    public void setEndDay(String endDay) {
        this.endTime = endTime;
    }
}
