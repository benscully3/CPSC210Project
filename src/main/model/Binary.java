package model;

public class Binary extends CentralBody{
    private CentralBody centralBody1;  // Cannot be another binary
    private CentralBody centralBody2;  // Cannot be another binary

    // REQUIRES: Central body parameters cannot be binaries
    // EFFECT:
    public Binary(CentralBody centralBody1, CentralBody centralBody2){
        // stub
    }

    public CentralBody getCentralBody1(){
        return centralBody1;
    }

    public CentralBody getCentralBody2(){
        return centralBody2;
    }
}
