package com.aharryhughes;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner scanner = new Scanner(System.in);

        VehicleInfo vehicleInfo = new VehicleInfo();

//        private int vIN; // (Vehicle Identification Number)
//        private double odometer; // (miles traveled)
//        private double consumption; // (gallons of gas consumed)
//        private double odometerReadingForLastOilChange;
//        private double engineSizeLiters; // (e.g. 2.0, 4.5)

        System.out.println("What is your Vehicle Identification Number?");
        vehicleInfo.setvIN(scanner.nextInt());
        System.out.println();

        System.out.println("How many miles has your vehicle traveled?");
        vehicleInfo.setOdometer(scanner.nextDouble());
        System.out.println();

        System.out.println("How many gallons of gas consumed by your vehicle?");
        vehicleInfo.setConsumption(scanner.nextDouble());
        System.out.println();

        System.out.println("What is your Odometer Reading for your last Oil Change?");
        vehicleInfo.setOdometerReadingForLastOilChange(scanner.nextDouble());
        System.out.println();

        System.out.println("What is your engine size in liters?");
        vehicleInfo.setEngineSizeLiters(scanner.nextDouble());
        System.out.println();

        TelematicsService telematicsService = null;
        try {
            telematicsService = new TelematicsService();
            telematicsService.report(vehicleInfo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
