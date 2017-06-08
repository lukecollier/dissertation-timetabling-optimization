package Attempt2.Data;
/*
    Project: Dissertation
    Created by: Joker
    Created date: 15/02/2017
*/

import java.util.ArrayList;
import java.util.Collections;

public class Constants {

    public static ArrayList<LectureLab> possibleLectureLabs;

    public static final Location BUILDING_LOCATIONS[] = {
            new Location(53.229357, -4.124116,"Dean Street"),
    };

    public static final Supervisor SUPERVISORS[] = {
            new Supervisor("Saad Mansoor"),
            new Supervisor("Mr Cameron Gray"),
            new Supervisor("Dr Franck Vidal"),
            new Supervisor("Prof Johnathon Roberts"),
            new Supervisor("Mr Daniel Williams")
    };

    public static final Module MODULES[] = {
            new Module("ICP-3036 Computer Graphics 3 Rendering"),
            new Module("ICP-3038 Computer Vision"),
            new Module("ICP-3029 Networks & Distributed Systems"),
            new Module("IED-3064 Business Process Reengineering"),
            new Module("ICP-3099 Individual Project-Computing")
    };

    public static final LectureLab LECTURE_LAB[] = {
            // ICP 3099
            new LectureLab(SUPERVISORS[0], Room.RoomType.LAB,3, MODULES[4]),
            new LectureLab(SUPERVISORS[0], Room.RoomType.LAB,3, MODULES[4]),

            // ICP 3036
            new LectureLab(SUPERVISORS[3], Room.RoomType.LECTURE_THEATRE,1, MODULES[0]),
            new LectureLab(SUPERVISORS[3], Room.RoomType.LECTURE_THEATRE,2, MODULES[0]),

            // ICP 3038
            new LectureLab(SUPERVISORS[2], Room.RoomType.LAB,2, MODULES[1]),
            new LectureLab(SUPERVISORS[2], Room.RoomType.LECTURE_THEATRE,2, MODULES[1]),

            // ICP 3029
            new LectureLab(SUPERVISORS[0], Room.RoomType.LECTURE_THEATRE,1, MODULES[2]),
            new LectureLab(SUPERVISORS[1], Room.RoomType.LAB,1, MODULES[2]),

            // IED-3064
            new LectureLab(SUPERVISORS[4], Room.RoomType.LECTURE_THEATRE,3, MODULES[3]),

    };

    public static final Room ROOMS[] = {
            new Room("Room 319",52, Room.RoomType.LAB, BUILDING_LOCATIONS[0]),
            new Room("Room 207",50, Room.RoomType.LAB, BUILDING_LOCATIONS[0]),
            new Room("MLT",300, Room.RoomType.LECTURE_THEATRE, BUILDING_LOCATIONS[0]),
            new Room("SLT", 120, Room.RoomType.LECTURE_THEATRE, BUILDING_LOCATIONS[0]),
    };

    public static final Course COURSES[] = {
            new Course(50,"Computer Science",
                    new Module[]{MODULES[0],MODULES[1]}),
    };

    /**
     * Get's all the possible lecture labs and puts them into the array
     */
    public static void populateLectureLabs() {
        possibleLectureLabs = new ArrayList<>();
        Collections.addAll(possibleLectureLabs, Constants.LECTURE_LAB);
    }

    /**
     * Find room by a min size of seats
     * @param minSize the min size for the room
     * @return rooms that match the size
     */
    public static Room[] findRoomBySeats(int minSize) {
        ArrayList<Room> foundRooms = new ArrayList<>();
        for (int i=0;i<ROOMS.length;i++) {
            if (ROOMS[i].getSeats() > minSize) {
                foundRooms.add(ROOMS[i]);
            }
        }
        return foundRooms.toArray(new Room[foundRooms.size()]);
    }

    /**
     * Find the room by the room type
     * @param roomType the type of room
     * @param rooms the rooms to check
     * @return an array of rooms of type
     */
    public static Room[] findRoomByType(Room.RoomType roomType, Room[] rooms) {
        ArrayList<Room> foundRooms = new ArrayList<>();
        for (int i=0;i<rooms.length;i++) {
            if (rooms[i].getType() == roomType) {
                foundRooms.add(rooms[i]);
            }
        }
        return foundRooms.toArray(new Room[foundRooms.size()]);
    }

    /**
     * Helper method to get students in a certain module
     * @param module the module to check the students of
     * @return the number of students on a module
     */
    public static int getStudentsInModule(Module module) {
        int studentCount = 0;
        for (int i=0;i<COURSES.length;i++) {
            for (int j=0;j<COURSES[i].getModules().length;j++) {
                if (COURSES[i].getModules()[j].equals(module)) {
                    studentCount+=COURSES[i].getStudentsNumber();
                }
            }
        }
        return studentCount;
    }
}
