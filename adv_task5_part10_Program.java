package ua.advanced.task5.part10;

public class Program {
    public static void main(String args[]) {
        Company[] companies = { new Company("Apple"),
                new Company("Microsoft"),
                new Company("Tesla") };
        Exchange exchange = new Exchange(companies);
        Broker[] brokers = new Broker[10];
        for (int i = 0; i < brokers.length; ++i) {
            brokers[i] = new Broker(i, exchange, companies);
            brokers[i].start();
        }
        exchange.openTrading();
    }

}
