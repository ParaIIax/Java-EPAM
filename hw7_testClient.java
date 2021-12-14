package ua.univer.task7;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestClient {
    @Test
    public void testAddDeposit() {
        BaseDeposit baseDeposit = new BaseDeposit(1000, 3);//157.62
        Client client = new Client();
        assertEquals(true, client.addDeposit(baseDeposit));
        for (int i = 0; i < 9; ++i)
            client.addDeposit(baseDeposit);
        assertEquals(false, client.addDeposit(baseDeposit));
    }
    @Test
    public void testTotalIncome() {
        BaseDeposit baseDeposit = new BaseDeposit(1000, 3);//157.62
        SpecialDeposit specialDeposit = new SpecialDeposit(1000, 4);//103.55
        LongDeposit longDeposit = new LongDeposit(1000, 7);//150.0
        Client client = new Client();
        client.addDeposit(baseDeposit);
        client.addDeposit(specialDeposit);
        client.addDeposit(longDeposit);
        assertEquals(411.17, client.totalIncome(), 0.01);
    }
    @Test
    public void testMaxIncome() {
        BaseDeposit baseDeposit = new BaseDeposit(1000, 3);//157.62
        SpecialDeposit specialDeposit = new SpecialDeposit(1000, 4);//103.55
        LongDeposit longDeposit = new LongDeposit(1000, 7);//150.0
        Client client = new Client();
        client.addDeposit(baseDeposit);
        client.addDeposit(specialDeposit);
        client.addDeposit(longDeposit);
        assertEquals(157.62, client.maxIncome(), 0.01);
    }
    @Test
    public void testGetIncomeByNumber() {
        BaseDeposit baseDeposit = new BaseDeposit(1000, 3);//157.62
        SpecialDeposit specialDeposit = new SpecialDeposit(1000, 4);//103.55
        LongDeposit longDeposit = new LongDeposit(1000, 7);//150.0
        Client client = new Client();
        client.addDeposit(baseDeposit);
        client.addDeposit(specialDeposit);
        client.addDeposit(longDeposit);
        assertEquals(103.55, client.getIncomeByNumber(2), 0.01);
        assertEquals(0.0, client.getIncomeByNumber(4), 0.01);
    }

}
