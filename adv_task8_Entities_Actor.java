package ua.advanced.task8.Enities;

public class Actor extends Entity {
    private String full_name;
    private int birth_date;

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public int getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(int birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return Actor.class.getSimpleName() + ": id - " + getId() + ", full name - " +
                full_name + ", date of birth - " + birth_date;
    }

}
