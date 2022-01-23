package com.gfg;

public class CarInIndia extends Car implements GovtNormsForCar{

    public CarInIndia(String rcNumber, Engine engine) {
        super(rcNumber, engine);
    }

    @Override
    public void followEnvPolicies() {

    }

    @Override
    public String getRC() {
        return null;
    }

    @Override
    public String getInsuranceDoc() {
        return null;
    }

    @Override
    public void followRTORules() {

    }

    @Override
    public String getEuropeRelatedDetails() {
        return null;
    }

    final public String drivingSide(){
        return "Left";
    }
}
