package ua.univer.finTask.business_logic;

public class City {
    private int cityID;
    private int belongsTo;
    private String cityName;
    private int inhabitantsNumber;
    private boolean capitalSign;

    public City() { }
    public City(int cityID, int belongsTo, String cityName, int inhabitantsNumber, boolean capitalSign) {
        this.cityID = cityID;
        this.belongsTo = belongsTo;
        this.cityName = cityName;
        this.inhabitantsNumber = inhabitantsNumber;
        this.capitalSign = capitalSign;
    }

    public int getCityID() { return cityID; }
    public void setCityID(int cityID) { this.cityID = cityID; }

    public int getBelongsTo() { return belongsTo; }
    public void setBelongsTo(int belongsTo) { this.belongsTo = belongsTo; }

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public int getInhabitantsNumber() { return inhabitantsNumber; }
    public void setInhabitantsNumber(int inhabitantsNumber) { this.inhabitantsNumber = inhabitantsNumber; }

    public boolean getCapitalSign() { return capitalSign; }
    public void setCapitalSign(boolean capitalSign) { this.capitalSign = capitalSign; }

    @Override
    public String toString() {
        return "\nCity{" + "city ID = '" + cityID + '\'' + ", belongs to country with ID = '"
                + belongsTo + '\'' + ", city name = '" + cityName + '\'' +
                ", number of inhabitants = '" + inhabitantsNumber + '\'' +
                ", sign of a capital = '" + capitalSign + '\'' + '}';
    }

}
