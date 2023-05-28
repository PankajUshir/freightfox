package com.example.meeting_assistant.entity;

import java.util.Comparator;

public class MeetingComparator implements Comparator<MeetingRequest> {
	
	@Override
    public int compare(MeetingRequest m1, MeetingRequest m2) {
        return m1.getStartTime().compareTo(m2.getStartTime());
    }
}
