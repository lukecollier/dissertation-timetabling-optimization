package Attempt2.Data;/*
    Project: Dissertation
    Created by: Joker
    Created date: 20/02/2017
*/

import java.time.DayOfWeek;
import java.util.ArrayList;

public class Helper {
    /**
     * Get all time slots that are within a certain amount of time from each other
     * @return an array of time slots that were found
     */
    public static int getCloseTimeSlots(TimeTable timeTable) {
        int timeSlotNumber = 0;
        for (int i=0; i<timeTable.size();i++) {
            for (int j=0; j<timeTable.size();j++) {
                TimeSlot timeSlotOne = timeTable.getTimeSlot(i);
                TimeSlot timeSlotTwo = timeTable.getTimeSlot(j);
                if ((timeSlotOne.getEndTime() == timeSlotTwo.getStartTime()
                        || timeSlotOne.getStartTime() == timeSlotTwo.getEndTime())
                        && timeSlotOne.getDay() == timeSlotTwo.getDay()) {
                    timeSlotNumber++;
                }
            }
        }
        return timeSlotNumber;
    }

    /**
     * Sorts the table by time
     * @param timeTable sort the timetable by time
     */
    public static void sortTimeTable(TimeTable timeTable) {
        for (int i=0;i<timeTable.size();i++) {
            for (int j=0;j<timeTable.size();j++) {
                if (timeTable.getTimeSlot(i).getDay().getValue()
                        < timeTable.getTimeSlot(j).getDay().getValue()) {
                    timeTable.swapTimeSlot(i,j);
                }
                else if(timeTable.getTimeSlot(i).getDay() == timeTable.getTimeSlot(j).getDay()
                        && timeTable.getTimeSlot(i).getStartTime().isBefore(timeTable.getTimeSlot(j).getStartTime())) {
                    timeTable.swapTimeSlot(i,j);
                }
            }
        }
    }

    /**
     * Get's the unused seats
     * @param timeTable the timetable to check
     * @return the unused seats
     */
    public static int getUnusedSeats(TimeTable timeTable) {
        int spareSeats = 0;
        for (int i=0;i<timeTable.size();i++) {
            spareSeats += timeTable.getTimeSlot(i).getRoom().getSeats()
                    - Constants.getStudentsInModule(timeTable.getTimeSlot(i).getLectureLab().getModule());
        }
        return spareSeats;
    }

    /**
     * Gets the day distance
     * @param timeTable the timetable to check for the fitness of
     * @return the fitness based on time distance of lecture labs
     */
    public static int getDayTimeDistance(TimeTable timeTable) {
        TimeTable sortedTimeTable = new TimeTable(timeTable);
        Helper.sortTimeTable(sortedTimeTable);

        int distance=0;
        for (int i=0;i<timeTable.size()-1;i++) {
            int j = i+1;
            if (timeTable.getTimeSlot(i).getDay() == timeTable.getTimeSlot(j).getDay()) {
                // Get the add distance
                int addDistance = (timeTable.getTimeSlot(i).getEndTime().getHour()
                        - timeTable.getTimeSlot(j).getStartTime().getHour());
                // Get the absolute distance and add it to the distance
                distance += Math.abs(addDistance);
            }
        }
        return distance;
    }

    /**
     * Get's the total distance required from each lecture theatre
     * @param timeTable the timetable to find the total walking distance in
     * @return the total walking distance to complete the timetable
     */
    public static double getWalkDistance(TimeTable timeTable) {
        double distance=0;
        for (int i=0;i<timeTable.size()-1;i++) {
            int j = i+1;
            distance += timeTable.getTimeSlot(i).getRoom().getLocation()
                            .getDistance(timeTable.getTimeSlot(j).getRoom().getLocation());
        }
        return distance;
    }

    /**
     * Get's how spread out over the week the timetable is
     * @param timeTable the timetable
     * @return a value to represent how many days have
     */
    public static int getWeekCoverage(TimeTable timeTable) {
        ArrayList<DayOfWeek> daysCovered = new ArrayList<>();
        for (int i=0;i<timeTable.size();i++) {
            daysCovered.add(timeTable.getTimeSlot(i).getDay());
        }
        return daysCovered.size();
    }
}
