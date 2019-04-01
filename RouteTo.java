package Simulation;

import javax.swing.Timer;
import java.util.*;
/**
 *
 * Contains data representing instructions of routing between two addresses, including the total route length
 *
 * @author Rio
 *
 */

public class RouteTo {

	public static List<Instruction> instructions ;
	private static double routeLength = 0;
	private static int truckHeading = 0;  // 1; (←)South, 2; East(↓), 3; North(→), 4; West(↑)
	//SandwichTruck truck ;
	Address truck;
	Address a ;
	Address prevAddr;
	private int previousHNum ;



	RouteTo(Order o  , SandwichTruck t){
		truck = t.getCurrentAddress();
		//####################
		//
		a = o.getAddress();
		//handleUturn();
		int hNum;
		int sNum;
		StreetDirection sDir;
		int truckHouseNum;
		int truckStreetNum;
		StreetDirection truckDir;
		instructions = new LinkedList<>();
		boolean sameDirection;
		boolean sameStreetNum;

		while (!(a.getStreetDirection().equals(truck.getStreetDirection()))|| (a.getStreetNumber()!=truck.getStreetNumber())){

			hNum = a.getHouseNumber();
			sNum = a.getStreetNumber();
			sDir = a.getStreetDirection();
			truckHouseNum = truck.getHouseNumber();
			truckStreetNum = truck.getStreetNumber();
			truckDir = truck.getStreetDirection();


			sameDirection = sDir == truckDir; // Same direction streets
			sameStreetNum = sNum == truckStreetNum;



		// more that two moves; eg: *---()<- corner, so now it is in 2 moves case
		//                              |
		//                              |
		//                               ------*
			// else if
		 	if(sameDirection) {
		 		int corner;
				if (truckDir == StreetDirection.SOUTH && sDir == StreetDirection.SOUTH ) {
					corner = truckHouseNum - (truckHouseNum % 100);
					instructions.add(new Instruction(new Address(corner, truckStreetNum, StreetDirection.EAST),2000)); //Not sure if we necessarily have to add the streetDirection here
					routeLength += Math.abs(corner - truckHouseNum);
					// truck =(new SandwichTruck(corner, truckStreetNum, StreetDirection.EAST));
					truck=(new Address(corner, truckStreetNum, StreetDirection.EAST));//
					continue;
				}
				else if (truckDir == StreetDirection.EAST&& sDir == StreetDirection.EAST) {
				corner =truckHouseNum - (truckHouseNum  % 100);
				instructions.add(new Instruction(new Address(corner, sNum, StreetDirection.SOUTH),2000)); //Not sure if we necessarily have to add the streetDirection here
				truckHeading = (truck.getHouseNumber() < a.getHouseNumber()) ? 2:4;

				routeLength += Math.abs(corner - hNum);
				truck=(new Address(corner, sNum, StreetDirection.SOUTH));
				continue;
				}
		 	}

			// In two moves eg: *---() <- new address
			//                      |
			//                      |
			//                      *
			else {
		 		int y1,x2;
		 		Address tem;
				if (truckDir== StreetDirection.SOUTH){
					x2 = sNum;
					y1 = truckStreetNum;
//					instructions.add(new Address(x1,y2));
					instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.EAST),2000));
					routeLength += Math.abs( x2*100 - truckHouseNum);
					previousHNum = y1*100;
					truck=(new Address(y1*100,x2,StreetDirection.EAST));
					//break;
				}
				else if (truckDir == StreetDirection.EAST){
//					instructions.add(new Address(x2,y1));
					x2 = sNum;
					y1 = truckStreetNum;
					instructions.add( new Instruction(new Address(y1*100,x2,StreetDirection.SOUTH),2000));
					routeLength += Math.abs(x2*100 - truckHouseNum);
					previousHNum = y1*100;
					truck=(new Address(y1*100,x2,StreetDirection.SOUTH));

//					instructions.add(new Address(x2*100,y1,StreetDirection.SOUTH));
//					routeLength += Math.abs(x2*100 - truckHouseNum);
					//break;
				}
		 	}
		}//close while
		// 1; (←)South, 2; East(↓), 3; North(→), 4; West(↑)
		if (truck.getStreetDirection()==StreetDirection.EAST){
			truckHeading = (truck.getHouseNumber() < a.getHouseNumber()) ? 2:4;}
		else if (truck.getStreetDirection()==StreetDirection.SOUTH){
			truckHeading = (truck.getHouseNumber() > a.getHouseNumber()) ? 1:3;}

		routeLength += Math.abs(truck.getHouseNumber() - a.getHouseNumber());
		instructions.add(new Instruction(a,1000));
		prevAddr=a;
		//t.setAddress(a);

	}

	public void handleUturn(){
		if (truckHeading%2==1){
			if (truckHeading==1 && prevAddr.getStreetDirection()==a.getStreetDirection() && prevAddr.getStreetNumber()==a.getStreetNumber()&& prevAddr.getHouseNumber()< a.getHouseNumber()){
				prevAddr= new Address(a.getHouseNumber()+100 - (a.getHouseNumber()  % 100),a.getStreetNumber(),a.getStreetDirection());
				//instructions.add(prevAddr);

			}

		}
//
//		else if (){}
//
//		else {}

	}

	public double getRouteLength() {
		return routeLength;
	}

	public List<Instruction> getRoute() {
		return instructions;
	}

	public void removeFirstInstruction() {
		if (instructions.size() != 1)
			instructions.remove(0);
	}
}

