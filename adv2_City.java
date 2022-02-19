package ua.advanced.task2;

public class City {
    private int cityID;
    private String cityName;

    public City() { }
    public City(int cityID, String cityName) {
        this.cityID = cityID;
        this.cityName = cityName;
    }

    public int getCityID() { return cityID; }
    public void setCityID(int countryID) { this.cityID = countryID; }

    public String getCityName() { return cityName; }

    public void setCityName(String countryName) { this.cityName = countryName; }

    @Override
    public String toString() {
        return "\nCity{" + "city ID = '" + cityID + '\'' + ", city name = '"
                + cityName + '\'' + '}';
    }

}
