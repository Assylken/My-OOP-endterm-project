public class Subjects {

    private String sname;
    private double gpa;


    public Subjects(double gpa) { this.gpa = gpa; }

    public String getSName() {
        return this.sname;
    }

    public void setSName(String name) {
        this.sname = name;
    }

    public double getGpa() {
        return this.gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}