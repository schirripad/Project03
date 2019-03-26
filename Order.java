package Simulation;

import java.time.LocalTime;

/**
 * Contains data representing a customers order, including their addressm, and
 * the time that the order was placed
 * 
 * @author Daniel
 *
 */
public class Order implements Comparable<Order> {
	private Address address;
	private LocalTime time;
	private Food[] food;

	@Deprecated
	public Order(Address address, LocalTime t) {
		this.address = address;
		this.time = t;
	}

	public Order(Address address, LocalTime t, Food... food) {
		this.address = address;
		this.time = t;
		this.food = food;
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
