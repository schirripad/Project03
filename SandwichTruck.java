package Simulation;

import java.awt.Rectangle;
import java.util.List;
import java.util.PriorityQueue;

import Simulation.gui.MapWindow;

/**
 * Sandwich Truck simulation, essentially drives program
 *
 * @author Dylan, Dan, Rio
 *
 */
public class SandwichTruck {
	public static final Address distributionCenter = new Address(910, 9, StreetDirection.SOUTH);
	public static final int truckSpeed = 30;

	private Address curAddress;
	private Router curRoute;
	private PriorityQueue<Order> orders = new PriorityQueue<Order>();
	private PriorityQueue<Order> orderss=new PriorityQueue<Order>();
	private Order curOrder;
	private Rectangle neighborHoodSize = new Rectangle(20, 20);

	private int truckHeading = 3;

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

//	public Router nextRoute() {
//		curOrder = getNextOrder();
//		System.out.println("------------" + orders.size());
//		System.out.println("NEXT");
//		System.out.println("curOrder:  "+curOrder.getAddress());
//		System.out.println("getCurrentAddress:  "+ this.getCurrentAddress());
//
//		curRoute = new RouteTo(curOrder, new SandwichTruck(this.getCurrentAddress()));
//		System.out.println("Next Order: "+ curOrder.getAddress());
//		System.out.println("From: "+ this.getCurrentAddress());
//		System.out.println("Route: ");
//		for (Instruction a : curRoute.getRoute()) {
//			System.out.println(a.getAddress());
//		}
//		System.out.println("---");
//		return curRoute;
//	}

	public Order getNextOrder() {
		return orders.poll();
	}

	public Order getCurrentOrder() {
		return curOrder;
	}

	public Instruction getNextRouteInstruction() {
		if (curRoute == null) {
			//nextRoute();
			allOrderRoute();
		}
		Instruction next;
		if (curRoute.getRoute().size() == 1) {
			next = curRoute.getRoute().get(0);
			//nextRoute();
		}
		else {
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
			//nextRoute();
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
		orderss=orders;
		return orderss;
	}

// Add allOrderRoute that will get the instruction route from the distributionCenter and visit all addresses @author Rio
	public Router allOrderRoute(){
		System.out.println("------------");
		//System.out.println("NEXT");
		curRoute = new RouteTo(orders, new SandwichTruck(this.getCurrentAddress()));
		System.out.println("From: "+ this.getCurrentAddress());
		//System.out.println("Route: ");
//			for (Instruction a : curRoute.getRoute()) {
//				System.out.println(a.getAddress());
//			}
		System.out.println("Total route length: "+curRoute.getRouteLength());
		System.out.println("---- My Instructions size is: "+curRoute.getRoute().size()+"----");
		System.out.println("---------------------------------");
		return curRoute;
	}

	public Router getCurRoute() {
		return curRoute;
	}


}
