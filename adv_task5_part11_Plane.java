package ua.advanced.task5.part11;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Plane extends Thread {
    private Destination destination;
    private Airport airport;
    private int capacity;
    private PlaneRangeEnum range;
    private int planeId;
    private Semaphore terminals = Airport.terminalsSemaphore;
    private Semaphore ladders = Airport.laddersSemaphore;
    private boolean[] availableTerminals = Airport.availableTerminals;
    private boolean[] availableLadders = Airport.availableLadders;

    public Plane(Airport airport, int capacity, PlaneRangeEnum range, int planeId) {
        this.airport = airport;
        this.capacity = capacity;
        this.range = range;
        this.planeId = planeId;
    }

    @Override
    public void run() {
        Random random = new Random();
        Destination[] destinations = airport.getAvailableDestinations();
        while (true) {
            System.out.printf("The plane #%d is ready to receive people (waiting for free terminal)\n", planeId);
            try {
                terminals.acquire();
                int index = random.nextInt(destinations.length);
                while (destinations[index].getRange() != range) {
                    index = random.nextInt(destinations.length);
                }
                destination = destinations[index];

                int parkingNumber = -1;
                synchronized (availableTerminals) {
                    for (int i = 0; i < availableTerminals.length; i++)
                        if (!availableTerminals[i]) {
                            availableTerminals[i] = true;
                            parkingNumber = i;
                            System.out.printf("-People board the plane #%d through the terminal #%d...\n", planeId, i);
                            break;
                        }
                }
                Thread.sleep(capacity * 30);
                System.out.printf("\tPlane #%d released the terminal #%d and flew on a flight - %s\n",
                        planeId, parkingNumber, destination.toString());
                synchronized (availableTerminals) {
                    availableTerminals[parkingNumber] = false;
                }
                terminals.release();
                try {
                    if (range == PlaneRangeEnum.BIG)
                        Thread.sleep(10000);
                    else if (range == PlaneRangeEnum.MEDIUM)
                        Thread.sleep(7000);
                    else
                        Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Plane #%d is back and ready to disembark people (waiting for free ladder)\n", planeId);
                ladders.acquire();
                int parkingNumber1 = -1;
                synchronized (availableLadders) {
                    for (int i = 0; i < availableLadders.length; i++)
                        if (!availableLadders[i]) {
                            availableLadders[i] = true;
                            parkingNumber1 = i;
                            System.out.printf("-Plane #%d drops people off through ladder #%d...\n", planeId, i);
                            break;
                        }
                }
                Thread.sleep(capacity * 30);
                System.out.printf("\tPlane #%d released the ladder #%d and goes to checkup for some time...\n", planeId, parkingNumber1);
                synchronized (availableLadders) {
                    availableLadders[parkingNumber1] = false;
                }
                ladders.release();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return Plane.class.getSimpleName() + ": plane ID - " + planeId + ", plane capacity - " +
                capacity + ", plane maximum range - " + range + ", " + airport;
    }

}
