package model;

public abstract class CentralBody extends Body {
    String centralBodyType;

    public abstract boolean canSupernova();

    public String getCentralBodyType() {
        return centralBodyType;
    }
}
