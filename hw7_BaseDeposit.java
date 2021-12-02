package ua.univer.task7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseDeposit extends Deposit {

    public BaseDeposit(double depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    public double income() {
        double depositAmount = super.getDepositAmount();
        double defaultDepositAmount = super.getDepositAmount();
        double result;
        for (int i = 0; i < super.getDepositPeriod(); ++i) {
            result = depositAmount * 0.05;
            depositAmount = depositAmount + result;
        }
        result = depositAmount - defaultDepositAmount;
        BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
        result = bd.doubleValue();
        return result;
    }
    //BaseDeposit implies each month 5% of interest  from current deposit sum. Each
    //following month of income is calculated from the sum, which was received by
    //adding to current income sum of the previous month and is rounded to hundredth.
}
