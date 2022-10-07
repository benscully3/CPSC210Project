package ui;

import model.*;

import java.util.Scanner;

import static java.lang.Double.parseDouble;

// Galaxy builder application
public class GalaxyBuilderApp {
    private Galaxy galaxy;
    private Scanner input;

    public GalaxyBuilderApp(){ runGalaxyBuilder(); }

    private void runGalaxyBuilder(){
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

        System.out.println("\nGoodbye!");
    }

    private void init(){
        String command = null;

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        print("Name your galaxy! (You can change it later)\n");
        command = input.next();
        galaxy = new Galaxy(command);

    }

    private void displayMainMenu(){
        if (galaxy.getSolarSystemCount() == 0){
            print("\nSelect from:");
            print("\tn -> Add a new solar system!");
            print("\tq -> Quit");

        } else {
            print("\nSelect from:");
            print("\tn -> Add a new solar system!");
            print("\te -> Edit your solar systems!");
            print("\tq -> Quit");
        }
    }

    private void processMainCommand(String command){
        if (galaxy.getSolarSystemCount() == 0) {
            if (command.equals("n")) {
                newSolarSystem();
            } else {
                System.out.println("Selection not valid...");
            }
        } else {
            if (command.equals("n")) {
                newSolarSystem();
            } else if (command.equals("e")) {
                editSolarSystems();
            } else {
                System.out.println("Selection not valid...");
            }

        }
    }

    private void newSolarSystem() {
        String name = null;
        CentralBody centralBody;
        SolarSystem solarSystem;

        print("\nEnter a name for your solar system!:");
        name = input.next();
        displayCentralBodies(false);
        centralBody =  processCentralBodyCommand( false);

        solarSystem = new SolarSystem(name, centralBody);

        galaxy.addSolarSystem(solarSystem);
    }

    private CentralBody processCentralBodyCommand(boolean binary) {
        boolean keepGoing = true;
        String command = input.next();

        if (binary){
            while (keepGoing) {
                if (command.equals("bh")) {
                    return makeBlackHole();
                } else if (command.equals("ns")) {
                    return makeNeutronStar();
                } else if (command.equals("wd")) {
                    return makeWhiteDwarf();
                } else if (command.equals("gs")) {
                    return makeGiantStar();
                } else {
                    System.out.println("Selection not valid...");
                }
            }
        } else {
            while (keepGoing) {
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
                    System.out.println("Selection not valid...");
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
        print("\nChoose a number for your star's brightness! " +
                "(entering 2 means twice as bright as the sun)");
        luminosity = inputNumber();
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
        print("\nChoose a number for your star's mass! " +
                "(entering 2 means twice the mass of the sun)");
        print("\nWhite Dwarfs must have a mass under 1.4 solar masses");

        while (keepGoing) {
            mass = inputNumber();
            if (mass > 1.4){
                print("Please enter a number under 1.4 :)");
            } else{
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
        print("\nChoose a number for your star's mass! " +
                "(entering 2 means twice the mass of the sun)");
        print("\n Neutron star mass must be between 1.4 and 2.5 solar masses");

        while (keepGoing) {
            mass = inputNumber();
            if ((mass < 1.4) || (mass > 2.5)){
                print("Please enter a number between 1.4 and 2.5 :)");
            } else{
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
        print("\nChoose a number for your black hole's mass! " +
                "(entering 2 means twice the mass of the sun)");
        print("\nBlack Hole mass must be at least 2.5 solar masses!");

        while (keepGoing) {
            mass = inputNumber();
            if (mass < 2.5){
                print("Please enter a number over 2.5 :)");
            } else{
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

        print("\nBinaries have two central bodies!");
        print("\nChoose your first central body!");
        displayCentralBodies(true);
        centralBody1 = processCentralBodyCommand(true);

        print("\nChoose your second central body!");
        displayCentralBodies(true);
        centralBody2 = processCentralBodyCommand(true);

        binary = new Binary(centralBody1, centralBody2);
        return binary;
    }

    private void editSolarSystems() {

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

    // REQUIRES: central body must be binary
    // MODIFIES: solarSystem
    // EFFECT: The two central bodies collapse into one, adding their mass
    //              if two white dwarfs -> supernova (destroys solar system)
    //              else if one white dwarf one 'normal star' -> supernova
    //              else if two main sequence or giants -> one giant
    //              else if one black hole -> one black hole
    //              else if one neutron star -> one neutron star
    private void coalesce(SolarSystem solarSystem){}

    private double inputNumber() {
        boolean keepGoing = true;
        double number = 0;

        while(keepGoing) {
            try {
                number = parseDouble(input.next());
                keepGoing = false;
            } catch (Exception NotANumber) {
                print("Please enter a number >:O");
            }
        }
        return number;
    }

    private void print(String println){
        System.out.println(println);
    }
}
