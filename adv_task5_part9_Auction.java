package ua.advanced.task5.part9;

import java.util.Random;

public class Auction {
    private int numberOfParticipants;
    private int[] participantsCash;
    private Lot[] lots;

    public Auction(Lot[] lots, int numberOfParticipants) {
        this.lots = lots;
        this.numberOfParticipants = numberOfParticipants;
        participantsCash = new int[numberOfParticipants];
        for (int i = 0; i < numberOfParticipants; i++)
            participantsCash[i] = 350 + new Random().nextInt(10);
    }

    public Auction(Lot[] lots, int[] participantsCash, int numberOfParticipants) {
        this.lots = lots;
        this.participantsCash = participantsCash;
        this.numberOfParticipants = numberOfParticipants;
    }

    public void startAuction() {
        for (int i = 0; i < lots.length; ++i) {
            Lot lot = new Lot(numberOfParticipants, lots[i].getLotId(), lots[i].getLotPrice());
            lot.start();
            Participant[] participants = new Participant[numberOfParticipants];
            for (int j = 0; j < numberOfParticipants; j++) {
                participants[j] = new Participant(j, lots[i].getLotPrice(), participantsCash[j]);
                lot.add(participants[j]);
                participants[j].start();
            }

            try {
                lot.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            participantsCash[lot.getWinner().getParticipantId()] = lot.getWinner().getCash();
            System.out.println("After lot #" + lots[i].getLotId() + ":");
            for (int j = 0; j < numberOfParticipants; j++)
                System.out.println(" - participant #" + participants[j].getParticipantId() + " cash = " + participants[j].getCash());
        }
    }

}

