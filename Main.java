package ua.univer;

public class Main {

    public static void main(String[] args) {
        int n = 142;
        //Task 1
        /*if (n > 0) {
            System.out.println(n * n);
        } else if (n < 0) {
            System.out.println(-n);
        } else if (n == 0) {
            System.out.println(0);
        }*/

        //Task 2
        /*int [] mas = new int[3];
        for (int i = 0;n > 0; i++) {
            mas[i] = n%10;
            n = n / 10;
        }
        for (int i = 0; i < mas.length; i++) {
            for (int j = 0; j < mas.length; j++) {
                if (mas[i] >= mas[j]) {
                    int x = mas[i];
                    mas[i] = mas[j];
                    mas[j] = x;
                }
            }
        }
        System.out.println(mas[0] * 100 + mas[1] * 10 + mas[2]);*/

        //Task 3
        /*int x = 0;
        while (n > 0) {
            if (n % 2 == 0) {
                x = x + n % 10;
            }
            n = n / 10;
        }
        System.out.println(x);*/

        //Task 4
        /*int counter = 0;
        while (n > 0) {
            //System.out.println(n % 2);
            if (n % 2 != 0)
                ++counter;
            n = n / 2;
        }
        System.out.println(counter);*/
    }
}
