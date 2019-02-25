package Simulation;

public class Address implements Comparable<Address> {

	private int houseNumber, streetNumber;
	private StreetDirection streetDir;

	public Address(int houseNumber, int streetNum, StreetDirection street) {
		this.houseNumber = houseNumber;
		this.streetNumber = streetNum;
		this.streetDir = street;
	}

	public int distanceFrom(Address a) {
		// Create address dictionary
		int hNum = a.getHouseNumber();
		int sNum = a.getStreetNumber();
		StreetDirection sDir = a.getStreetDirection();
		// Compute distance
		// If houses are on same street, simply subtract housenumbers
		if (sDir == streetDir && sNum == streetNumber) {
			return Math.abs(hNum - houseNumber);
		} else {
			// Compute distance
		}
		return -1;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public StreetDirection getStreetDirection() {
		return streetDir;
	}

	@Override
	public int compareTo(Address a) {
		return 0;
	}

}
