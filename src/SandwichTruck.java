package Simulation;

import java.util.PriorityQueue;

public class SandwichTruck {
	
	public int east;
	public int south;
	public PriorityQueue<Address> targets = new PriorityQueue<Address>();
	
	public SandwichTruck(int east, int south) {
		
		this.east = east;
		this.south = south;
		
	}
	
}
