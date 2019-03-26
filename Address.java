package Simulation;

import java.time.LocalTime;
/**
 * 
 * @author schirripad@moravian.edu
 *
 */
public class Address implements Comparable<Address> {

	private int houseNumber, streetNumber;
	private StreetDirection streetDir;
//	private LocalTime time;

	public Address(int houseNumber, int streetNum, StreetDirection street) {
		this.houseNumber = houseNumber;
		this.streetNumber = streetNum;
		this.streetDir = street;
	}
	public Address(int houseNumber, int streetNum) {
		this.houseNumber = houseNumber;
		this.streetNumber = streetNum;
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
			// Otherwise calculate distance
			if (streetDir == sDir) {
				int xDis = Math.abs(hNum - houseNumber);
				int yDis = Math.abs((sNum * 100) - (streetNumber * 100));
				return xDis + yDis;
			} else {
				int xDis = Math.abs(hNum - (streetNumber * 100));
				int yDis = Math.abs((sNum * 100) - houseNumber);
				return xDis + yDis;
			}
		}
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
		int aDis, thisDis;
		aDis = a.distanceFrom(SandwichTruck.distribtutionCenter);
		thisDis = this.distanceFrom(SandwichTruck.distribtutionCenter);
		if (thisDis > aDis) {
			return 1;
		} else if (thisDis < aDis)
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return (streetDir + " " + streetNumber + " " + houseNumber);
	}

}
