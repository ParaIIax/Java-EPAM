package ua.univer.task6;

public class Manager extends Employee {
    private int quantity;

    public Manager(String name, double salary, int clientAmount) {
        super(name, salary);
        this.quantity = clientAmount;
    }

    @Override
    public void setBonus(double bonus) {
        if (quantity > 150)
            super.setBonus(bonus += 1000);
        else if (quantity > 100)
            super.setBonus(bonus += 500);
    }

}
