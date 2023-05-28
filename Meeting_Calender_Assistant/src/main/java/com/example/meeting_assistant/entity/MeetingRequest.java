package com.example.meeting_assistant.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MeetingRequest {

	private String organizer;
    private List<String> participants;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    

    public boolean hasConflict(MeetingRequest otherMeeting) {
        return startTime.isBefore(otherMeeting.endTime) && otherMeeting.startTime.isBefore(endTime);
    }
}
