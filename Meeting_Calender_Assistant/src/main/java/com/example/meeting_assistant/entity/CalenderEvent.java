package com.example.meeting_assistant.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CalenderEvent {

    private List<MeetingRequest> meetings;

    public CalenderEvent() {
        this.meetings = new ArrayList<>();
    }

    public void addMeeting(MeetingRequest meetingRequest) {
        meetings.add(meetingRequest);
    }

    public List<TimeInterval> findFreeSlots(CalenderEvent otherCalendar, int durationMinutes) {
        List<TimeInterval> freeSlots = new ArrayList<>();
        List<MeetingRequest> mergedMeetings = new ArrayList<>(meetings);
        mergedMeetings.addAll(otherCalendar.getMeetings());
        mergedMeetings.sort(new MeetingComparator());

        LocalDateTime startTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        for (MeetingRequest meeting : mergedMeetings) {
            LocalDateTime endTime = meeting.getStartTime();
            if (Duration.between(startTime, endTime).toMinutes() >= durationMinutes) {
                freeSlots.add(new TimeInterval(startTime, endTime));
            }
            startTime = meeting.getEndTime();
        }

        LocalDateTime endTime = LocalDateTime.now().plusYears(1);
        if (Duration.between(startTime, endTime).toMinutes() >= durationMinutes) {
            freeSlots.add(new TimeInterval(startTime, endTime));
        }

        return freeSlots;
    }

    public boolean hasMeetingConflict(MeetingRequest meetingRequest) {
        for (MeetingRequest meeting : meetings) {
            if (meeting.hasConflict(meetingRequest))
                return true;
        }
        return false;
    }
}
