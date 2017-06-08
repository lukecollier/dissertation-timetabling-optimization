package Attempt2.Data;/*
    Project: Dissertation
    Created by: Joker
    Created date: 15/02/2017
*/

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class TimeSlot
{
    private Random rand;

    private LectureLab lectureLab;
    private Room room;

    // Day of the week
    private DayOfWeek day;

    // Start and end time of the slot
    private LocalTime endTime;
    private LocalTime startTime;

    public TimeSlot() {
        rand = new Random();

        // Default the day to monday
        day = DayOfWeek.MONDAY;
    }

    TimeSlot(TimeSlot timeSlot) {
        rand = new Random();

        // Set member objects
        this.room = new Room(timeSlot.getRoom());
        this.lectureLab = new LectureLab(timeSlot.getLectureLab());

        this.endTime = timeSlot.getEndTime();
        this.startTime = timeSlot.getStartTime();

        this.day = timeSlot.getDay();
    }

    public Room getRoom() {
        return room;
    }

    public LectureLab getLectureLab() {
        return lectureLab;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    /**
     * Set's the day for the time slot
     * @param day the day to have the timeslot on
     */
    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    /**
     * Returns true if there is an overlap
     * @param timeSlot the time slot to check if they overlap
     * @return true if the time slots overlap
     */
    public boolean checkOverlap(TimeSlot timeSlot) {
        return this.getDay() == timeSlot.getDay() &&
                Math.max(this.getStartTime().getHour(), timeSlot.getStartTime().getHour()) <
                        Math.min(this.getEndTime().getHour(), timeSlot.getEndTime().getHour());
    }

    /**
     * Generate the time and date randomly
     */
    public void generateTimeDate() {
        //Set the day to MONDAY
        setDay(DayOfWeek.MONDAY);

        // Calculate a random length for this time slot between 1 and it's contact time
        long dayResult = (long)(Math.random() * 5);
        setDay(getDay().plus(dayResult));

        // Calculate a random start time for the lecture between 8 and 6pm
        long lower = 28800; // 8am
        long upper = 64800 - (3600*(lectureLab.getDuration()-1)); // 6pm minus the length if it's over 1
        long result = (long)(Math.random() * (upper - lower) + lower);
        startTime = LocalTime.MIN.plusSeconds(result);
        startTime = startTime.truncatedTo(ChronoUnit.HOURS);

        endTime = startTime.plusHours(lectureLab.getDuration());
    }

    /**
     * Generate a random time slot
     */
    void generate() {
        // Get the lecture labs available
        int randomLectureLab = rand.nextInt(Constants.possibleLectureLabs.size());
        lectureLab = new LectureLab(Constants.possibleLectureLabs.get(randomLectureLab));
        Constants.possibleLectureLabs.remove(randomLectureLab);

        // Get's the rooms
        Room[] possibleRooms = Constants.findRoomBySeats(Constants.getStudentsInModule(lectureLab.getModule()));
        possibleRooms = Constants.findRoomByType(lectureLab.getRequiredRoom(), possibleRooms);
        room = new Room(possibleRooms[rand.nextInt(possibleRooms.length)]);

        generateTimeDate();
    }

    @Override
    public String toString() {
        return String.format("\t%s[\n\t\tlecture or lab=%s, \n\t\troom=%s,\n\tday=%s, time=%s-%s]",
                this.getClass().getName(), lectureLab, room, getDay().name(),startTime,endTime);
    }
}
