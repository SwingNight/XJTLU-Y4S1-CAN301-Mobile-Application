package cn.edu.xjtlu.testapp1210;

public class User {
    private String name;
    private String password;
    private String category;
    private String email;
    private String office;
    private String course;

    public User(String name, String password, String category, String email, String office, String course) {
        this.name = name;
        this.password = password;
        this.category = category;
        this.email = email;
        this.office = office;
        this.course = course;
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getOffice() {
        return office;
    }
    public void setOffice(String office) {
        this.office = office;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", category='" + category + '\'' +
                ", email='" + email + '\'' +
                ", office='" + office + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}