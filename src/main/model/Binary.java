package model;

public class Binary extends CentralBody {
    private CentralBody centralBody1;  // Cannot be another binary
    private CentralBody centralBody2;  // Cannot be another binary

    // REQUIRES: Central body parameters cannot be binaries
    // EFFECT: Create a binary consisting of two CentralBodies with a given name
    public Binary(String name, CentralBody centralBody1, CentralBody centralBody2) {
        this.centralBody1 = centralBody1;
        this.centralBody2 = centralBody2;
        this.mass = centralBody1.getMass() + centralBody2.getMass();
        this.radius = centralBody1.getRadius() + centralBody2.getRadius();
        this.name = name;
        this.centralBodyType = "Binary";
    }

    // EFFECT: Returns false because binaries cannot go supernova (in this program)
    public boolean canSupernova() {
        return false;
    }

    public CentralBody getCentralBody1() {
        return centralBody1;
    }

    public CentralBody getCentralBody2() {
        return centralBody2;
    }
}
