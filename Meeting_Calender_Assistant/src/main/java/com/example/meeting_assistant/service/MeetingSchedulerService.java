package com.example.meeting_assistant.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.meeting_assistant.entity.CalenderEvent;
import com.example.meeting_assistant.entity.MeetingRequest;
import com.example.meeting_assistant.entity.TimeInterval;

@Service
public class MeetingSchedulerService {
	
	private Map<String, CalenderEvent> calendars = new HashMap<>();
	
	public ResponseEntity<String> bookMeeting(String ownerId,MeetingRequest meetingRequest) {

        if (calendars.containsKey(ownerId)) {
            CalenderEvent calendar = calendars.get(ownerId);
            calendar.addMeeting(meetingRequest);
        } else {
            CalenderEvent newCalendar = new CalenderEvent();
            newCalendar.addMeeting(meetingRequest);
            calendars.put(ownerId, newCalendar);
        }

        return ResponseEntity.ok("Meeting booked successfully!");
    }
	
	public ResponseEntity<List<TimeInterval>> findFreeSlots(String employee1,
            String employee2,int durationMinutes) {
		
		if (!calendars.containsKey(employee1) || !calendars.containsKey(employee2))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        CalenderEvent calendar1 = calendars.get(employee1);
        CalenderEvent calendar2 = calendars.get(employee2);

        List<TimeInterval> freeSlots = calendar1.findFreeSlots(calendar2, durationMinutes);
        return ResponseEntity.ok(freeSlots);
	}
	
	public ResponseEntity<List<String>> findMeetingConflicts( MeetingRequest meetingRequest) {

        List<String> conflicts = new ArrayList<>();

        for (Map.Entry<String, CalenderEvent> entry : calendars.entrySet()) {
        	CalenderEvent calendar = entry.getValue();
            if (calendar.hasMeetingConflict(meetingRequest))
                conflicts.add(entry.getKey());
        }

        return ResponseEntity.ok(conflicts);
    }
}
