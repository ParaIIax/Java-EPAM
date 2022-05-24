package ua.advanced.task5.part10;

public class Request {
    private Company company;
    private RequestTypeEnum requestType;
    private Broker broker;

    public Request(Broker broker, Company company, RequestTypeEnum requestType) {
        this.broker = broker;
        this.company = company;
        this.requestType = requestType;
        this.broker.setRequest(this);
    }

    public Company getCompany() {
        return company;
    }

    public RequestTypeEnum getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {
        return Request.class.getSimpleName() + ": broker - " + broker + ", company - " + company.getCompanyName()
                + ", request - " + requestType;
    }

}
