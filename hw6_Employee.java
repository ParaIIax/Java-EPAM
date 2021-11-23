package ua.univer.task6;

public class Employee {
    private String name;
    private double salary;
    private double bonus;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void setBonus(double bonus) { this.bonus = bonus; }
    public double toPay() {
        return salary + bonus;
    }

}
