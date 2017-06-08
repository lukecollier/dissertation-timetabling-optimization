package Attempt2.Data;/*
    Project: Dissertation
    Created by: Joker
    Created date: 15/02/2017
*/

import Attempt2.Algorithm;

public class TimeTable {

    // Student[] students; //Replace with group A/B
    private TimeSlot[] timeSlots;

    public TimeTable() {
        int slotsNumber = Constants.LECTURE_LAB.length;
        timeSlots = new TimeSlot[slotsNumber];
    }

    public TimeTable(TimeTable timeTable) {
        timeSlots = new TimeSlot[timeTable.size()];
        for (int i=0;i<timeTable.size();i++) {
            this.timeSlots[i] = new TimeSlot(timeTable.getTimeSlot(i));
        }
    }

    /**
     * Get's a time slot from a certain position
     * @param pos the position to get the timeslot
     * @return the timeslot to find
     */
    public TimeSlot getTimeSlot(int pos) {
        return timeSlots[pos];
    }

    /**
     * Set's the time slot
     * @param timeSlot the time slot
     * @param pos the position
     */
    public void setTimeSlot(int pos, TimeSlot timeSlot) {
        timeSlots[pos] = new TimeSlot(timeSlot);
    }

    /**
     * This size of the timetable
     * @return the time table size
     */
    public int size() {
        return timeSlots.length;
    }

    /**
     * Find if any of the timeslots overlap in the timetable
     * @return true if the timetables overlap
     */
    public boolean checkOverlaps() {
        for (TimeSlot timeSlotOne : timeSlots) {
            for (TimeSlot timeSlotTwo : timeSlots) {
                if (timeSlotOne.checkOverlap(timeSlotTwo) && !timeSlotOne.equals(timeSlotTwo)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Swap two timetable slots
     * @param posOne the position of the first slot
     * @param posTwo the position of the second slot
     */
    public void swapTimeSlot(int posOne, int posTwo) {
        TimeSlot temp = new TimeSlot(timeSlots[posOne]);
        setTimeSlot(posOne, getTimeSlot(posTwo));
        setTimeSlot(posTwo, temp);
    }

    /**
     * Keep generating the timetable until there are no overlaps
     */
    public void generate() {
        boolean noOverlap = true;
        while (noOverlap) {
            Constants.populateLectureLabs();
            for (int i = 0; i < timeSlots.length; i++) {
                timeSlots[i] = new TimeSlot();
                timeSlots[i].generate();
            }
            noOverlap = checkOverlaps();
        }
    }

    @Override
    public String toString() {
        String temp=this.getClass().getName() + "[\n";
        for (TimeSlot timeSlot : timeSlots) {
            temp += String.format("%s\n", timeSlot.toString());
        }
        return String.format("%s fitness=%.2f]",temp, Algorithm.getFitness(this));
    }
}
