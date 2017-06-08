package Attempt2.Data;/*
    Project: Dissertation
    Created by: Joker
    Created date: 15/02/2017
*/

public class Room
{
    public enum RoomType {
        LAB, LECTURE_THEATRE, NONE
    }

    private String name;
    private int seats;
    private RoomType type;
    private Location location;


    Room(String name, int seats, RoomType type, Location location) {
        this.name = name;
        this.seats = seats;
        this.type = type;
        this.location = location;
    }

    Room(Room room) {
        this.name = room.getName();
        this.seats = room.getSeats();
        this.type = room.getType();
        this.location = new Location(room.getLocation());
    }

    /**
     * Gets the amount of seats in the room
     * @return the number of seats in the room
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Gets the name of the room
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     *  Gets the type of the room
     * @return the type to return
     */
    public RoomType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("%s[name=%s, seats=%d, type=%s]",
                this.getClass().getName(), getName(), getSeats(), getType());
    }
}