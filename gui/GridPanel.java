package Simulation.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.time.LocalTime;
import javax.swing.JPanel;

import Simulation.Address;
import Simulation.Instruction;
import Simulation.Order;
//import Simulation.RouteTo;
import Simulation.Router;
import Simulation.SandwichTruck;
import Simulation.StreetDirection;

/**
 * Creates a grid that paints the truck and all addresses
 * 
 * @author Daniel
 *
 * bug solving some of the issues we have had with
 * the simulation of the track movement and drawRoute method and drawAddresses method (paints the truck and all addresses)
 * @author Riyad
 */
public class GridPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	// 20 Lines, 17 total spaces
	private SandwichTruck t;
	private int hoodHeight = 20, hoodWidth = 20;
	private int lineDistance = 1;
	int houseDistance = lineDistance / 9;
	private Router route;
	private Dimension next;
	private List<Address> paintMe = new LinkedList<>();
	private double countDistance = 0;
	int count;

	//set starting time
	LocalTime time1 = LocalTime.of(10,0,0);
	// setting the truck speed
	public static final int truckSpeed = SandwichTruck.truckSpeed;
	// setting the distance between houses
	private static double distanceToNext = SandwichTruck.distanceToNext;

	private static int instructionCounter = 0;
	private static ArrayList<Instruction> instructions;



	// TODO Make grid adjustable

	public GridPanel(SandwichTruck truck) {
		t = truck;
	}

	public GridPanel(SandwichTruck truck, Rectangle neighborHoodSize) {
		this.t = truck;
		hoodHeight = neighborHoodSize.height;
		hoodWidth = neighborHoodSize.width;
	}

	@Override
	public void setSize(int x, int y) {
		super.setSize(x, x);
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		drawGrid(g);
		drawAddresses(g);
		drawDeliveredOrderAddress(g);
		drawTruck(g);
		// if (instructionCounter == 0)
		drawRoute(g);
		// else
		// instructionCounter--;
	}

	private void drawGrid(Graphics2D g) {
		// Get distance between each line
		// Implement scaling in MapWindow? Make sure GridPanel is always a square
		// relative to size of window
		if (getWidth() < getHeight())
			lineDistance = this.getWidth() / (hoodWidth - 2);
		else
			lineDistance = this.getHeight() / (hoodHeight - 2);
		houseDistance = lineDistance / 9;
		// Make XY Ambiguous
		for (int i = 0; i < hoodHeight; i++) {
			int currentXY = i * lineDistance;
			g.drawLine(currentXY, 0, currentXY, getHeight());
			g.drawLine(0, currentXY, getWidth(), currentXY);
		}
	}

	private void drawTruck(Graphics2D g) {
		// Store original graphics color, set to Red
		Color c = g.getColor();
		g.setColor(Color.RED);

		// Get trucks x and y
		Dimension curXY = getAddressXY(t.getCurrentAddress());
		g.fillOval(curXY.width - 2, curXY.height - 2, 5, 5);
		g.setColor(c);

		// set truck distribution Center @author Riyad
		Dimension disTruck = getAddressXY(t.distributionCenter);
		g.setColor(Color.BLUE);
		g.fillRect(disTruck.width,disTruck.height,5,5);

	}

	// fixed the bugs of setting the Dimension of an Address @author Riyad
	private Dimension getAddressXY(Address a) {
		// Nine houses on a block, so in between two streets there are nine available
		// spots
		int blockX, blockY;
		if (a.getStreetDirection() == StreetDirection.EAST) {
			// Y value is street number, house is X
			blockY = a.getStreetNumber() *lineDistance;
//			blockX = (((a.getHouseNumber())/100 *lineDistance))+((a.getHouseNumber() /100) + houseDistance);
			blockX = ((a.getHouseNumber()/100) *lineDistance) + ((a.getHouseNumber()%100)/10)*houseDistance;

		} else {
			// X value is street number, house is Y
			blockX = a.getStreetNumber() *lineDistance;
			blockY = ((a.getHouseNumber()/100) *lineDistance) + ((a.getHouseNumber()%100)/10)*houseDistance;
		}

		return new Dimension(blockX, blockY);
	}

