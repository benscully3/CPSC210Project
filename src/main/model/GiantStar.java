package model;

public class GiantStar extends Star {
    private double temperature = 6000;
    private double luminosity;
    private double stephanBoltzmann = 2e-16;

    public GiantStar(String name, double luminosity) {
        this.luminosity = luminosity;
        this.mass = 1.4 * Math.pow(luminosity, 0.286);
        this.radius = Math.sqrt(luminosity /
                (4.0 * 3.14 * stephanBoltzmann * Math.pow(temperature, 4)));
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

        return this.temperature;
    }
}
