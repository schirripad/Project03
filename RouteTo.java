package Simulation;

import java.util.*;

public class RouteTo {

	public static List<Address> instructions ;
	private static double routeLength = 0;
	SandwichTruck truck ;



	RouteTo(Order o  , SandwichTruck t){
		truck = t;
		Address a = o.getAddress();
		int hNum;
		int sNum;
		StreetDirection sDir;
		int truckHouseNum;
		int truckStreetNum;
		StreetDirection truckDir;
		instructions = new LinkedList<>();
		boolean sameDirection;
		boolean sameStreetNum;

		while (!(a.getStreetDirection().equals(truck.getCurrentAddress().getStreetDirection()))|| (a.getStreetNumber()!=truck.getCurrentAddress().getStreetNumber())){

			hNum = a.getHouseNumber();
			sNum = a.getStreetNumber();
			sDir = a.getStreetDirection();
			truckHouseNum = truck.getCurrentAddress().getHouseNumber();
			truckStreetNum = truck.getCurrentAddress().getStreetNumber();
			truckDir = truck.getCurrentAddress().getStreetDirection();


			sameDirection = sDir == truckDir; // Same direction streets
			sameStreetNum = sNum == truckStreetNum;



		// more that two moves; eg: *---()<- corner, so now it is in 2 moves case
		//                              |
		//                              |
		//                               ------*
			//else if
		 if(sameDirection) {
			int corner;
			if (truckDir == StreetDirection.SOUTH && sDir == StreetDirection.SOUTH ) {
				corner = truckHouseNum - (truckHouseNum  % 100);
				instructions.add(new Address(corner, truckStreetNum, StreetDirection.EAST)); //Not sure if we necessarily have to add the streetDirection here
				routeLength += Math.abs(corner - truckHouseNum);
				// truck =(new SandwichTruck(corner, truckStreetNum, StreetDirection.EAST));
				truck.setAddress(new Address(corner, truckStreetNum, StreetDirection.EAST));
				continue;

			} else if (truckDir == StreetDirection.EAST&& sDir == StreetDirection.EAST) {
				corner =truckHouseNum - (truckHouseNum  % 100);
				instructions.add(new Address(corner, sNum, StreetDirection.SOUTH));//Not sure if we necessarily have to add the streetDirection here
				routeLength += Math.abs(corner - hNum);
				truck.setAddress(new Address(corner, sNum, StreetDirection.SOUTH));
				continue;

			}

		}

		// In two moves eg: *---() <- new address
		//                      |
		//                      |
		//                      *
		else {
			int y1,x2;
			if (truckDir== StreetDirection.SOUTH){
				x2 = sNum;
				y1 = truckStreetNum;

//				instructions.add(new Address(x1,y2));
				instructions.add(new Address(y1*100,x2,StreetDirection.EAST));
				routeLength += Math.abs( x2*100- truckHouseNum);
				break;
			}
			else if (truckDir == StreetDirection.EAST){
//				instructions.add(new Address(x2,y1));
				x2 = sNum;
				y1 = truckStreetNum;
				instructions.add(new Address(y1*100,x2,StreetDirection.SOUTH));
				routeLength += Math.abs(x2*100 - truckHouseNum);
//				instructions.add(new Address(x2*100,y1,StreetDirection.SOUTH));
//				routeLength += Math.abs(x2*100 - truckHouseNum);
				break;
			}

				} //close while

		}
		instructions.add(a);
		truck.setAddress(a);

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
}