//	@Deprecated
//	private void drawRouteOld(Graphics2D g) {
//		Address next = t.peekNextRouteInstruction().getAddress();
//		Address cur = t.getCurrentAddress();
//
//		System.out.println(
//				"Going to: " + next.getHouseNumber() + " " + next.getStreetDirection() + " " + next.getStreetNumber());
//
//		System.out.println(
//				"Coming from: " + cur.getHouseNumber() + " " + cur.getStreetDirection() + " " + cur.getStreetNumber());
//
//		if (next.getHouseNumber() == cur.getHouseNumber() && cur.getStreetNumber() == next.getStreetNumber()
//				&& next.getStreetDirection() == cur.getStreetDirection()) {
//			next = t.getNextRouteInstruction().getAddress();
//		}
//
//		if (next.getStreetDirection() == cur.getStreetDirection()) {
//			if (next.getHouseNumber() > cur.getHouseNumber()) {
//				// Add one to house number
//				Address a = new Address(cur.getHouseNumber() + 10, cur.getStreetNumber(), cur.getStreetDirection());
//				t.setAddress(a);
//			} else if (next.getHouseNumber() < cur.getHouseNumber()) {
//				// Subtract one
//				Address a = new Address(cur.getHouseNumber() - 10, cur.getStreetNumber(), cur.getStreetDirection());
//				t.setAddress(a);
//			} else {
//				Address a = new Address(cur.getStreetNumber() * 100 - 1, cur.getHouseNumber() / 100,
//						next.getStreetDirection());
//				t.setAddress(a);
//			}
//		} else {
//			Address a = new Address(cur.getStreetNumber() * 100, cur.getHouseNumber() / 100,
//					(cur.getStreetDirection() == StreetDirection.SOUTH) ? StreetDirection.EAST : StreetDirection.SOUTH);
//			t.setAddress(a);
//		}
//
//		// TODO Use last intermediate address as turning point rather than calculating
//		// new turning point
//	}
//
//	@Deprecated
//	public void drawRouteNew(Graphics2D g) {
//		if (route == null || route.getRoute().isEmpty()) {
//			route = (RouteTo) t.allOrderRoute();
//		}
//		if (instructions == null || instructions.size() < 2) {
//			Instruction tmp = null;
//			if (instructions != null && instructions.size() != 0)
//				tmp = instructions.get(0);
//			populateRouteNew(route);
//			instructionCounter = instructions.get(0).getTime();
//			if (tmp != null)
//				instructions.add(0, tmp);
//		}
//		Color old = g.getColor();
//		g.setColor(Color.BLUE);
//		for (Instruction i : route.getRoute()) {
//			Dimension iXY = getAddressXY(i.getAddress());
//
//			g.drawOval(iXY.width, iXY.height, 5, 5);
//		}
//		g.setColor(old);
//
//		t.setAddress(instructions.get(0).getAddress());
//		instructions.remove(0);
//		System.out.println(t.getCurrentAddress());
//	}

	public int getInstructionTime() {
		return instructionCounter;
	}

//	@Deprecated
//	private void populateRoute(Router r) {
//		Instruction i = t.getNextRouteInstruction();
//		Dimension iXY = getAddressXY(i.getAddress());
//		Dimension tXY = getAddressXY(t.getCurrentAddress());
//		Address tAdd = t.getCurrentAddress();
//
//		ArrayList<Instruction> ins = new ArrayList<Instruction>();
//
//		if (iXY.width == tXY.width) {
//			if (iXY.height > tXY.height) {
//				if (tAdd.getStreetDirection() == StreetDirection.EAST)
//					tAdd = new Address(tAdd.getStreetNumber() * 100, tAdd.getHouseNumber() / 100,
//							StreetDirection.SOUTH);
//				for (int nX = tXY.height; nX < iXY.height + 1; nX += houseDistance) {
//					ins.add(new Instruction(
//							new Address(tAdd.getHouseNumber() + 1, tAdd.getStreetNumber(), StreetDirection.SOUTH), 1));
//				}
//			} else {
//				if (tAdd.getStreetDirection() == StreetDirection.SOUTH)
//					tAdd = new Address(tAdd.getStreetNumber() * 100, tAdd.getHouseNumber() / 100, StreetDirection.EAST);
//				for (int nX = tXY.width; nX < iXY.width + 1; nX += houseDistance) {
//					ins.add(new Instruction(
//							new Address(tAdd.getHouseNumber() - 1, tAdd.getStreetNumber(), StreetDirection.EAST), 1));
//				}
//			}
//
//		} else if (iXY.height == tXY.height) {
//			if (iXY.width > tXY.width) {
//				if (tAdd.getStreetDirection() == StreetDirection.EAST)
//					tAdd = new Address(tAdd.getStreetNumber() * 100, tAdd.getHouseNumber() / 100,
//							StreetDirection.SOUTH);
//				for (int nX = tXY.width; nX < iXY.width + 1; nX += houseDistance) {
//					ins.add(new Instruction(
//							new Address(tAdd.getHouseNumber() + 10, tAdd.getStreetNumber(), StreetDirection.SOUTH), 1));
//				}
//			} else {
//				if (tAdd.getStreetDirection() == StreetDirection.EAST)
//					tAdd = new Address(tAdd.getStreetNumber() * 100, tAdd.getHouseNumber() / 100,
//							StreetDirection.SOUTH);
//				for (int nX = tXY.width; nX < iXY.width + 1; nX += houseDistance) {
//					ins.add(new Instruction(
//							new Address(tAdd.getHouseNumber() - 10, tAdd.getStreetNumber(), StreetDirection.SOUTH), 1));
//				}
//			}
//			instructions = ins;
//		} else {
//			System.out.println("ROUTE INVALID");
//		}
//	}

