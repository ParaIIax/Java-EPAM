package ua.advanced.task5.part9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class Lot extends Thread {
    private List<Participant> participants;
    public static CountDownLatch auctionStart;
    public static CountDownLatch auctionEnd = new CountDownLatch(1);
    private int lotPrice;
    private int lotId;
    private Participant winner;

    public Lot(int numberOfParticipants, int lotId, int lotPrice) {
        auctionStart = new CountDownLatch(numberOfParticipants);
        participants = new ArrayList<>();
        this.lotPrice = lotPrice;
        this.lotId = lotId;
    }

    public boolean add(Participant e) {
        return participants.add(e);
    }

    public int getLotPrice() {
        return lotPrice;
    }

    public int getLotId() {
        return lotId;
    }

    public Participant getWinner() {
        return winner;
    }

    @Override
    public void run() {
        try {
            System.out.println("Lot #" + lotId);
            auctionStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bets are closed");
        winner = defineWinner();
        auctionEnd.countDown();
    }

    private Participant defineWinner() {
        Participant winner =
                Collections.max(participants
                        .stream()
                        .filter(o -> !o.isLost())
                        .collect(Collectors.toList()), Comparator.comparingInt(Participant::getCurrentLotPrice));
        System.out.println("The winner is participant #" + winner.getParticipantId() + ", with bet = " + winner.getCurrentLotPrice());
        winner.setCash(winner.getCash() - winner.getCurrentLotPrice());
        return winner;
    }

}

