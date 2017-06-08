package Attempt2.Data;/*
    Project: Dissertation
    Created by: Joker
    Created date: 18/02/2017
*/

public class Location {
    private String name;
    private double longitude;
    private double latitude;

    /**
     * Basic constructor for a new location
     * @param longitude the longitude
     * @param latitude the latitude
     */
    Location(double longitude, double latitude, String name) {
        this.longitude=longitude;
        this.latitude =latitude;
        this.name=name;
    }

    /**
     * The copy constructor
     * @param location the location to copy into
     */
    Location(Location location) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.name=location.getName();
    }

    /**
     * Get's the latitude of the location
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get's the longitude of the location
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    /**
     * Get's the distance between two points
     * @param location the location to get the distance too
     * @return the distance between two points
     */
    public double getDistance(Location location) {
        double latitude = location.getLatitude()-getLatitude();
        double longitude = location.getLongitude()-getLongitude();
        return Math.abs(Math.sqrt(Math.pow(latitude,2)+Math.pow(longitude,2)));
    }
}
