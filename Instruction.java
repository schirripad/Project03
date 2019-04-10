package Simulation;

/**
 * 
 * @author Daniel
 *
 */
public class Instruction {
	private Address address;
	private int timeValue;
	private int truckHeading = -1;

	public Instruction(Address goTo, int time) {
		this.address = goTo;
		this.timeValue = time;
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

}
