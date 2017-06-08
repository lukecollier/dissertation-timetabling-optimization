package Attempt2.Data;
/*
    Project: Dissertation
    Created by: Joker
    Created date: 17/02/2017
*/

public class LectureLab {
    private Supervisor supervisor;
    private Room.RoomType requiredRoom;
    private int duration;

    private Module module;

    LectureLab(Supervisor supervisor, Room.RoomType requiredRoom, int classLength, Module module) {
        this.supervisor = supervisor;
        this.requiredRoom = requiredRoom;
        this.duration = classLength;
        this.module = module;
    }

    LectureLab(LectureLab lectureLab) {
        this.supervisor = new Supervisor(lectureLab.supervisor);
        this.requiredRoom = lectureLab.getRequiredRoom();
        this.duration = lectureLab.getDuration();
        this.module = new Module(lectureLab.module);
    }

    public Room.RoomType getRequiredRoom() {
        return requiredRoom;
    }

    public int getDuration() {
        return duration;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public Module getModule() {
        return module;
    }

    public boolean equals(LectureLab lectureLab) {
        return (getSupervisor().equals(lectureLab.getSupervisor())
                && getRequiredRoom() == lectureLab.getRequiredRoom()
                && getDuration() == lectureLab.getDuration());
    }

    @Override
    public String toString() {
        return String.format("%s[supervisor=%s, required room=%s, duration=%s]",
                this.getClass().getName(),supervisor,requiredRoom,duration);
    }
}
