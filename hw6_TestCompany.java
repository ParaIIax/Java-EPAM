package ua.univer.task6;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCompany {
    @Test
    public void totalToPay() {
        Manager a = new Manager("Vasya1", 15000, 110);
        SalesPerson b = new SalesPerson("Vasya2", 16000, 110);
        SalesPerson c = new SalesPerson("Vasya3", 11000, 320);
        Manager d = new Manager("Vasya4", 12000, 250);
        Employee [] array = { a, b, c, d };
        Company apple = new Company(array);
        apple.giveEverybodyBonus(450);
        assertEquals(58650, apple.totalToPay(), 0.001);
    }
    @Test
    public void nameMaxSalary() {
        Manager a = new Manager("Vasya1", 15000, 110);
        SalesPerson b = new SalesPerson("Vasya2", 16000, 110);
        SalesPerson c = new SalesPerson("Vasya3", 11000, 320);
        Manager d = new Manager("Vasya4", 12000, 250);
        Employee [] array = { a, b, c, d };
        Company apple = new Company(array);
        apple.giveEverybodyBonus(450);
        assertEquals("Vasya2", apple.nameMaxSalary());
    }

}
