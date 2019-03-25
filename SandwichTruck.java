package Simulation;

import java.util.PriorityQueue;

import Simulation.gui.MapWindow;

public class SandwichTruck {
	public static final Address distribtutionCenter = new Address(910, 9, StreetDirection.SOUTH);

	private Address curAddress;
	private RouteTo curRoute;
	private PriorityQueue<Order> orders = new PriorityQueue<Order>();

	public SandwichTruck(int addressNum, int streetNum, StreetDirection streetDir) {
		curAddress = new Address(addressNum, streetNum, streetDir);
	}

	public SandwichTruck(Address a) {
		curAddress = a;
	}

	public Address getCurrentAddress() {
		return curAddress;
	}

	public void addOrder(Order o) {
		orders.add(o);
	}

	public RouteTo nextRoute() {
		curRoute = new RouteTo(seeNextOrder().getAddress());
		System.out.println("Route: ");
		for (Address a : curRoute.getRoute()) {
			System.out.println(a.getHouseNumber() + " " + a.getStreetNumber() + "st");
		}
		return curRoute;
	}

	public Order getNextOrder() {
		return orders.poll();
	}

	public Address getNextRouteInstruction() {
		if (curRoute == null) {
			curRoute = new RouteTo(getNextOrder().getAddress());
		}
		Address next;
		if (curRoute.getRoute().size() == 1) {
			next = curRoute.getRoute().get(0);
			nextRoute();
		} else {
			next = curRoute.getRoute().get(0);
		}
		curRoute.removeFirstInstruction();
		return next;
	}

	public Address peekNextRouteInstruction() {
		if (curRoute == null) {
			curRoute = new RouteTo(getNextOrder().getAddress());
		}
		return curRoute.getRoute().get(0);
	}

	public void setAddress(Address a) {
		curAddress = a;
	}

	public Order seeNextOrder() {
		return orders.peek();
	}

	public void showAreaMap() {
		new MapWindow(this);
	}

}
