package hospital;

public class Doctor extends Person {

    private String specialization;
    private double salary;

    public Doctor(String id, String name, int age, String gender, String specialization, double salary) {
        super(id, name, age, gender);
        this.specialization = specialization;
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
        System.out.println("Salary: " + salary);
    }
}
