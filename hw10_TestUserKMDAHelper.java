package ua.univer.task10;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TestUserKMDAHelper {
    @Test
    public void getUsersAllSalary() {
        List<User> users = new ArrayList<>();
        users.add(new User("a", "a", 20000));
        users.add(new User("b", "b", 18000));
        users.add(new User("c", "c", 31000));
        users.add(new User("d", "d", 30000));
        assertEquals(99000, UserKMDAHelper.getUsersAllSalary(users), 0.001);
    }

    @Test
    public void getUsersEachMonthMidSalary() throws IOException {
        List<User> users = UserKMDAHelper.getUserFromURL("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/d2b1a7d2-9222-4dfa-b57e-c0bb0b21485b/download/sichen-zp-2019.csv");
        List<User> users1 = UserKMDAHelper.getUserFromURL("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/38ef4e5e-72ec-4715-95d7-28c0fd42176c/download/liutii-zp-2019.csv");
        List<User> users2 = UserKMDAHelper.getUserFromURL("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/953aae94-8c82-4296-881f-f57b3a7be389/download/berezen-2019.csv");
        double[] arr = { 9673.75, 13650.203333333331, 13865.75 };
        assertEquals(Arrays.toString(arr), Arrays.toString(UserKMDAHelper.getUsersEachMonthMidSalary(users, users1, users2)));
    }

    @Test
    public void getUsersMaxMonthSalary() throws IOException {
        List<User> users = UserKMDAHelper.getUserFromURL("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/d2b1a7d2-9222-4dfa-b57e-c0bb0b21485b/download/sichen-zp-2019.csv");
        List<User> users1 = UserKMDAHelper.getUserFromURL("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/38ef4e5e-72ec-4715-95d7-28c0fd42176c/download/liutii-zp-2019.csv");
        List<User> users2 = UserKMDAHelper.getUserFromURL("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/953aae94-8c82-4296-881f-f57b3a7be389/download/berezen-2019.csv");
        assertEquals(3, UserKMDAHelper.getUsersMaxMonthSalary(users, users1, users2));
    }

}
