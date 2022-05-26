package ua.advanced.task5.part10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Exchange {
    private List<Action> actions = new ArrayList();
    private List<Request> requests = new ArrayList();
    private AtomicBoolean isWorking = new AtomicBoolean(true);

    public Exchange(Company[] companies) {
        Random random = new Random();
        for (var company : companies)
            actions.add(new Action(company, random.nextInt(1000) + 500));
    }

    public Exchange(List<Action> actions) {
        this.actions = actions;
    }

    public synchronized void completeRequest(Request request) {
        Random random = new Random();
        requests.add(request);
        System.out.println(request + " completing...");
        for (int i = 0; i < actions.size(); ++i) {
            if (request.getRequestType() == RequestTypeEnum.BUY && actions.get(i).getCompany() == request.getCompany())
                actions.get(i).setActionPrice(actions.get(i).getActionPrice() + random.nextInt(10));
            else
                actions.get(i).setActionPrice(actions.get(i).getActionPrice() - random.nextInt(10));
        }
        fixIndex(request);
    }

    private void showActions() {
        for (var action : actions) {
            System.out.println(action);
        }
    }

    public void fixIndex(Request request) {
        int soldActions = 0;
        for (var req : requests) {
            if (soldActions >= 4)
                suspendTrading(request);
            if (request.getRequestType() == RequestTypeEnum.SELL && req.getRequestType() == RequestTypeEnum.SELL)
                soldActions++;
            else
                soldActions = 0;
        }
    }

    private void suspendTrading(Request request) {
        showActions();
        System.out.println("Closed, because " + request.getCompany().getCompanyName() +
                " " + request.getRequestType() + " was very often");
        isWorking = new AtomicBoolean(false);
    }

    public void openTrading() {
        System.out.println("Open");
        showActions();
    }

    public boolean checkWork() {
        return isWorking.get();
    }

}

