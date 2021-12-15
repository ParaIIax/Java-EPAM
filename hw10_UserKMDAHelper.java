package ua.univer.task10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class UserKMDAHelper {
    public static List<User> getUserFromURL(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {//, Charset.forName("WINDOWS-1251")))) {
            br.readLine();
            while (br.ready()) {
                String text = br.readLine();
                String[] words = text.split(";");
                users.add(new User(
                        words[0],
                        words[1],
                        Double.parseDouble(words[2].replace(" ", "").replace(",", ".").replace("₴", ""))));
            }
        }
        return users;
    }

    public static List<User> getUserWithMaxSalary(List<User> users1, List<User> users2, List<User> users3){
        List<User> maxSalaryUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.addAll(users1);
        users.addAll(users2);
        users.addAll(users3);
        User maxSalaryUser = users.get(0);
        for (var user: users) {
            if (maxSalaryUser.getSalary() < user.getSalary())
                maxSalaryUser = user;
        }
        for (var user: users) {
            if (maxSalaryUser.getSalary() == user.getSalary())
                maxSalaryUsers.add(user);
        }
        return maxSalaryUsers;
    }

    public static List<User> getUserWithMinSalary(List<User> users1, List<User> users2, List<User> users3){
        List<User> minSalaryUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.addAll(users1);
        users.addAll(users2);
        users.addAll(users3);
        User minSalaryUser = users1.get(0);
        for (var user: users) {
            if (minSalaryUser.getSalary() > user.getSalary())
                minSalaryUser = user;
        }
        for (var user: users) {
            if (minSalaryUser.getSalary() == user.getSalary())
                minSalaryUsers.add(user);
        }
        return minSalaryUsers;
    }

    public static double getUsersAllSalary(List<User> users){
        double allSalaryUser = 0;
        for (var user: users) {
            allSalaryUser += user.getSalary();
        }
        return allSalaryUser;
    }

    public static List<User> getUsersWithMidSalary(List<User> users1, List<User> users2, List<User> users3){
        List<User> midSalaryUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.addAll(users1);
        users.addAll(users2);
        users.addAll(users3);
        double allSalaryUser = getUsersAllSalary(users);
        double midSalaryUser = allSalaryUser / users.size();
        for (var user: users) {
            if (user.getSalary() >= midSalaryUser - (midSalaryUser * 0.20) && user.getSalary() <= midSalaryUser + (midSalaryUser * 0.20)) {
                midSalaryUsers.add(user);
            }
        }
        return midSalaryUsers;
    }

    public static double[] getUsersEachMonthMidSalary(List<User> users1, List<User> users2, List<User> users3){
        double[] allSalaryUsersArr = new double[3];
        double[] midSalaryUsersArr = new double[allSalaryUsersArr.length];
        allSalaryUsersArr[0] = getUsersAllSalary(users1);
        allSalaryUsersArr[1] = getUsersAllSalary(users2);
        allSalaryUsersArr[2] = getUsersAllSalary(users3);
        for (int i = 0; i < allSalaryUsersArr.length; ++i) {
            midSalaryUsersArr[i] = allSalaryUsersArr[i] / users1.size();
        }
        return midSalaryUsersArr;
    }

    public static int getUsersMaxMonthSalary(List<User> users1, List<User> users2, List<User> users3){
        double[] eachMonthMidSalaries = getUsersEachMonthMidSalary(users1, users2, users3);
        double monthMaxSalary = eachMonthMidSalaries[0];
        int maxSalaryMonthIndex = 0;
        for (int i = 1; i < eachMonthMidSalaries.length; i++) {
            if (eachMonthMidSalaries[i] > monthMaxSalary) {
                monthMaxSalary = eachMonthMidSalaries[i];
                maxSalaryMonthIndex = i;
            }
        }
        return ++maxSalaryMonthIndex;
    }

}
