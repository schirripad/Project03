package Simulation;

import java.time.LocalTime;

public class Order implements Comparable<Order> {
	private Address address;
	private LocalTime time;

	public Order(Address address, LocalTime t) {
		this.address = address;
		this.time = t;
	}

	public LocalTime getTime() {
		return time;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public int compareTo(Order o) {
		return time.compareTo(o.getTime());
	}

}
