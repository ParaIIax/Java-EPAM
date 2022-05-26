package ua.advanced.task5.part10;

import java.util.Random;

public class Broker extends Thread {
    private int brokerId;
    private Request request;
    private Exchange exchange;
    private Company[] companies;

    public Broker(int brokerId, Exchange exchange, Company[] companies) {
        this.brokerId = brokerId;
        this.exchange = exchange;
        this.companies = companies;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (exchange.checkWork()) {
            try {
                    int index = random.nextInt(companies.length);
                    boolean type = random.nextBoolean();
                    RequestTypeEnum requestType = RequestTypeEnum.SELL;
                    if (type)
                        requestType = RequestTypeEnum.BUY;
                    exchange.completeRequest(new Request(this, companies[index], requestType));
                    Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "broker " + "id: " + brokerId;
    }

}

