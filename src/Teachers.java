public class Teachers extends Subjects{

    private String name, surname;
    private double salary;

    public Teachers(String name, String surname, double salary, double sname) {
        super(sname);
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher name:'" + name + '\'' +
                ", surname:'" + surname + '\'' +
                ", salary:" + salary + "subject: " + getSName() +
                '}';
    }
}