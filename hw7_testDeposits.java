package ua.univer.task7;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestDeposits {
    @Test
    public void testBaseDepositIncome() {
        BaseDeposit baseDeposit = new BaseDeposit(1000, 3);
        assertEquals(157.62, baseDeposit.income(), 0.01);
    }
    @Test
    public void testSpecialDepositIncome() {
        SpecialDeposit specialDeposit = new SpecialDeposit(1000, 2);
        assertEquals(30.20, specialDeposit.income(), 0.01);
    }
    @Test
    public void testLongDepositIncome() {
        LongDeposit longDeposit = new LongDeposit(1000, 8);
        assertEquals(322.5, longDeposit.income(), 0.01);
        LongDeposit longDeposit2 = new LongDeposit(1000, 6);
        assertEquals(0.0, longDeposit2.income(), 0.01);
    }

}
