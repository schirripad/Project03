package Simulation;

public class Instruction {
	private Address address;
	private int timeValue;

	public Instruction(Address goTo, int time) {
		this.address = goTo;
		this.timeValue = time;
	}

	public Address getAddress() {
		return address;
	}

	public int getTime() {
		return timeValue;
	}

}
