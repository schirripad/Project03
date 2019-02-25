package Simulation;

import java.util.PriorityQueue;

public class SandwichTruck {

	public Address curAddress;
	public PriorityQueue<Address> targets = new PriorityQueue<Address>();

	public SandwichTruck(int east, int south) {
		curAddress = new Address(east, south);
	}

	public Address getCurrentAddress() {
		return curAddress;
	}

}
