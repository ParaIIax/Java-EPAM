package ua.univer.task6;

public class SalesPerson extends Employee {
    private int percent;

    public SalesPerson(String name, double salary, int percent) {
        super(name, salary);
        this.percent = percent;
    }

    @Override
    public void setBonus(double bonus) {
        if (percent > 200)
        super.setBonus(bonus *= 3);
        else if (percent > 100)
        super.setBonus(bonus *= 2);
    }

}
