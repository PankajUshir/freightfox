package com.example.meeting_assistant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.meeting_assistant.entity.MeetingRequest;
import com.example.meeting_assistant.entity.TimeInterval;
import com.example.meeting_assistant.service.MeetingSchedulerService;

@RestController
public class MeetingSchedulerController {

	@Autowired
	private MeetingSchedulerService meetingSchedulerService;
	
    // http://localhost:8080/book-meeting/{ownerId}
    @PostMapping("/book-meeting/{ownerId}")
    public ResponseEntity<String> bookMeeting(
            @PathVariable("ownerId") String ownerId,
            @RequestBody MeetingRequest meetingRequest) {

        return meetingSchedulerService.bookMeeting(ownerId, meetingRequest);
    }

    // http://localhost:8080/find-free-slots?employee1={employee1Id}&employee2={employee2Id}&duration=30
    @GetMapping("/find-free-slots")
    public ResponseEntity<List<TimeInterval>> findFreeSlots(
            @RequestParam("employee1") String employee1,
            @RequestParam("employee2") String employee2,
            @RequestParam("duration") int durationMinutes) {

        return meetingSchedulerService.findFreeSlots(employee1, employee2, durationMinutes);
    }

    // http://localhost:8080/find-meeting-conflicts
    @GetMapping("/find-meeting-conflicts")
    public ResponseEntity<List<String>> findMeetingConflicts(
            @RequestBody MeetingRequest meetingRequest) {

        return meetingSchedulerService.findMeetingConflicts(meetingRequest);
    }
}
