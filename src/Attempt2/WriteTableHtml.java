package Attempt2;/*
    Project: Dissertation
    Created by: Joker
    Created date: 18/04/2017
*/

import Attempt2.Data.TimeSlot;
import Attempt2.Data.TimeTable;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

public class WriteTableHtml {
    /**
     * Writes to a html file
     * @param fileName the filename to save as
     */
    public static void writeFile(String fileName, TimeTable timeTable) {
        LinkedList<String> lines = new LinkedList<>();
        lines.add("<!doctype html><html><head><title>Timetable</title><link rel=\"stylesheet\" type=\"text/css\" href=\"base.css\"></head><body><table><div class=\"head_nav\"><tr><th><p>Time</p></th><th><p>Monday</p></th><th><p>Tuesday</p></th><th><p>Wednesday</p></th><th><p>Thrusday</p></th><th><p>Friday</p></th></tr></div>");

        lines.add(getLines(timeTable));

        lines.add("</table>");

        Path file = Paths.get(fileName + ".html");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLines(TimeTable timeTable) {
        // List of points to ignore on the timetable
        ArrayList<int[]> missPoints = new ArrayList<>();
        String line = "";
        for (int i=0; i<10;i++) {
            line = line+"<tr>";
            int startTime = (i+8), endTime = (i+9);
            line = String.format("%s <th><p>%d:00 - %d:00</p></th>",line,startTime,endTime);
            // Run through every day and check if there should be a new cell
            for (int j=1; j<6; j++) {
                if (!checkPoints(j,i,missPoints)) {
                    line = line + "<td";
                    for (int k = 0; k < timeTable.size(); k++) {
                        int timeSlotStart = timeTable.getTimeSlot(k).getStartTime().getHour();
                        int timeSlotDay = timeTable.getTimeSlot(k).getDay().getValue();
                        if (timeSlotStart == startTime && j == timeSlotDay) {
                            int duration = timeTable.getTimeSlot(k).getLectureLab().getDuration();
                            line = line + " rowspan=\"" + duration + "\">";
                            line = line + getTimeSlot(timeTable.getTimeSlot(k));
                            for (int z = 1; z < duration; z++)
                                missPoints.add(new int[]{j, i + z});
                        }
                    }
                    line = line + "></td>\n";
                }
            }
            line = line+"</tr>";
        }

        return line;
    }

    public static boolean checkPoints(int x, int y, ArrayList<int[]> list) {
        for (int[] point : list) {
            if (point[0] == x && point[1] == y ) {
                return true;
            }
        }
        return false;
    }

    public static String getTimeSlot(TimeSlot timeSlot) {
        // Get the information from the timeSlot
        String moduleName = timeSlot.getLectureLab().getModule().getName();
        String type = timeSlot.getLectureLab().getRequiredRoom().name();
        String roomName = timeSlot.getRoom().getName();
        String supervisorName = timeSlot.getLectureLab().getSupervisor().getFullname();

        String line =
                "<div class=\"time-slot-holder\">\n" +
                "      <p>" + moduleName + "</p>\n" +
                "      <p>" + type + "</p>\n" +
                "      <p>" + roomName + "</p>\n" +
                "      <p>" + supervisorName + "</p>\n" +
                "    </div";

        return line;
    }
}
