package ua.advanced.task5.part10;

public class Action {
    private Company company;
    private int actionPrice;

    public Action(Company company, int actionPrice) {
        this.actionPrice = actionPrice;
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public int getActionPrice() {
        return actionPrice;
    }

    public void setActionPrice(int actionPrice) {
        this.actionPrice = actionPrice;
    }

    @Override
    public String toString() {
        return "\t Company - " + company.getCompanyName() + ", action price = " + actionPrice;
    }

}
