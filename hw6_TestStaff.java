package ua.univer.task6;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestStaff {
    @Test
    public void toPay() {
        Employee employee = new Employee("Vasya", 15000);
        employee.setBonus(500);
        assertEquals(15500, employee.toPay(), 0.001);
    }
    @Test
    public void setSalesPersonBonus() {
        SalesPerson salesPerson = new SalesPerson("Vasya", 15000, 250);
        salesPerson.setBonus(500);
        assertEquals(16500, salesPerson.toPay(), 0.001);
    }
    @Test
    public void setManagerBonus() {
        Manager manager = new Manager("Vasya", 15000, 110);
        manager.setBonus(500);
        assertEquals(16000, manager.toPay(), 0.001);
    }

}