//	@Deprecated
//	private void populateRouteNew(Router r) {
//		// Generate route using houseNumber difference
//
//		// Get Route instruction
//		Instruction i = route.getRoute().get(0);
//		route.removeFirstInstruction();
//		// Get address to go to
//		Address iAdd = i.getAddress();
//		// Get trucks current address
//		Address tAdd = t.getCurrentAddress();
//
//		ArrayList<Instruction> ins = new ArrayList<Instruction>();
//
//		// If the street directions don't match up, the route is discontinuous, check to
//		// see if can be fixed
//		if ((iAdd.getHouseNumber() % 100 == 0) && (iAdd.getStreetDirection() != tAdd.getStreetDirection())) {
//			// Going to street is invalid, need to correct
//			int newHouse = iAdd.getStreetNumber() * 100;
//			int newStreet = iAdd.getHouseNumber() / 100;
//
//			if (newStreet != tAdd.getStreetNumber()) {
//				System.out.println("INVALID ROUTE");
//			} else {
//				Address iOld = iAdd;
//				iAdd = new Address(newHouse, newStreet, tAdd.getStreetDirection());
//				System.out.println("Fixed bad Address: " + iOld + " -> " + iAdd);
//				iOld = null;
//			}
//
//		}
//
//		// Calculate distance between addresses
//		int hNum = tAdd.getHouseNumber() - iAdd.getHouseNumber();
//		boolean decreasing = false;
//		// If the distance is a positive value, then Addresses should be decreasing to
//		// reach it
//		if (hNum > 0)
//			decreasing = true;
//		hNum = (Math.abs(hNum));
//		// Calculate discrete addresses to reach the endpoint
//		for (int a = 0; a <= hNum; a += 10) {
//			if (decreasing)
//				ins.add(new Instruction(
//						new Address(tAdd.getHouseNumber() - a, tAdd.getStreetNumber(), tAdd.getStreetDirection()), 1));
//			else
//				ins.add(new Instruction(
//						new Address(tAdd.getHouseNumber() + a, tAdd.getStreetNumber(), tAdd.getStreetDirection()), 1));
//		}
//		// All endpoints except for the final destination should be turn locations, so
//		// set truck as if it was on the other street
//		if (ins.get(ins.size() - 1).getAddress().getHouseNumber() % 100 == 0) {
//			Address old = ins.get(ins.size() - 1).getAddress();
//			ins.remove(ins.size() - 1);
//			ins.add(new Instruction(new Address(old.getStreetNumber() * 100, old.getHouseNumber() / 100,
//					(old.getStreetDirection() == StreetDirection.SOUTH) ? StreetDirection.EAST : StreetDirection.SOUTH),
//					1));
//		}
//
//		instructions = ins;
//
//	}

	// Move truck based on XY coordinates rather than address
