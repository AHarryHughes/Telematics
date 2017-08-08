package com.aharryhughes;

/**
 * Created by ahhughes8 on 7/21/17.
 */
public class VehicleInfo {

    private int vIN; // (Vehicle Identification Number)
    private double odometer; // (miles traveled)
    private double consumption; // (gallons of gas consumed)
    private double odometerReadingForLastOilChange;
    private double engineSizeLiters; // (e.g. 2.0, 4.5)

    public VehicleInfo() {
    }

    public int getvIN() {
        return vIN;
    }

    public void setvIN(int vIN) {
        this.vIN = vIN;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getOdometerReadingForLastOilChange() {
        return odometerReadingForLastOilChange;
    }

    public void setOdometerReadingForLastOilChange(double odometerReadingForLastOilChange) {
        this.odometerReadingForLastOilChange = odometerReadingForLastOilChange;
    }

    public double getEngineSizeLiters() {
        return engineSizeLiters;
    }

    public void setEngineSizeLiters(double engineSizeLiters) {
        this.engineSizeLiters = engineSizeLiters;
    }
}
