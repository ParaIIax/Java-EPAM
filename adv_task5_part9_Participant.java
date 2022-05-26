package ua.advanced.task5.part9;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Participant extends Thread {
    private int participantId;
    private int currentLotPrice;
    private int cash;
    private boolean lost;
    private CountDownLatch bidStart = Lot.auctionStart;
    private CountDownLatch bidEnd = Lot.auctionEnd;

    public Participant(int id, int lotPrice, int cash) {
        this.participantId = id;
        this.currentLotPrice = lotPrice;
        this.cash = cash;
    }

    public int getParticipantId() {
        return participantId;
    }

    public int getCurrentLotPrice() {
        return currentLotPrice;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public boolean isLost() {
        return lost;
    }

    @Override
    public void run() {
        try {
            if (cash < currentLotPrice) {
                lost = true;
                bidStart.countDown();
                System.out.println("Participant #" + participantId + " lost (cash (" + cash + ") < price (" + currentLotPrice + "))");
                return;
            }
            System.out.println("Participant #" + participantId + " takes part in the lot with cash = " + cash);
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2500));

            int delta = new Random().nextInt(10);
            currentLotPrice += delta;
            currentLotPrice = currentLotPrice < cash ? currentLotPrice : cash;
            System.out.println("Participant #" + participantId + " made a bet = " + currentLotPrice);

            for (int i = 0; i < new Random().nextInt(4); ++i) {
                boolean changeBet = new Random().nextBoolean();
                if (changeBet) {
                    delta = new Random().nextInt(10);
                    currentLotPrice += delta;
                    currentLotPrice = currentLotPrice < cash ? currentLotPrice : cash;
                    System.out.println("Participant #" + participantId + " changed a bet to " + currentLotPrice);
                }
            }
            bidStart.countDown();
            this.bidEnd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

