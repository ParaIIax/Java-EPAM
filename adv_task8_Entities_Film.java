package ua.advanced.task8.Enities;

public class Film extends Entity {
    private String name;
    private int release_date;
    private String country_name;

    public String getName() {
        return name;
    }

    public int getReleaseDate() {
        return release_date;
    }

    public String getCountryName() {
        return country_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(int release_date) {
        this.release_date = release_date;
    }

    public void setCountryName(String country_name) {
        this.country_name = country_name;
    }

    @Override
    public String toString() {
        return Film.class.getSimpleName() + ": " + "id - " + getId() + ", date of release - "
                + release_date + ", the name of the country - " + country_name;
    }

}
