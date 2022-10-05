package model;

public abstract class Star extends CentralBody {
    protected int mass;
    protected int radius;
    protected int luminosity;
    protected int temperature;

    public int getMass(){

        return this.mass;
    }
    public int getRadius(){
        return this.radius;
    }
    public int getLuminosity(){

        return this.luminosity;
    }
    public int getTemperature(){

        return this.temperature;
    }
}
