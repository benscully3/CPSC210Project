package model;

public abstract class Star extends CentralBody {
    protected double luminosity;
    protected double temperature;


    public double getLuminosity(){

        return this.luminosity;
    }
    public double getTemperature(){

        return this.temperature;
    }
}
