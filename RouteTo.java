package Simulation;

import java.util.*;
/**
 *
 * Contains data representing instructions of routing between two addresses(truck current location and the address of the next order)
 * with the delaying units of right-hand and left-hand turns, besides the total route length.
 *
 * @author Rio
 *
 */

public class RouteTo implements Router{

	public List<Instruction> instructions ;
	private static double routeLength = 0;
	private static int truckHeading = 0;  // 1; (←)South, 2; East(↓), 3; North(→), 4; West(↑)
	Address truck;
	Address a ;
	//Address previous;



	RouteTo(Order o  , SandwichTruck t){
		truck = t.getCurrentAddress();
		a = o.getAddress();
		// a = new Address(1270,5,StreetDirection.SOUTH);

		// set the truckHeading
		if (truck == SandwichTruck.distribtutionCenter )
			truckHeading =2;


		int hNum;
		int sNum;
		StreetDirection sDir;
		int truckHouseNum;
		int truckStreetNum;
		StreetDirection truckDir;
		instructions = new LinkedList<>();
		boolean sameDirection;
		boolean sameStreetNum = a.getStreetNumber() == truck.getStreetNumber();


		// Deal with the U-turn case in same street situation
		if (a.getStreetDirection() == truck.getStreetDirection() && sameStreetNum ){
			int corner = truck.getHouseNumber()- (truck.getHouseNumber() % 100);

			// Good!
			if ((truck.getStreetDirection() == StreetDirection.SOUTH && truckHeading == 1 && a.getHouseNumber() > truck.getHouseNumber()) ) {
				instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.EAST),2000));
				instructions.add(new Instruction(new Address(corner,truck.getStreetNumber()-1,StreetDirection.SOUTH),2000));

