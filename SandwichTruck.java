package Simulation;

import java.util.PriorityQueue;

import Simulation.gui.MapWindow;

public class SandwichTruck {
	public static final Address distribtutionCenter = new Address(910, 9, StreetDirection.SOUTH);

	private Address curAddress;
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

	public Order getNextOrder() {
		return orders.peek();
	}

	public void showAreaMap() {

		MapWindow mw = new MapWindow(this);

	}

}
