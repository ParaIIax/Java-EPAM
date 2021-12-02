package ua.univer.task7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LongDeposit extends Deposit{

    public LongDeposit(double depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    public double income() {
        double depositAmount = super.getDepositAmount();
        double defaultDepositAmount = super.getDepositAmount();
        int depositPeriod = super.getDepositPeriod();
        double result;
        if (depositPeriod > 6)
        {
            for (int i = 6; i < depositPeriod; ++i) {
                result = depositAmount * 0.15;
                depositAmount = depositAmount + result;
            }
            result = depositAmount - defaultDepositAmount;
            BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
            result = bd.doubleValue();
            return result;
        }
        return 0;
    }
    //LongDeposit implies that during first 6 months, no percent is added to client’s
    //deposit, but starting from 7th month, each month percent addition is 15% from
    //current deposit sum, thus encouraging client to make long-term deposits.
}
