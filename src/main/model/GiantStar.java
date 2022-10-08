package model;

public class GiantStar extends Star {
    private final double TEMPERATURE = 6000;
    private double luminosity;
    private final double STEPHAN_BOLTZMANN = 2e-16;

    public GiantStar(String name, double luminosity) {
        this.luminosity = luminosity;
        this.mass = 1.4 * Math.pow(luminosity, 0.286);
        this.radius = Math.sqrt(luminosity /
                (4.0 * 3.14 * STEPHAN_BOLTZMANN * Math.pow(TEMPERATURE, 4)));
        this.name = name;
        this.centralBodyType = "Giant Star";

    }

    public boolean canSupernova() {
        return true;
    }

    public double getLuminosity() {

        return this.luminosity;
    }

    public double getTemperature() {

        return this.TEMPERATURE;
    }
}
