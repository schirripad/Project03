package Simulation;

import java.time.LocalTime;

/**
 * Contains information concerning the proper execution of a route, including
 * turns and times
 * 
 * @author Daniel
 *
 */
public class Instruction {
	private Address address;
	private int timeValue;
	private int truckHeading = -1;
	private LocalTime time;

	public Instruction(Address goTo, int time) {
		this.address = goTo;
		this.timeValue = time;
	}

	public Instruction(Address goTo, LocalTime time) {
		this.address = goTo;
		this.time = time;
	}

	public Instruction(Address goTo) {
		this.address = goTo;
		this.timeValue = 0;
	}

	public Instruction(Address goTo, int time, int truckHeading) {
		this.address = goTo;
		this.timeValue = time;
		this.truckHeading = truckHeading;
	}

	public Address getAddress() {
		return address;
	}

	public int getTime() {
		return timeValue;
	}
	
	public LocalTime getLocalTime() {
		return time;
	}

}
