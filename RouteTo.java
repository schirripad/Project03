package Simulation;

import java.util.LinkedList;
import java.util.List;

public class RouteTo {

	public static List<Address> instructions;
	private static double routeLength = 0;
	SandwichTruck truck = new SandwichTruck(910, 9, StreetDirection.SOUTH);

	public RouteTo(Address a) {
		int hNum = a.getHouseNumber();
		int sNum = a.getStreetNumber();
		StreetDirection sDir = a.getStreetDirection();

		instructions = new LinkedList<>();
		instructions.add(truck.getCurrentAddress());

		boolean sameDirection = sDir == truck.getCurrentAddress().getStreetDirection(); // Same direction streets
		boolean sameStreetNum = sNum == truck.getCurrentAddress().getStreetNumber();

		// while (truck.getCurrentAddress() != a) {

		// basic case
		if (sameDirection && sameStreetNum) {
			// Address tem = new Address(hNum-truck.targets[1].getHouseNumber(),sNum,sDir);
			instructions.add(a);
			routeLength += Math.abs(hNum - truck.getCurrentAddress().getHouseNumber());
		}
		// more that two moves
		else if (sameDirection && !sameStreetNum) {
			if (sameDirection && sNum > truck.getCurrentAddress().getStreetNumber()) {

			}

			else if (true) {

			}
		}
		// In two moves
		else {
			if (truck.getCurrentAddress().getStreetDirection() == StreetDirection.SOUTH) {
				int x1 = sNum;
				int y2 = truck.getCurrentAddress().getStreetNumber();
				instructions.add(new Address(x1, y2));
				routeLength += Math.abs(x1 - truck.getCurrentAddress().getHouseNumber());
			} else if (truck.getCurrentAddress().getStreetDirection() == StreetDirection.EAST) {
				int y1 = sNum;
				int x2 = truck.getCurrentAddress().getStreetNumber();
				instructions.add(new Address(x2, y1));
				routeLength += Math.abs(y1 - truck.getCurrentAddress().getHouseNumber());
			}
			// } close while

		}
	}

	public double getRouteLength() {
		return routeLength;
	}

	public List<Address> getRoute() {
		return instructions;
	}

	public void removeFirstInstruction() {
		if (instructions.size() != 1)
			instructions.remove(0);
	}

	// public void addAddress(Address ad) {
	// }

}
