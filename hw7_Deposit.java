package ua.univer.task7;

public abstract class Deposit {
    private double depositAmount;
    private int depositPeriod;

    public double getDepositAmount() {
        return depositAmount;
    }
    //Public money property only for reading Amount (deposit amount)

    public int getDepositPeriod() {
        return depositPeriod;
    }
    //Public integer property only for reading Period (time of deposit in months)


    public Deposit(double depositAmount, int depositPeriod) {
        this.depositAmount = depositAmount;
        this.depositPeriod = depositPeriod;
    }
    //Constructor (for calling in class-inheritor) with parameters depositAmount
    //and depositPeriod, which creates object deposit with specified sum for
    //specified period.


    public abstract double income();
    //Abstract method income, which returns money value – amount of income
    //from deposit. Income is the difference between sum, withdrawn from
    //deposit upon expiration date and deposited sum.
}
