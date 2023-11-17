package data;

public abstract class Users {
    private String name;
    private String lastName;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return getName() + " " + getLastName();
    }

    public abstract boolean logIn(String input);
}
