package Simulation;

import java.util.PriorityQueue;

public class SandwichTruck {
	private static final Address distribtutionCenter = new Address(910, 9, StreetDirection.SOUTH);

	public Address curAddress;
	public PriorityQueue<Address> targets = new PriorityQueue<Address>();

	public SandwichTruck(int addressNum, int streetNum, StreetDirection streetDir) {
		curAddress = new Address(addressNum, streetNum, streetDir);
	}

	public Address getCurrentAddress() {
		return curAddress;
	}

}
