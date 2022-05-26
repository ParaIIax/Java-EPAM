package ua.advanced.task5.part11;

public class Destination {
    private String city;
    private PlaneRangeEnum range;

    public Destination(String city, PlaneRangeEnum range) {
        this.city = city;
        this.range = range;
    }

    public String getCity() {
        return city;
    }

    public PlaneRangeEnum getRange() {
        return range;
    }

    @Override
    public String toString() {
        return Destination.class.getSimpleName() + ": city - " + city + ", range - " + range;
    }

}

