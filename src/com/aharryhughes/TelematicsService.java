package com.aharryhughes;

import com.fasterxml.jackson.databind.ObjectMapper;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by ahhughes8 on 7/21/17.
 */
public class TelematicsService {

    public TelematicsService() throws FileNotFoundException {
    }

    public void report(VehicleInfo vehicleInfo){
        //Create new file named after vin
        int vin = vehicleInfo.getvIN();
        File file = new File("./"+((Integer) vin).toString() + ".json");

        //write to file the information of vehicle
        try {
            FileWriter fileWriter = new FileWriter(file);
            String newVehicleInstance = "{\"vIN\": \""+vehicleInfo.getvIN()+"\", \"odometer\": \""+vehicleInfo.getOdometer()+"\", \"consumption\": \""+vehicleInfo.getConsumption()+"\", \"odometerReadingForLastOilChange\": \""+vehicleInfo.getOdometerReadingForLastOilChange()+"\", \"engineSizeLiters\": \""+vehicleInfo.getEngineSizeLiters()+"\"}";
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(vehicleInfo);
            fileWriter.write(json);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //find all the json files and add to an arraylist
        ArrayList<VehicleInfo> vList = new ArrayList<>();
        try {
            File thisFile = new File(".");
            for (File f : thisFile.listFiles()) {
                if (f.getName().endsWith(".json")) {
                    // Now you have a File object named "f". You can use this in the FileReader constructor
                    String json = null;
                    json = new String(Files.readAllBytes(Paths.get(f.getPath())));
                    ObjectMapper mapper = new ObjectMapper();
                    VehicleInfo vi = mapper.readValue(json, VehicleInfo.class);
                    vList.add(vi);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        double odomAvg = 0;
        double conAvg = 0;
        double oilAvg = 0;
        double sizeAvg = 0;
        String nuHTML = "";

        for (VehicleInfo v :vList) {
            odomAvg += v.getOdometer();
            conAvg += v.getConsumption();
            oilAvg += v.getOdometerReadingForLastOilChange();
            sizeAvg += v.getEngineSizeLiters();

            nuHTML +=
                    "    <tr>\n" +
                    "        <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td align=\"center\">"+v.getvIN()+"</td><td align=\"center\">"+v.getOdometer()+"</td><td align=\"center\">"+v.getConsumption()+"</td><td align=\"center\">"+v.getOdometerReadingForLastOilChange()+"</td><td align=\"center\">"+v.getEngineSizeLiters()+"</td>\n" +
                    "    </tr>\n" ;


        }

        odomAvg /= vList.size();
        conAvg /= vList.size();
        oilAvg /= vList.size();
        sizeAvg /= vList.size();

        String htmlPart1 = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Vehicle Telematics Dashboard</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1 align=\"center\">Averages for # vehicles</h1>\n" +
                "<table align=\"center\">\n" +
                "    <tr>\n" +
                "        <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td align=\"center\">"+odomAvg+"</td><td align=\"center\">"+conAvg+"</td><td align=\"center\">"+oilAvg+"</td><td align=\"center\">"+sizeAvg+"</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<br>" +
                "<h1 align=\"center\">History</h1>\n";



        String finalHTML = htmlPart1 +
                "<table align=\"center\" border=\"1\">\n" + nuHTML  +
                "</table>\n" + "</body>\n" + "</html>";

        File htmlFile = new File("VTD.html");
        try {
            FileOutputStream stream = new FileOutputStream(htmlFile, false);
            byte[] myBytes = finalHTML.getBytes();
            stream.write(myBytes);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    //update html file
//    File htmlFile = new File("VTD.html");
//    FileReader json = new FileReader(htmlFile);
//    ObjectMapper mapper = new ObjectMapper();


}
