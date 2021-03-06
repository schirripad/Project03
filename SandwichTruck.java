package Simulation;

import java.awt.Rectangle;
import java.util.PriorityQueue;

import Simulation.gui.MapWindow;

/**
 * Sandwich Truck simulation, essentially drives program
 *
 * @author Dylan, Dan, Rio
 *
 */
public class SandwichTruck implements Observer {
	public static final Address distributionCenter = new Address(510, 5, StreetDirection.EAST);
	private Address curAddress;
	private Router curRoute;
	private PriorityQueue<Order> orders = new PriorityQueue<Order>();
	private PriorityQueue<Order> orderss = new PriorityQueue<Order>();
	private Order curOrder;
	private Rectangle neighborHoodSize = new Rectangle(20, 20);
	public int selectedStrategy;
	// if distributionCenter South, set heading 3. distributionCenter East, set
	// heading 2.
	private int truckHeading = 2;
	// setting the truck speed
	public static final int truckSpeed = 30;
	// setting the distance between houses
	public static double distanceToNext = 0.03;

	public SandwichTruck(Address a) {
		curAddress = a;
	}

	public Address getCurrentAddress() {
		return curAddress;
	}

	public void addOrder(Order o) {
		orders.add(o);
	}

	public int getHeading() {
		return truckHeading;
	}

	public void setHeading(int heading) {
		this.truckHeading = heading;
	}

	public Order getNextOrder() {
		return orders.poll();
	}

	public Order getCurrentOrder() {
		return curOrder;
	}

	public Instruction getNextRouteInstruction() {
		if (curRoute == null) {
			allOrderRoute();
		}
		Instruction next;
		if (curRoute.getRoute().size() == 1) {
			next = curRoute.getRoute().get(0);
		} else {
			next = curRoute.getRoute().get(0);
		}
		curRoute.removeFirstInstruction();
		return next;
	}

	public void addRouteInstruction(Instruction i, int index) {
		curRoute.getRoute().add(index, i);
	}

	public Instruction peekNextRouteInstruction() {
		if (curRoute == null) {
			// nextRoute();
			allOrderRoute();
		}
		return curRoute.getRoute().get(0);
	}

	public void setAddress(Address a) {
		curAddress = a;
	}

	public Order seeNextOrder() {
		return orders.peek();
	}

	public void setNeighborHoodSize(Rectangle r) {
		this.neighborHoodSize = r;
	}

	public void showAreaMap() {
		new MapWindow(this, neighborHoodSize);
	}

	public PriorityQueue<Order> getAllOrders() {
		return orders;
	}

	public PriorityQueue<Order> getAllOrdersCopy() {
		orderss = orders;
		return orderss;
	}

	// Add allOrderRoute that will get the instruction route from the
	// distributionCenter and visit all addresses @author Rio
	public Router allOrderRoute() {
		System.out.println("-------------------------------------------");
		if (selectedStrategy == 1) {
			System.out.println("Both hands");
			curRoute = new RouteTo();
		}

		if (selectedStrategy == 2) {
			System.out.println("Right hand");
			curRoute = new RouteTo();
		}

		curRoute.createRoute(orders, new SandwichTruck(getCurrentAddress()));

		System.out.println("* Total route length: " + curRoute.getRouteLength() + " miles");
		// delete next line
		System.out.println(curRoute.toString());
		System.out.println("* Instructions size is: " + curRoute.getRoute().size());
		System.out.println("* Truck speed is " + truckSpeed);
		System.out.println("-------------------------------------------");
		System.out.println("Start the route at: 10:00  from: " + this.getCurrentAddress() + "\n" + "." + "\n" + ".");
		return curRoute;
	}

	// public Router allOrderRoute(){
	// //Scanner reader = new Scanner(System.in); // Reading from System.in
	// System.out.println("-------------------------------------------");
	// //System.out.println("Choose a strategy movement for the truck: "+
	// // "\n"+"1: only left hand turn" +"\n"+"2: both left and right hand
	// turns"+"\n");
	//
	// //int n = reader.nextInt(); // Scans the next token of the input as an int.
	// //if (n ==1)
	// // curRoute = new RouteToByLeftHand(orders, new
	// SandwichTruck(this.getCurrentAddress()));
	// //else if (n ==2)
	// curRoute = new RouteTo(orders, new SandwichTruck(this.getCurrentAddress()));
	// //System.out.println("From: "+ this.getCurrentAddress());
	// //System.out.println("Route: ");
	//// for (Instruction a : curRoute.getRoute()) {
	//// System.out.println(a.getAddress());
	//// }
	// System.out.println("* Total route length: "+curRoute.getRouteLength()+"
	// miles");
	// System.out.println("* Instructions size is: "+curRoute.getRoute().size());
	// System.out.println("* Truck speed is "+ truckSpeed);
	// System.out.println("-------------------------------------------");
	// System.out.println("Start the route at: 10:00 from: "
	// +this.getCurrentAddress()+"\n"+"."+"\n"+".");
	// //reader.close();
	// return curRoute;
	// }

	public Router getCurRoute() {
		return curRoute;
	}

	@Override
	public void update(Instruction a) {
		setAddress(a.getAddress());
	}

}