//	@Deprecated
//	public void drawRouteEvenNewer(Graphics2D g) {
//		if (route == null || route.getRoute().isEmpty()) {
//			route = (RouteTo) t.allOrderRoute();
//		}
//		Dimension cur = getAddressXY(t.getCurrentAddress());
//		Dimension intermediate = cur;
//		if (distanceToNext == 0)
//			next = getAddressXY(route.getRoute().get(0).getAddress());
//
//		System.out.println("(" + cur.width + "," + cur.height + ");(" + next.width + "," + next.height + ")");
//
//		if (cur.width == next.width && cur.height == next.height) {
//			// Next instruction
//			distanceToNext = 0;
//		} else if (cur.width == next.width) {
//			distanceToNext = cur.height - next.height;
//			if (distanceToNext > 0) {
//				// Subtract one from height
//				intermediate = new Dimension(cur.width, cur.height - 1);
//			} else {
//				// Add one to height
//				intermediate = new Dimension(cur.width, cur.height + 1);
//			}
//		} else if (cur.height == next.height) {
//			distanceToNext = cur.width - next.width;
//			if (distanceToNext > 0) {
//				// Subtract one from width
//				intermediate = new Dimension(cur.width - 1, cur.height);
//			} else {
//				// Add one to width
//				intermediate = new Dimension(cur.width + 1, cur.height);
//			}
//		} else {
//			System.out.println(
//					"INVALID ROUTE: (" + cur.width + "," + cur.height + ");(" + next.width + "," + next.height + ")");
//		}
//		if (intermediate.height == cur.height) {
//			// Get Address from XY when height is street number
//
//		} else if (intermediate.width == cur.width) {
//
//		}
//	}

	private void drawAddresses(Graphics2D g) {
		PriorityQueue<Order> orders = t.getAllOrders();
		for (Order o : orders) {
			Dimension xy = getAddressXY(o.getAddress());
			g.setColor(Color.DARK_GRAY);
			g.fillOval(xy.width, xy.height, 5, 5);
		}
	}


	//Fixed the bugs of the drawRoute that we had have in Sprint3
	// and add the time(hours, minutes, seconds) for the instructions. @author Riyad
	private void drawRoute(Graphics2D g) {
		//LocalTime time2;
		Instruction next = t.peekNextRouteInstruction();
		Address cur = t.getCurrentAddress();
		Address nextAdd = next.getAddress();
		PriorityQueue<Order> orders = t.getAllOrdersCopy();
		LocalTime time2;
		//System.out.println(cur);

		if (cur.getHouseNumber() == nextAdd.getHouseNumber() && cur.getStreetNumber() == nextAdd.getStreetNumber()
				&& cur.getStreetDirection() == nextAdd.getStreetDirection()) {
				next = t.getNextRouteInstruction();
				nextAdd = next.getAddress();
			if (count!=0){
				double timeConvertedToSec= (countDistance/truckSpeed)*3600;
				time2= time1.plusSeconds((long) timeConvertedToSec);

				// last index
				if (cur.getHouseNumber()%100==0){
					System.out.println("Turning in  "+cur+ " at time : " + time2);
					// if cur is the last turn before getting back to the distribution Center
					if (t.getCurRoute().getRoute().size() == 2){
						System.out.println(". \n. \n--Delivered all the orders!--");
					}
				}else if (cur.getHouseNumber()%100!=0){
					if (t.getCurRoute().getRoute().size()!=1){
					System.out.println("Just delivered the Order of : " + cur + " at time : "+ time2);
					System.out.println("-----");}
					if (t.getCurRoute().getRoute().size() == 1){
						System.out.println("Just got back to the distribution Center : " + cur + " at time : "+ time2);
						System.out.println("-------------------------------------------");
					}
				}
				// set the original time to the updated time
				time1 = time2;
				// reset distance & count, to recalculate
				countDistance=0;
				count=0;
			}
				//System.out.println("Turn");
		}
		if (cur.getStreetDirection() != nextAdd.getStreetDirection()) {
			instructionCounter = next.getTime();
			// This means that we are turning
			t.setAddress(nextAdd);
			return;
		}
		if (cur.getHouseNumber() == nextAdd.getHouseNumber()) {
			// DO nothing, the method t.getNextRouteInstruction() will increment the truck
			// to the next instruction
				for (Order i : orders) {
					if (i.getAddress().equals(nextAdd)) {
						paintMe.add(i.getAddress());
					}
				}
		}
		if (cur.getHouseNumber() == nextAdd.getHouseNumber()) {}

		else if (cur.getHouseNumber() > nextAdd.getHouseNumber()) {
			t.setAddress(new Address(cur.getHouseNumber() - 10, cur.getStreetNumber(), cur.getStreetDirection()));
			countDistance+=distanceToNext;
			count+=1;
		} else {
			t.setAddress(new Address(cur.getHouseNumber() + 10, cur.getStreetNumber(), cur.getStreetDirection()));
			countDistance+=distanceToNext;
			count+=1;
			//time2= time1.plusSeconds((long)distanceToNext/truckSpeed);
		}
	}

	// Add drawDeliveredOrderAddress to paint the visited Address @author Riyad
	public void drawDeliveredOrderAddress(Graphics2D g){
		g.setColor(Color.orange);
			for (Address ii : paintMe){
				Dimension iXY = getAddressXY(ii);
				g.drawOval(iXY.width, iXY.height, 5, 5);
			}
	}
}