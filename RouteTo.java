
package Simulation;

import java.util.*;
/**
 *
 * Contains data representing instructions of routing between two addresses(truck current location and the address of the next order)
 * with the delaying units of right-hand and left-hand turns, besides the total route length.
 *
 * @author Rio and Dylan
 *
 */

//RouteTo
public class RouteTo implements Router{

	public List<Instruction> instructions = new LinkedList<>();;
	public static double routeLength;
	public int unitsofTime;
	public double timeUnits;
	public static int truckHeading=3;  // 1; (←)South, 2; East(↓), 3; North(→), 4; West(↑)
	Address truck;
	Address a;
	int count;
	double theRouteLength;


	RouteTo(Collection<Order> orderss, SandwichTruck t) {
		truck = t.getCurrentAddress();

		Iterator<Order> iterator = orderss.iterator();
		while (iterator.hasNext())
		{
			Order o = iterator.next();
			a = o.getAddress();
		/*
           test the code by setting a truck address and the address that truck going to
        */
		//truck= new Address(1220, 14, StreetDirection.EAST);
		//a = new Address(240, 1, StreetDirection.EAST);

		truckHeading = t.getHeading();

		int hNum;
		int sNum;
		StreetDirection sDir;
		int truckHouseNum;
		int truckStreetNum;
		StreetDirection truckDir;
		//instructions = new LinkedList<>();
		boolean sameDirection;
		boolean sameStreetNum = a.getStreetNumber() == truck.getStreetNumber();
		//instructions.add(new Instruction(truck));

		// Deal with the U-turn case in same street situation
		if (a.getStreetDirection() == truck.getStreetDirection() && sameStreetNum) {
			int corner = truck.getHouseNumber() - (truck.getHouseNumber() % 100);

			// Good!
			if ((truck.getStreetDirection() == StreetDirection.SOUTH && truckHeading == 1 && a.getHouseNumber() > truck.getHouseNumber())) {
				instructions.add(new Instruction(new Address(corner,truck.getStreetNumber()  , StreetDirection.SOUTH), 2000));
				instructions.add(new Instruction(new Address(truck.getStreetNumber() * 100, corner / 100, StreetDirection.EAST)));

				instructions.add(new Instruction(new Address((truck.getStreetNumber() - 1)*100,corner/100,  StreetDirection.EAST), 2000));
				instructions.add(new Instruction(new Address(corner, truck.getStreetNumber() - 1, StreetDirection.SOUTH)));
				routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 1;

				truck = new Address(corner, truck.getStreetNumber() - 1, StreetDirection.SOUTH);
				truckHeading = 3;
			}
			// Good!
			else if ((truck.getStreetDirection() == StreetDirection.SOUTH && truckHeading == 3 && a.getHouseNumber() < truck.getHouseNumber())) {
				instructions.add(new Instruction(new Address((corner + 100),truck.getStreetNumber(), StreetDirection.SOUTH), 2000));
				instructions.add(new Instruction(new Address(truck.getStreetNumber() * 100, (corner + 100) / 100, StreetDirection.EAST)));

				instructions.add(new Instruction(new Address((truck.getStreetNumber() + 1)*100,(corner + 100)/100,  StreetDirection.EAST), 2000));
				instructions.add(new Instruction(new Address(corner + 100, truck.getStreetNumber() + 1, StreetDirection.SOUTH)));
				truckHeading = 1;
				routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 1;

				truck = new Address(corner + 100, truck.getStreetNumber() + 1, StreetDirection.SOUTH);
			}
			// Good!
			else if ((truck.getStreetDirection() == StreetDirection.EAST && truckHeading == 2 && a.getHouseNumber() < truck.getHouseNumber())) {
				instructions.add(new Instruction(new Address( (corner + 100) ,truck.getStreetNumber(), StreetDirection.EAST), 2000));
				instructions.add(new Instruction(new Address(truck.getStreetNumber() * 100, (corner + 100) / 100, StreetDirection.SOUTH)));

				instructions.add(new Instruction(new Address( (truck.getStreetNumber() - 1)*100,(corner + 100)/100, StreetDirection.SOUTH), 2000));
				instructions.add(new Instruction(new Address(corner + 100, truck.getStreetNumber() - 1, StreetDirection.EAST)));
				truckHeading = 4;
				routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 1;
				truck = new Address(corner + 100, truck.getStreetNumber() - 1, StreetDirection.EAST);

			}
			// Good!
			else if ((truck.getStreetDirection() == StreetDirection.EAST && truckHeading == 4 && a.getHouseNumber() > truck.getHouseNumber())) {
				instructions.add(new Instruction(new Address( corner ,truck.getStreetNumber() , StreetDirection.EAST), 2000));
				instructions.add(new Instruction(new Address(truck.getStreetNumber() * 100, corner / 100, StreetDirection.SOUTH)));

				instructions.add(new Instruction(new Address((truck.getStreetNumber() + 1)*100,corner/100,  StreetDirection.SOUTH), 2000));
				instructions.add(new Instruction(new Address(corner, truck.getStreetNumber() + 1, StreetDirection.EAST), 2000));
				truckHeading = 2;
				routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 1;
				truck = new Address(corner, truck.getStreetNumber() + 1, StreetDirection.EAST);
			}
			routeLength += 100;
		}


		if (truck.getStreetDirection() != a.getStreetDirection() && truck.getStreetDirection() == StreetDirection.EAST) {
			// Good!
			if (truckHeading == 4 && truck.getHouseNumber() - (truck.getHouseNumber() % 100) < a.getStreetNumber() * 100) {

				int corner = truck.getHouseNumber() - (truck.getHouseNumber() % 100);
				instructions.add(new Instruction(new Address(corner,truck.getStreetNumber()  , StreetDirection.EAST), 2000));
				instructions.add(new Instruction(new Address(truck.getStreetNumber() * 100, corner / 100, StreetDirection.SOUTH)));
				truckHeading = 3;
				routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 1;
				truck = new Address(truck.getStreetNumber() * 100, corner / 100, StreetDirection.SOUTH);
			}
			// Good!
			else if (truckHeading == 2 && truck.getHouseNumber() - ((truck.getHouseNumber() + 100) % 100) > a.getStreetNumber() * 100) {

				int corner = (truck.getHouseNumber()) - (truck.getHouseNumber() % 100);
				instructions.add(new Instruction(new Address( (corner + 100) ,truck.getStreetNumber(), StreetDirection.EAST), 4000));
				instructions.add(new Instruction(new Address(truck.getStreetNumber() * 100, (corner + 100) / 100, StreetDirection.SOUTH)));
				truckHeading = 3;
				routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 1;
				truck = new Address(truck.getStreetNumber() * 100, (corner + 100) / 100, StreetDirection.SOUTH);
			}
		}
		// Good!
		if (truck.getStreetDirection() != a.getStreetDirection() && truck.getStreetDirection() == StreetDirection.SOUTH) {
			int corner = (truck.getHouseNumber()) - (truck.getHouseNumber() % 100);
			if (truckHeading == 3 && truck.getHouseNumber() - (truck.getHouseNumber() % 100) > a.getStreetNumber() * 100) {
				instructions.add(new Instruction(new Address( (corner + 100) ,truck.getStreetNumber(), StreetDirection.SOUTH), 2000));
				instructions.add(new Instruction(new Address((truck.getStreetNumber() * 100), (corner + 100) / 100, StreetDirection.EAST)));
				truckHeading = 2;
				routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 1;
				truck = new Address((truck.getStreetNumber() * 100), (corner + 100) / 100, StreetDirection.EAST);
			} else if (truckHeading == 1 && truck.getHouseNumber() - (truck.getHouseNumber() % 100) < a.getStreetNumber() * 100) {
				instructions.add(new Instruction(new Address( corner,truck.getStreetNumber() , StreetDirection.SOUTH), 4000));
				instructions.add(new Instruction(new Address(truck.getStreetNumber() * 100, corner / 100, StreetDirection.EAST)));
				truckHeading = 2;
				routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 1;
				truck = new Address(truck.getStreetNumber() * 100, corner / 100, StreetDirection.EAST);
			}
		}


		while (!(a.getStreetDirection().equals(truck.getStreetDirection())) || (a.getStreetNumber() != truck.getStreetNumber())) {

			hNum = a.getHouseNumber();
			sNum = a.getStreetNumber();
			sDir = a.getStreetDirection();
			truckHouseNum = truck.getHouseNumber();
			truckStreetNum = truck.getStreetNumber();
			truckDir = truck.getStreetDirection();
			sameDirection = sDir == truckDir;


			if (sameDirection) {
				// Truck On East; eg:                *
				//                                   |
				//                                   ()------
				//                                           |
				//                                           *
				int corner;
				if (truckDir == StreetDirection.SOUTH) {
					// if Address in the right side
					if (hNum > truckHouseNum) {
						if (truckHeading == 1) { //(←)South

							corner = truckHouseNum - (truckHouseNum % 100);
							if (sNum < truckStreetNum) {
								instructions.add(new Instruction(new Address(corner,truckStreetNum ,StreetDirection.SOUTH), 2000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.EAST)));
								truckHeading = 4;
							} else {
								instructions.add(new Instruction(new Address(corner,truckStreetNum,StreetDirection.SOUTH), 4000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.EAST)));
								truckHeading = 2;
							}
							routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 1;
							truck = (new Address(truckStreetNum * 100, corner / 100, StreetDirection.EAST));
						} else if (truckHeading == 3) {// North(→)

							corner = hNum - (hNum % 100);
							if (sNum < truckStreetNum) {
								instructions.add(new Instruction(new Address( corner ,truckStreetNum , StreetDirection.SOUTH), 4000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.EAST)));
								truckHeading = 4;
							} else {
								instructions.add(new Instruction(new Address(corner,truckStreetNum , StreetDirection.SOUTH), 2000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.EAST)));
								truckHeading = 2;
							}
							routeLength += Math.abs(truckHouseNum - corner);
                            unitsofTime += 1;
							truck = (new Address(truckStreetNum * 100, corner / 100, StreetDirection.EAST));
						}

					}
					// if Address in the left side
					else {
						if (truckHeading == 1) {// (←)South

							corner = hNum - (hNum % 100);
							if (sNum < truckStreetNum) {
								instructions.add(new Instruction(new Address(corner,truckStreetNum , StreetDirection.SOUTH), 2000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.EAST)));
								truckHeading = 4;
							} else {
								instructions.add(new Instruction(new Address( corner,truckStreetNum , StreetDirection.SOUTH), 4000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.EAST)));
								truckHeading = 2;
							}
							//review route length
							routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 1;
							truck = (new Address(truckStreetNum * 100, corner / 100, StreetDirection.EAST));
						} else if (truckHeading == 3) {//North(→)
							corner = (truckHouseNum) - (truckHouseNum % 100);
							if (sNum < truckStreetNum) {
								instructions.add(new Instruction(new Address( (corner + 100),truckStreetNum, StreetDirection.SOUTH), 4000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, (corner + 100) / 100, StreetDirection.EAST)));
								truckHeading = 4;
							} else {
								instructions.add(new Instruction(new Address( (corner + 100) ,truckStreetNum, StreetDirection.SOUTH), 2000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, (corner + 100) / 100, StreetDirection.EAST)));
								truckHeading = 2;

							}
							//review
							routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 1;
							truck = (new Address(truckStreetNum * 100, (corner + 100) / 100, StreetDirection.EAST));

						}

					}

				}
				// more that two moves; eg:            *---
				//                                         |
				//                                         |
				// corner, so now it is in 2 moves case -> ()------*

				else if (truckDir == StreetDirection.EAST) {
					//  down side
					if (hNum > truckHouseNum) {
						if (truckHeading == 4) {// West(↑)
							///
							corner = truckHouseNum - (truckHouseNum % 100);
							if (sNum > truckStreetNum) {
								instructions.add(new Instruction(new Address(corner,truckStreetNum , StreetDirection.EAST), 2000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.SOUTH)));
								truckHeading = 3;
							} else {
								instructions.add(new Instruction(new Address( corner ,truckStreetNum, StreetDirection.EAST), 4000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.SOUTH)));
								truckHeading = 1;
							}// see here
							//truckHeading = (truck.getHouseNumber() > a.getHouseNumber()) ? 2:4;
							routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 1;
							truck = (new Address(truckStreetNum * 100, corner / 100, StreetDirection.SOUTH));

						} else if (truckHeading == 2) {//2; East(↓)
							corner = hNum - (hNum % 100);

							if (sNum > truckStreetNum) {
								instructions.add(new Instruction(new Address( corner ,truckStreetNum, StreetDirection.EAST), 4000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.SOUTH)));
								truckHeading = 3;
							} else {
								instructions.add(new Instruction(new Address( corner ,truckStreetNum, StreetDirection.EAST), 2000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.SOUTH)));
								truckHeading = 1;
								//truck= new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH);
							}
							routeLength += Math.abs(truckHouseNum - corner);
                            unitsofTime += 1;
							truck = new Address(truckStreetNum * 100, corner / 100, StreetDirection.SOUTH);

						}
					}
					//up side
					else {
						if (truckHeading == 4) {// West(↑)
							corner = hNum - (hNum % 100);
							if (sNum > truckStreetNum) {
								instructions.add(new Instruction(new Address( corner ,truckStreetNum, StreetDirection.EAST), 2000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.SOUTH)));
								truckHeading = 3;
							} else {
								instructions.add(new Instruction(new Address( corner ,truckStreetNum , StreetDirection.EAST), 4000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, corner / 100, StreetDirection.SOUTH)));
								truckHeading = 1;
							}
							routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 1;
							truck = (new Address(truckStreetNum * 100, corner / 100, StreetDirection.SOUTH));
						} else if (truckHeading == 2) {//2; East(↓)
							corner = (truckHouseNum) - (truckHouseNum % 100);
							if (sNum > truckStreetNum) {
								instructions.add(new Instruction(new Address( (corner + 100),truckStreetNum, StreetDirection.EAST), 4000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, (corner + 100) / 100, StreetDirection.SOUTH)));
								truckHeading = 3;
							} else {
								instructions.add(new Instruction(new Address( (corner + 100),truckStreetNum, StreetDirection.EAST), 2000));
								instructions.add(new Instruction(new Address(truckStreetNum * 100, (corner + 100) / 100, StreetDirection.SOUTH)));
								truckHeading = 1;
							}
							routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 1;
							truck = (new Address(truckStreetNum * 100, (corner + 100) / 100, StreetDirection.SOUTH));

						}
					}
				}
			}

			// In two moves eg: *---() <- new address
			//                      |
			//                      |
			//                      *
			else {
				int y1, x2;
				Address tem;
				if (truckDir == StreetDirection.SOUTH) {
					x2 = sNum;
					y1 = truckStreetNum;
					if (truckHeading == 1) {

						if (hNum > y1 * 100) {
							instructions.add(new Instruction(new Address(x2 * 100, y1, StreetDirection.SOUTH), 4000));
							instructions.add(new Instruction(new Address(y1 * 100, x2, StreetDirection.EAST)));
						}
						else{
							instructions.add(new Instruction(new Address(x2 * 100, y1, StreetDirection.SOUTH), 2000));
							instructions.add(new Instruction(new Address(y1 * 100, x2, StreetDirection.EAST)));
						}
					} else if (truckHeading == 3) {

						if (hNum > y1 * 100) {
							instructions.add(new Instruction(new Address(x2 * 100, y1, StreetDirection.SOUTH), 2000));
							instructions.add(new Instruction(new Address(y1 * 100, x2, StreetDirection.EAST)));
						}
						else {
							instructions.add(new Instruction(new Address(x2 * 100, y1, StreetDirection.SOUTH), 4000));
							instructions.add(new Instruction(new Address(y1 * 100, x2, StreetDirection.EAST)));
						}
					}
					routeLength += Math.abs(x2 * 100 - truckHouseNum);
                    unitsofTime += 1;
					truck = (new Address(y1 * 100, x2, StreetDirection.EAST));
					//break;
				} else if (truckDir == StreetDirection.EAST) {
					x2 = sNum;
					y1 = truckStreetNum;
					if (truckHeading == 2) {
						//////// now here
						if (hNum > y1 * 100) {
							instructions.add(new Instruction(new Address(x2 * 100, y1, StreetDirection.EAST), 4000));
							instructions.add(new Instruction(new Address(y1 * 100, x2, StreetDirection.SOUTH)));

						}
						else{
							instructions.add(new Instruction(new Address(x2 * 100, y1, StreetDirection.EAST), 2000));
							instructions.add(new Instruction(new Address(y1 * 100, x2, StreetDirection.SOUTH)));
						}
					} else if (truckHeading == 4) {

						if (hNum > y1 * 100) {
							instructions.add(new Instruction(new Address(x2 * 100, y1, StreetDirection.EAST), 2000));
							instructions.add(new Instruction(new Address(y1 * 100, x2, StreetDirection.SOUTH)));
						}
						else {
							instructions.add(new Instruction(new Address(x2 * 100, y1, StreetDirection.EAST), 4000));
							instructions.add(new Instruction(new Address(y1 * 100, x2, StreetDirection.SOUTH)));
						}
					}

					routeLength += Math.abs(x2 * 100 - truckHouseNum);
                    unitsofTime += 1;
					truck = (new Address(y1 * 100, x2, StreetDirection.SOUTH));

				}
			}
		} //close while

		// 1; (←)South, 2; East(↓), 3; North(→), 4; West(↑)
		if (truck.getStreetDirection() == StreetDirection.EAST) {
			truckHeading = (truck.getHouseNumber() < a.getHouseNumber()) ? 2 : 4;
			instructions.add(new Instruction(a, 1000,truckHeading));
		}
		else if (truck.getStreetDirection() == StreetDirection.SOUTH) {
			truckHeading = (truck.getHouseNumber() > a.getHouseNumber()) ? 1 : 3;
			instructions.add(new Instruction(a, 1000,truckHeading));
		}
		routeLength += Math.abs(truck.getHouseNumber() - a.getHouseNumber());
		unitsofTime += 1;
		routeLength /= 100;
		//System.out.println(routeLength);
		t.setHeading(truckHeading);
		truck = (a);
		count+=1;
		theRouteLength+=routeLength;
		//System.out.println(count);
		//t.setAddress(a);
		}
		// make sure about it
		t.setAddress(SandwichTruck.distributionCenter);
	}



	public double getRouteLength() {
		return theRouteLength;
	}

	public List<Instruction> getRoute() {
		return instructions;
	}

	public void removeFirstInstruction() {
		if (instructions.size() != 1)
			instructions.remove(0);

	}

	public int getUnitsofTime(){
	    return unitsofTime;
    }
	public int getTruckHeading(){return truckHeading;}

	public double getTimeUnits(){
        timeUnits = (routeLength / 30);
        //Getting real time as the length is divided by 30MPH DH
        return  timeUnits;
    }
}


//$$$$$$$$$$$$$$$$