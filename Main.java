package Simulation;

import java.awt.Rectangle;
import java.io.IOException;
import java.time.LocalTime;
import java.util.PriorityQueue;

/**
 * 
 * @author Daniel, Dylan, Rio
 *
 */
public class Main {

	// TODO Generate jar file to upload to github
	// TODO Add class headers
	// TODO Fix the Rectangle (Neighborhood size)

	public static void main(String[] args) {
		// Make sure to change the distributionCenter in SandwichTruck class to be the same.
		SandwichTruck t = new SandwichTruck(new Address(510, 5, StreetDirection.EAST));
		t.setNeighborHoodSize(new Rectangle(10, 10));
		try {

			// set how many address!
			AddressGenerator.generateAddresses(10,3);

			PriorityQueue<Order> orders = AddressLoader.loadOrders("addresses.txt");
			for (Order a : orders) {
				//System.out.println(a.getAddress().toString() + " at time " + a.getTime().toString());
				t.addOrder(a);
			}
			
			// The sandwich truck return to the distribution center after all deliveries are completed.
			t.addOrder(new Order(SandwichTruck.distributionCenter, LocalTime.of(19,00,00)));
			t.showAreaMap();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
