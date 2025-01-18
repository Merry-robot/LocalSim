package org.merry.simulation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.io.InputStream;

public class Aircraft {
    private static String selectedCallsign;
    private String type;
    private double latit;
    public double longit;
    private double altitude;
    private double speed;
    private String status;

    public void setType(String type) {
        this.type = type; // ICAO format
    }

    public void setLatit(double latit) {
        this.latit = latit;
    }

    public void setLongit(double longit) {
        this.longit = longit;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude; // in feet
    }

    public void setSpeed(double speed) {
        this.speed = speed; // in knots
    }

    public void setStatus(String status) {
        this.status = status; // taxiing, parked, cruise, departing, landing
    }

    public String getType() {
        return type;
    }

    public double getLatit() {
        return latit;
    }

    public double getLongit() {
        return longit;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public String getStatus() {
        return status;
    }

    public static String generatedCallsign() {
        try {
            // Load callsigns from JSON file in classpath
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Aircraft.class.getClassLoader().getResourceAsStream("callsigns.json");

            if (inputStream == null) {
                throw new IOException("callsigns.json not found in classpath");
            }

            CallsignsData data = objectMapper.readValue(inputStream, CallsignsData.class);

            // Pick a random callsign
            List<String> callsigns = data.getCallsigns();
            Random random = new Random();
            selectedCallsign = callsigns.get(random.nextInt(callsigns.size()));
        } catch (IOException e) {
            System.err.println("Error loading callsigns: " + e.getMessage());
        }
        return selectedCallsign;
    }


    public String getCallsign() {
        return selectedCallsign;
    }
}
