package ua.advanced.task5.part11;

import java.util.concurrent.Semaphore;

public class Airport {
    public String airportName;
    public static Semaphore terminalsSemaphore;
    public static Semaphore laddersSemaphore;
    public static boolean[] availableTerminals;
    public static boolean[] availableLadders;
    private Destination[] availableDestinations;

    public Airport(String airportName, Destination[] availableDestinations, int terminalCount, int ladderCount) {
        this.airportName = airportName;
        this.availableDestinations = availableDestinations;
        terminalsSemaphore = new Semaphore(terminalCount, true);
        laddersSemaphore = new Semaphore(ladderCount, true);
        availableTerminals = new boolean[terminalCount];
        availableLadders = new boolean[ladderCount];
    }

    public Destination[] getAvailableDestinations() {
        return availableDestinations;
    }

    public void startWorking(Plane[] planes) {
        System.out.println("Airport is starting to work...");
        for (int i = 0; i < planes.length; ++i)
            planes[i].start();
    }

    @Override
    public String toString() {
        return Airport.class.getSimpleName() + ": airport name - " + airportName;
    }

}

