package Simulation;

public class Address implements Comparable<Address> {

	public int east;
	public int south;

	public Address(int east, int south) {
		this.east = east;
		this.south = south;
	}

	public int distanceFrom(Address a) {
		return -1;
	}

	public int getEast() {
		return east;
	}

	public int getSouth() {
		return south;
	}

	@Override
	public int compareTo(Address a) {
		return 0;
	}

}
