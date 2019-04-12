package Simulation;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 * 
 * @author Daniel, Dylan, Rio
 *
 */
public class Main {

	// TODO Generate jar file to upload to github
	// TODO Add class headers

	public static void main(String[] args) {
		SandwichTruck t = new SandwichTruck(new Address(910, 9, StreetDirection.SOUTH));
		t.setNeighborHoodSize(new Rectangle(20, 20));
		try {
			AddressGenerator.generateAddresses(20);

			PriorityQueue<Order> orders = AddressLoader.loadOrders("addresses.txt");
			for (Order a : orders) {
				System.out.println(a.getAddress().toString() + " at time " + a.getTime().toString());
				t.addOrder(a);
			}
			t.showAreaMap();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
