package model;

// Abstract class for a central body at the centre of a solar system.
public abstract class CentralBody extends Body {
    String centralBodyType;

    // EFFECT: Abstract function to check if a central body can go supernova
    public abstract boolean canSupernova();

    public String getCentralBodyType() {
        return centralBodyType;
    }
}
