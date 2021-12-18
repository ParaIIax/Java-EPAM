package ua.univer.finTask.business_logic;

public class Country {
    private int countryID;
    private String countryName;

    public Country() { }
    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    public int getCountryID() { return countryID; }
    public void setCountryID(int countryID) { this.countryID = countryID; }

    public String getCountryName() { return countryName; }

    public void setCountryName(String countryName) { this.countryName = countryName; }

    @Override
    public String toString() {
        return "\nCountry{" + "country ID = '" + countryID + '\'' + ", country name = '"
                + countryName + '\'' + '}';
    }

}
