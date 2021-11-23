package ua.univer.task6;

public class Company {
    private Employee [] employees;

    public Company(Employee [] employees) {
        for (int i = 0; i < employees.length; ++i) {
            this.employees = employees;
        }
    }

    public void giveEverybodyBonus(double companyBonus) {
        for (Employee employee: employees) {
            employee.setBonus(companyBonus);
        }
    }
    public double totalToPay() {
        double total = 0;
        for (Employee employee: employees) {
            total += employee.toPay();
        }
        return total;
    }
    public String nameMaxSalary() {
        Employee maxEmployee = this.employees[0];
        for (int i = 1; i < employees.length; i++) {
            if (employees[i].toPay() > maxEmployee.toPay()) {
                maxEmployee = employees[i];
            }
        }
        return maxEmployee.getName();
    }

}
