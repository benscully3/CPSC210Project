package ui;

import Exceptions.BadCommand;
import Exceptions.BadNegativeNumber;
import Exceptions.NameAlreadyUsed;
import model.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Double.parseDouble;

// Galaxy builder application
public class GalaxyBuilderApp {
    private Galaxy galaxy;
    private Scanner input;

    public GalaxyBuilderApp() {
        runGalaxyBuilder();
    }

    private void runGalaxyBuilder() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMainCommand(command);
            }
        }

        System.out.println("\nGoodbye! Thanks for playing!");
    }

    private void init() {
        String command = null;

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        print("Name your galaxy! (You can change it later)\n");
        command = input.next();
        command = command.toLowerCase();
        galaxy = new Galaxy(command);

    }

    private void displayMainMenu() {
        if (galaxy.getSolarSystemCount() == 0) {
            print("\nSelect from:");
            print("\tn -> Add a new solar system!");
            print("\tq -> Quit");

        } else {
            print("\nSelect from:");
            print("\tn -> Add a new solar system!");
            print("\te -> Edit your solar systems!");
            print("\td -> Display your solar systems!");
            print("\tq -> Quit");
        }
    }

    private void processMainCommand(String command) {
        if (galaxy.getSolarSystemCount() == 0) {
            if (command.equals("n")) {
                newSolarSystem();
            } else {
                System.out.println("Selection not valid :(, try again");
            }
        } else {
            if (command.equals("n")) {
                newSolarSystem();
            } else if (command.equals("e")) {
                editSolarSystems();
            } else if (command.equals("d")) {
                displaySolarSystems();
            } else {
                System.out.println("Selection not valid :(, try again");
            }

        }
    }

    private void displaySolarSystems() {
        String name = galaxy.getName();
        HashMap<String, SolarSystem> solarSystems = galaxy.getSolarSystems();
        boolean keepGoing = true;
        String command = null;

        print("\nYOUR GALAXY: " + name);
        print(" SOLAR SYSTEMS");
        for (SolarSystem solarSystem : solarSystems.values()) {
            displaySolarSystemInfo(solarSystem);
        }
        print("\nType 'back' (without the quotes) to exit to the main page");
        while (keepGoing) {
            command = input.next();
            if (command.equals("back")) {
                keepGoing = false;
            }
        }
    }

    private void displaySolarSystemInfo(SolarSystem solarSystem) {
        String name = solarSystem.getName();
        String centralBodyType = solarSystem.getCentralBody().getCentralBodyType();
        String planetCount = Integer.toString(solarSystem.getPlanetCount());

        print("\nName: " + name);
        print("\tCentral body: " + centralBodyType);
        print("\tNumber of planets: " + planetCount);

    }

    private void newSolarSystem() {
        String name = null;
        CentralBody centralBody;
        SolarSystem solarSystem;
        boolean keepGoing = true;


        displayCentralBodies(false);
        centralBody = processCentralBodyCommand(false);

        while (keepGoing) {
            try {
                print("\nEnter a name for your solar system!:");
                name = input.next();
                name = name.toLowerCase();
                solarSystem = new SolarSystem(name, centralBody);
                galaxy.addSolarSystem(solarSystem);
                print("Congratulations! You made a solar system! Add planets in the edit solar systems page.");
                keepGoing = false;
            } catch (Exception e) {
                print("Looks like a solar system is named that already...");
            }
        }
    }

    private CentralBody processCentralBodyCommand(boolean binary) {
        boolean keepGoing = true;
        String command;

        if (binary) {
            while (keepGoing) {
                command = input.next();
                command = command.toLowerCase();
                if (command.equals("bh")) {
                    return makeBlackHole();
                } else if (command.equals("ns")) {
                    return makeNeutronStar();
                } else if (command.equals("wd")) {
                    return makeWhiteDwarf();
                } else if (command.equals("gs")) {
                    return makeGiantStar();
                } else {
                    System.out.println("Selection not valid :(, try again");
                }
            }
        } else {
            while (keepGoing) {
                command = input.next();
                command = command.toLowerCase();
                if (command.equals("bh")) {
                    return makeBlackHole();
                } else if (command.equals("ns")) {
                    return makeNeutronStar();
                } else if (command.equals("wd")) {
                    return makeWhiteDwarf();
                } else if (command.equals("gs")) {
                    return makeGiantStar();
                } else if (command.equals("by")) {
                    return makeBinary();
                } else {
                    System.out.println("Selection not valid :(, try again");
                }
            }
        }
        return null;
    }

    private CentralBody makeGiantStar() {
        GiantStar giantStar;
        String name;
        double luminosity = 0;

        print("\nName your star!:");
        name = input.next();
        name = name.toLowerCase();
        print("\nChoose a number for your star's brightness! " +
                "(entering 2 means twice as bright as the sun)");
        luminosity = inputPositiveNumber();
        giantStar = new GiantStar(name, luminosity);
        return giantStar;
    }

    private CentralBody makeWhiteDwarf() {
        WhiteDwarf whiteDwarf;
        String name;
        double mass = 0;
        boolean keepGoing = true;

        print("\nName your star!:");
        name = input.next();
        name = name.toLowerCase();
        print("\nChoose a number for your star's mass! " +
                "(entering 2 means twice the mass of the sun)");
        print("\nWhite Dwarfs must have a mass under 1.4 solar masses");

        while (keepGoing) {
            mass = inputPositiveNumber();
            if (mass > 1.4) {
                print("Please enter a number under 1.4 :)");
            } else {
                keepGoing = false;
            }
        }
        whiteDwarf = new WhiteDwarf(name, mass);
        return whiteDwarf;
    }

    private CentralBody makeNeutronStar() {
        NeutronStar neutronStar;
        String name;
        double mass = 0;
        boolean keepGoing = true;

        print("\nName your star!:");
        name = input.next();
        name = name.toLowerCase();
        print("\nChoose a number for your star's mass! " +
                "(entering 2 means twice the mass of the sun)");
        print("\n Neutron star mass must be between 1.4 and 2.5 solar masses");

        while (keepGoing) {
            mass = inputPositiveNumber();
            if ((mass < 1.4) || (mass > 2.5)) {
                print("Please enter a number between 1.4 and 2.5 :)");
            } else {
                keepGoing = false;
            }
        }
        neutronStar = new NeutronStar(name, mass);
        return neutronStar;
    }

    private CentralBody makeBlackHole() {
        BlackHole blackHole;
        String name;
        double mass = 0;
        boolean keepGoing = true;

        print("\nName your black hole!:");
        name = input.next();
        name = name.toLowerCase();
        print("\nChoose a number for your black hole's mass! " +
                "(entering 2 means twice the mass of the sun)");
        print("\nBlack Hole mass must be at least 2.5 solar masses!");

        while (keepGoing) {
            mass = inputPositiveNumber();
            if (mass < 2.5) {
                print("Please enter a number over 2.5 :)");
            } else {
                keepGoing = false;
            }
        }

        blackHole = new BlackHole(name, mass);
        return blackHole;
    }

    private CentralBody makeBinary() {
        Binary binary;
        CentralBody centralBody1;
        CentralBody centralBody2;
        String name;

        print("\nBinaries have two central bodies!");
        print("\nChoose your first central body!");
        displayCentralBodies(true);
        centralBody1 = processCentralBodyCommand(true);

        print("\nChoose your second central body!");
        displayCentralBodies(true);
        centralBody2 = processCentralBodyCommand(true);

        print("\nFinally name the binary!");
        name = input.next();
        binary = new Binary(name, centralBody1, centralBody2);
        return binary;
    }

    private void editSolarSystems() {
        String command = null;
        boolean keepGoing = true;

        HashMap<String, SolarSystem> solarSystems = galaxy.getSolarSystems();
        print("\nChoose which solar system to edit");
        for (String solarSystemName : solarSystems.keySet()) {
            displaySolarSystem(solarSystemName);
        }
        print("\tback = Back to main page");
        while (keepGoing) {
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("back")) {
                keepGoing = false;
            } else {
                try {
                    processSolarSystemCommand(command, solarSystems.keySet());
                    keepGoing = false;
                } catch (BadCommand e) {
                    print("\n That solar system doesn't exist :(, try again!");
                }
            }
        }

    }

    private void processSolarSystemCommand(String command, Set<String> solarSystemNames) throws BadCommand {
        for (String solarSystemName : solarSystemNames) {
            if (command.equals(solarSystemName)) {
                editSolarSystem(solarSystemName);
                return;
            }
        }
        throw new BadCommand();
    }

    private void editSolarSystem(String solarSystemName) {
        SolarSystem solarSystem = galaxy.getSolarSystem(solarSystemName);
        HashMap<String, Planet> planets = solarSystem.getPlanets();
        displayEditSolarSystem(planets, solarSystem.getPlanetCount(), solarSystem);

    }

    private void displayEditSolarSystem(HashMap<String, Planet> planets, int planetCount, SolarSystem solarSystem) {
        String command = null;
        boolean keepGoing = true;

        if (planetCount == 0) {
            print("\nAdd a planet!");
            addPlanet(solarSystem.getName());
        } else {
            print("\nChoose what to do:");
            print("\ta -> Add a new planet");
            print("\tr -> Remove a planet");
            print("\td -> Display solar system data");
            print("\tback -> Back to main page");

            while (keepGoing) {
                command = input.next();
                command = command.toLowerCase();
                if (command.equals("back")) {
                    keepGoing = false;
                } else {
                    try {
                        processEditSolarSystemCommand(command, planets, solarSystem.getName());
                        keepGoing = false;
                    } catch (BadCommand e) {
                        System.out.println("Selection not valid :(, try again");
                    }
                }
            }
        }
    }

    private void processEditSolarSystemCommand(String command, HashMap<String, Planet> planets,
                                               String solarSystemName) throws BadCommand {
        if (command.equals("a")) {
            addPlanet(solarSystemName);
        } else if (command.equals("r")) {
            removePlanet(planets);
        } else if (command.equals("d")) {
            displaySolarSystemData(solarSystemName, planets);
        } else {
            throw new BadCommand();
        }
    }

    private void displaySolarSystemData(String solarSystemName, HashMap<String, Planet> planets) {
        CentralBody centralBody;
        String command;
        boolean keepGoing = true;

        centralBody = galaxy.getSolarSystem(solarSystemName).getCentralBody();
        displayCentralBody(centralBody);
        print("\n PLANETS:");
        for (Planet p : planets.values()) {
            displayPlanet(p);
        }
        print("\nType 'back' (without the quotes) to exit to the main page");

        while (keepGoing) {
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("back")) {
                keepGoing = false;
            }
        }
    }

    private void displayCentralBody(CentralBody centralBody) {
        String name = centralBody.getName();
        String mass = String.format("%.2f", centralBody.getMass());
        String radius = String.format("%.2f", centralBody.getRadius());
        String centralBodyType = centralBody.getCentralBodyType();
        print("\n CENTRAL BODY:");
        print("\nName: " + name + " - " + centralBodyType);
        print("\tMass: " + mass + " Solar masses");
        print("\tRadius: " + radius + " Solar radii");

        if (centralBodyType.equals("Giant Star")) {
            GiantStar giantStar = (GiantStar) centralBody;
            String luminosity = String.format("%.2f", giantStar.getLuminosity());
            print("\tLuminosity: " + luminosity + " Solar luminosities");
        }
    }

    private void displayPlanet(Planet p) {
        String name;
        String radius;
        String mass;
        String orbit;
        String moon = "No moon";
        String planetType = "Gas giant planet";

        name = p.getName();
        radius = String.format("%.2f", p.getRadius());
        orbit = String.format("%.2f", p.getOrbitSize());
        mass = String.format("%.2f", p.getMass());
        if (p.isMoon()) {
            moon = "Has a moon";
        }
        if (p.isRocky()) {
            planetType = "Rocky planet";
        }
        ;

        print("\nName:" + name + " - " + planetType + " - " + moon);
        print("\t-Mass: " + mass + " Earth masses");
        print("\t-Radius: " + radius + " Earth radii");
        print("\t-Orbit Size: " + orbit + " Earth orbits");
    }

    private void removePlanet(HashMap<String, Planet> planets) {
        boolean keepGoing = true;
        String command = null;
        Planet planet = null;

        Set<String> planetNames = planets.keySet();
        print("\nChoose a planet to remove - !This cannot be undone!");
        for (String planetName : planetNames) {
            displayPlanetName(planetName);
        }
        print("\tback -> Back to main page");

        while (keepGoing) {
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("back")) {
                keepGoing = false;
            } else {
                try {
                    planet = processPlanetCommand(command, planets);
                    keepGoing = false;
                } catch (BadCommand e) {
                    print("That planet wasn't found :( Try again!");
                }
            }
        }
        planets.remove(planet.getName());
        print("Success! " + planet.getName() + " was removed.");

    }

    private Planet processPlanetCommand(String command, HashMap<String, Planet> planets) throws BadCommand {
        Set<String> planetNames = planets.keySet();
        for (String planetName : planetNames) {
            if (command.equals(planetName)) {
                return planets.get(planetName);
            }
        }
        throw new BadCommand();

    }

    private void displayPlanetName(String planetName) {
        print("\t" + planetName);
    }

    private void addPlanet(String solarSystemName) {
        String planetName = "";
        boolean moon;
        double radius;
        double orbitSize;
        Planet planet = null;
        boolean collide;

        SolarSystem solarSystem = galaxy.getSolarSystem(solarSystemName);
        print("\nName your planet!");
        planetName = namePlanet(solarSystem);

        print("\nNow choose your planet's size:" + "\n\t(Entering 2 is twice as big as Earth)");
        radius = inputPositiveNumber();
        print("\nAnd now choose your planet's orbit:" + "\n\t(Again, 2 is twice the orbit size of Earth)");
        orbitSize = inputPositiveNumber();
        print("\nFinally, would you like your planet to have a moon?" + "\n\ty -> yes" + "\n\tn -> no");
        moon = processMoonCommand();

        planet = new Planet(planetName, radius, orbitSize, moon);
        collide = solarSystem.addPlanet(planet);

        madePlanet(collide, radius);
    }

    private void madePlanet(boolean collide, double radius) {
        if (collide) {
            print("\nOh! The planet you added had the same orbit as a pre-existing one!");
            print("They collided, and combined! The new planet has the same name as the old one.");
            print("The collision also gave the planet a moon!");
            print("Look in Edit Solar Systems -> Solar System -> Display solar system data to see more!");
        } else if (radius > 5) {
            print("\nBased on the size you entered, a new Gas Giant planet has been added!");
        } else {
            print("\nBased on the size you entered, a new rocky planet has been added!");
        }
    }

    private String namePlanet(SolarSystem solarSystem) {
        boolean keepGoing = true;
        String planetName = "";

        while (keepGoing) {
            planetName = input.next();
            planetName = planetName.toLowerCase();
            try {
                checkName(planetName, solarSystem.getPlanets());
                keepGoing = false;
            } catch (NameAlreadyUsed e) {
                print("\nThis solar system has a planet with that name already. Pick a different name");
            }
        }
        return planetName;
    }

    private boolean processMoonCommand() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("y")) {
                return true;
            } else if (command.equals("n")) {
                return false;
            } else {
                print("Please enter y or n :)");
            }
        }
        return false;
    }

    private void checkName(String planetName, HashMap<String, Planet> planets) throws NameAlreadyUsed {
        Set<String> planetNames = planets.keySet();
        for (String pName : planetNames) {
            if (pName.equals(planetName)) {
                throw new NameAlreadyUsed();
            }
        }
    }


    private void displaySolarSystem(String solarSystemName) {
        print("\t" + solarSystemName);
    }

    private void displayCentralBodies(boolean binary) {
        if (binary) {
            print("\nChoose a central body!:");
            print("\tbh -> Black Hole");
            print("\tns -> Neutron Star");
            print("\tgs -> Giant Star");
            print("\twd -> White Dwarf");

        } else {
            print("\nChoose a central body!:");
            print("\tbh -> Black Hole");
            print("\tns -> Neutron Star");
            print("\tgs -> Giant Star");
            print("\twd -> White Dwarf");
            print("\tby -> Binary (You will choose two more central bodies)");

        }
    }

    private double inputPositiveNumber() {
        boolean keepGoing = true;
        double number = 0;

        while (keepGoing) {
            try {
                number = parseDouble(input.next());
                if (number <= 0) {
                    throw new BadNegativeNumber();
                }
                keepGoing = false;
            } catch (Exception e) {
                print("Please enter a positive number >:O");
            }
        }
        return number;
    }

    private void print(String println) {
        System.out.println(println);
    }
}
