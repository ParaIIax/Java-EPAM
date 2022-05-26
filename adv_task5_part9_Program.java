package ua.advanced.task5.part9;

public class Program {
    public static void main(String[] args) {
        int numberOfParticipants = 5;
        Lot[] lots = { new Lot(numberOfParticipants, 0,100), new Lot(numberOfParticipants, 1,30), new Lot(numberOfParticipants, 2,125) };
        Auction auction = new Auction(lots, numberOfParticipants);
        auction.startAuction();
    }
    
}