				truck= new Address(corner,truck.getStreetNumber()-1,StreetDirection.SOUTH);
				truckHeading = 3;
			}
			// Good!
			else if ((truck.getStreetDirection() == StreetDirection.SOUTH && truckHeading == 3 && a.getHouseNumber() < truck.getHouseNumber())){
				instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,(corner+100)/100,StreetDirection.EAST),2000));
				instructions.add(new Instruction(new Address(corner+100,truck.getStreetNumber()+1,StreetDirection.SOUTH),2000));
				truckHeading = 1;
				truck= new Address(corner+100,truck.getStreetNumber()+1,StreetDirection.SOUTH);
			}
			// Good!
			else if ((truck.getStreetDirection() == StreetDirection.EAST && truckHeading == 2 && a.getHouseNumber() < truck.getHouseNumber())){
				instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,(corner+100)/100,StreetDirection.SOUTH),2000));
				instructions.add(new Instruction(new Address(corner+100,truck.getStreetNumber()-1,StreetDirection.EAST),2000));
				truckHeading = 4;
				truck= new Address(corner+100,truck.getStreetNumber()-1,StreetDirection.EAST);

			}
			// Good!
			else if ((truck.getStreetDirection() == StreetDirection.EAST && truckHeading == 4 && a.getHouseNumber() > truck.getHouseNumber())){
				instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.SOUTH),2000));
				instructions.add(new Instruction(new Address(corner,truck.getStreetNumber()+1,StreetDirection.EAST),2000));
				truckHeading = 2;
				truck= new Address(corner,truck.getStreetNumber()+1,StreetDirection.EAST);
			}
		}


		if (truck.getStreetDirection()!= a.getStreetDirection() && truck.getStreetDirection()==StreetDirection.EAST){
			// Good!
			if(truckHeading ==4 && truck.getHouseNumber() - (truck.getHouseNumber() % 100) < a.getStreetNumber()*100){

				int corner= truck.getHouseNumber() - (truck.getHouseNumber() % 100);
				instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.SOUTH),2000));
				truckHeading=3;
				truck =new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.SOUTH);
			}
			// Good!
			else if(truckHeading == 2 && truck.getHouseNumber() - ((truck.getHouseNumber()+100) % 100) > a.getStreetNumber()*100){

				int corner= (truck.getHouseNumber()) - (truck.getHouseNumber() % 100);
				instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,(corner+100)/100,StreetDirection.SOUTH),4000));
				truckHeading=3;
				truck =new Address(truck.getStreetNumber()*100,(corner+100)/100,StreetDirection.SOUTH);
			}
		}
		// Good!
		if (truck.getStreetDirection()!= a.getStreetDirection() && truck.getStreetDirection()==StreetDirection.SOUTH){
			if(truckHeading ==3 && truck.getHouseNumber() - (truck.getHouseNumber() % 100) > a.getStreetNumber()*100){

				int corner= (truck.getHouseNumber()) - (truck.getHouseNumber() % 100);
				instructions.add(new Instruction(new Address((truck.getStreetNumber()*100),(corner+100)/100,StreetDirection.EAST),2000));
				truckHeading=2;
				truck =new Address((truck.getStreetNumber()*100),(corner+100)/100,StreetDirection.EAST);
			}
			else if(truckHeading == 1 && truck.getHouseNumber() - (truck.getHouseNumber() % 100) < a.getStreetNumber()*100){

				int corner= (truck.getHouseNumber()) - (truck.getHouseNumber() % 100);
				instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.EAST),4000));
				truckHeading=2;
				truck =new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.EAST);
			}
		}


		while (!(a.getStreetDirection().equals(truck.getStreetDirection()))|| (a.getStreetNumber()!=truck.getStreetNumber())){

			hNum = a.getHouseNumber();
			sNum = a.getStreetNumber();
			sDir = a.getStreetDirection();
			truckHouseNum = truck.getHouseNumber();
			truckStreetNum = truck.getStreetNumber();
			truckDir = truck.getStreetDirection();


			sameDirection = sDir == truckDir; // Same direction streets


		 	if(sameDirection) {
				// more that two moves; eg:            *---
				//                                         |
				//                                         |
				// corner, so now it is in 2 moves case -> ()------*
		 		int corner;
				if (truckDir == StreetDirection.SOUTH ) {
					// if Address in the right side
					if (hNum > truckHouseNum) {
						if (truckHeading == 1) { //(←)South

							corner = truckHouseNum - (truckHouseNum % 100);
							if (sNum<truckStreetNum){
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),2000));
								truckHeading = 4;
							}
							else {
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),4000));
								truckHeading = 2;
							}
							routeLength += Math.abs(corner - truckHouseNum);
							truck =(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST));
						}
						else if (truckHeading == 3) {// North(→)

							corner =hNum - (hNum  % 100);
							if (sNum < truckStreetNum) {
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),4000));
								truckHeading=4;
							}
							else {
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),2000));
								truckHeading=2;
							}
							routeLength += Math.abs(truckHouseNum -( hNum-(hNum  % 100)));
							truck=(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST));
						}

					}
					// if Address in the left side
					else {
						if (truckHeading==1){// (←)South

							corner =hNum - (hNum  % 100);
							if (sNum<truckStreetNum){
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),2000));
								truckHeading =4;
							}
							else {
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),4000));
								truckHeading =2;
							}
							//review route length
							routeLength += Math.abs(corner - truckHouseNum);
							truck=(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST));
						}
						else if (truckHeading==3) {//North(→)
							corner =(truckHouseNum) - (truckHouseNum  % 100);
							if (sNum<truckStreetNum) {
								instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.EAST), 4000));
								truckHeading=4;
							}
							else {
								instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.EAST), 2000));
								truckHeading=2;

							}
							//review
							routeLength += Math.abs(corner - truckHouseNum);
							truck=(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.EAST));

						}

					}

				}
				// Truck On East; eg:                *
				//                                   |
				//                                   |
				//                                   ()------
				//                                           |
				//                                           *
				else if (truckDir == StreetDirection.EAST) {
					//  down side
					if (hNum>truckHouseNum){
						if(truckHeading==4){// West(↑)
							///
							corner =truckHouseNum - (truckHouseNum  % 100);
							if (sNum>truckStreetNum){
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH),2000));
								truckHeading=3;
							}
							else {
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH), 4000));
								truckHeading=1;
							}// see here
							//truckHeading = (truck.getHouseNumber() > a.getHouseNumber()) ? 2:4;
							routeLength += Math.abs(corner - truckHouseNum);
							truck=(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH));
						}

						else if (truckHeading==2){//2; East(↓)
							corner =hNum - (hNum  % 100);

							if (sNum>truckStreetNum){
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH),4000));
								truckHeading=3;
							}
							else{
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH),2000));
								truckHeading =1;
								//truck= new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH);
							}
							routeLength += Math.abs(truckHouseNum -( hNum -(hNum  % 100)));
							truck= new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH);

						}
					}
					//up side
					else {
						if (truckHeading==4){// West(↑)
							corner= hNum - (hNum  % 100);
							if (sNum>truckStreetNum){
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH),2000));
								truckHeading =3;
							}
							else {
								instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH),4000));
								truckHeading =1;
							}
							routeLength += Math.abs(corner - truckHouseNum);
							truck=(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH));
						}
						else if (truckHeading==2){//2; East(↓)
							corner =(truckHouseNum) - (truckHouseNum  % 100);
							if (sNum>truckStreetNum) {
								instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.SOUTH), 4000));
								truckHeading=3;
							}
							else {
								instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.SOUTH), 2000));
								truckHeading=1;
							}
							routeLength += Math.abs(corner - truckHouseNum);
							truck=(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.SOUTH));

						}
					}
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
					y1 = truckStreetNum ;
					if (truckHeading==1){

						if (hNum > y1*100)
							instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.EAST),4000));
						else
							instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.EAST),2000));}
					else if (truckHeading==3){

						if (hNum > y1*100)
							instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.EAST),2000));
						else{
							instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.EAST),4000));}
					}
					routeLength += Math.abs( x2*100 - truckHouseNum);
					truck=(new Address(y1*100,x2,StreetDirection.EAST));
					//break;
				}
				else if (truckDir == StreetDirection.EAST){
					x2 = sNum;
					y1 = truckStreetNum ;
					if (truckHeading==2){
						//////// now here
						if (hNum > y1*100)
							instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.SOUTH),4000));
						else
							instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.SOUTH),2000));
					}
					else if (truckHeading==4){

						if (hNum > y1*100)
							instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.SOUTH),2000));
						else{
							instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.SOUTH),4000));}
					}
					routeLength += Math.abs( x2*100 - truckHouseNum);
					truck=(new Address(y1*100,x2,StreetDirection.SOUTH));

				}
		 	}
		} //close while

		// 1; (←)South, 2; East(↓), 3; North(→), 4; West(↑)
		if (truck.getStreetDirection()==StreetDirection.EAST){
			truckHeading = (truck.getHouseNumber() < a.getHouseNumber()) ? 2:4;}
		else if (truck.getStreetDirection()==StreetDirection.SOUTH){
			truckHeading = (truck.getHouseNumber() > a.getHouseNumber()) ? 1:3;}

		routeLength += Math.abs(truck.getHouseNumber() - a.getHouseNumber());
		instructions.add(new Instruction(a,1000));
		t.setAddress(a);
	}

//	void  handleUturn(Address a, Address truck) {

//	 }

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
