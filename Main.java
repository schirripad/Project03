package Simulation;

import java.io.IOException;
import java.util.PriorityQueue;

import Simulation.gui.MapWindow;

/**
 * 
 * @author Daniel, Dylan, Rio
 *
 */
public class Main {

	// TODO Generate jar file to upload to github
	// TODO Add class headers

	public static void main(String[] args) {
		SandwichTruck t = new SandwichTruck(SandwichTruck.distribtutionCenter);
		try {
			AddressGenerator.generateAddresses();

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
