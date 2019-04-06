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
public class SandwichTruck {
	public static final Address distribtutionCenter = new Address(510, 5, StreetDirection.EAST);

	private Address curAddress;
	private Router curRoute;
	private PriorityQueue<Order> orders = new PriorityQueue<Order>();
	private Order curOrder;
	private Rectangle neighborHoodSize = new Rectangle(20, 20);

	private int truckHeading = 2;

	public SandwichTruck(int addressNum, int streetNum, StreetDirection streetDir) {
		curAddress = new Address(addressNum, streetNum, streetDir);
	}

	public SandwichTruck(int addressNum, int streetNum, StreetDirection streetDir, int truckHeading) {
		curAddress = new Address(addressNum, streetNum, streetDir);
		this.truckHeading = truckHeading;
	}

	public SandwichTruck(Address a) {
		curAddress = a;
	}

	public SandwichTruck(Address a, int truckHeading) {
		curAddress = a;
		this.truckHeading = truckHeading;
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

	public Router nextRoute() {
		System.out.println("NEXT");
		curOrder = getNextOrder();
		curRoute = new RouteTo(curOrder, new SandwichTruck(this.getCurrentAddress()));
		System.out.println("Route: ");
		for (Instruction a : curRoute.getRoute()) {
			System.out.println(a.getAddress());
		}
		System.out.println("---");
		return curRoute;
	}

	public Order getNextOrder() {
		return orders.poll();
	}

	public Order getCurrentOrder() {
		return curOrder;
	}

	public Instruction getNextRouteInstruction() {
		if (curRoute == null) {
			curRoute = new RouteTo(getNextOrder(), new SandwichTruck(this.getCurrentAddress(), truckHeading));
		}
		Instruction next;
		if (curRoute.getRoute().size() == 1) {
			next = curRoute.getRoute().get(0);
			nextRoute();
		} else {
			next = curRoute.getRoute().get(0);
		}
		curRoute.removeFirstInstruction();
		return next;
	}

	public Instruction peekNextRouteInstruction() {
		if (curRoute == null) {
			curRoute = new RouteTo(getNextOrder(), new SandwichTruck(this.getCurrentAddress(), truckHeading));
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

}
