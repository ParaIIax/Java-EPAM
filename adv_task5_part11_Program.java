package ua.advanced.task5.part11;

import java.util.Random;

public class Program {
    public static void main(String args[]) {
        Destination[] destinations = { new Destination("New York", PlaneRangeEnum.BIG),
                new Destination("Rum", PlaneRangeEnum.MEDIUM),
                new Destination("Odessa", PlaneRangeEnum.SMALL),
                new Destination("Tokyo", PlaneRangeEnum.BIG),
                new Destination("London", PlaneRangeEnum.MEDIUM) };
        Airport airport = new Airport("Boryspil", destinations, 5, 6);
        Random random = new Random();
        Plane[] planes = new Plane[7];
        System.out.println("---------------------Planes---------------------");
        for (int i = 0; i < planes.length; ++i) {
            PlaneRangeEnum planeRange;
            int randomRange = random.nextInt(3);

            if (randomRange == 1) {
                planeRange = PlaneRangeEnum.BIG;
            }
            else if (randomRange == 2) {
                planeRange = PlaneRangeEnum.MEDIUM;
            }
            else {
                planeRange = PlaneRangeEnum.SMALL;
            }
            planes[i] = new Plane(airport, random.nextInt(100) + 30, planeRange, i);
            System.out.println(planes[i]);
        }
        System.out.println("------------------------------------------------");
        airport.startWorking(planes);
    }

}
