package model;

// Abstract class to represent an astronomical body with a name, radius and mass
public abstract class Body {
    protected double mass;
    protected double radius;
    protected String name;

    // MODIFIES: this
    // EFFECT: change the body's name
    public void changeName(String newName) {
        this.name = newName;

        EventLog.getInstance().logEvent(new Event("Changed body's name to " + name));
    }

    // getters
    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return this.radius;
    }

    public String getName() {
        return this.name;
    }
}
