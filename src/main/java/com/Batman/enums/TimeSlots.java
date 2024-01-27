package com.Batman.enums;

import java.time.LocalTime;

public enum TimeSlots {
	Morning(LocalTime.of(9,0), LocalTime.of(11, 0)), Afternoon(LocalTime.of(15, 0), LocalTime.of(17, 30)),
	Evening(LocalTime.of(19, 0), LocalTime.of(21, 0));

	private LocalTime startTime;
	private LocalTime endTime;

	TimeSlots(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}
}
