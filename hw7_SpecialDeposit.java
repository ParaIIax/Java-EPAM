package ua.univer.task7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecialDeposit extends Deposit{

    public SpecialDeposit(double depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    public double income() {
        double depositAmount = super.getDepositAmount();
        double defaultDepositAmount = super.getDepositAmount();
        double result;
        double monthPercent = 0.01;
        for (int i = 0; i < super.getDepositPeriod(); ++i) {
            result = depositAmount * monthPercent;
            depositAmount = depositAmount + result;
            monthPercent += 0.01;
        }
        result = depositAmount - defaultDepositAmount;
        BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
        result = bd.doubleValue();
        return result;
    }
    //SpecialDeposit implies income addition each month, amount of which (in percent)
    //equals to deposit expiration period. If during the first month 1% is added, during the
    //second month – 2% from the sum obtained after first month and so on.
}
