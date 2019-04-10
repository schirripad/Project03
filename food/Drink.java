package Simulation.food;

import Simulation.Food;
/**
 * 
 * @author Daniel
 *
 */
public class Drink extends Food{
	private final int TYPE_1 = 0, TYPE_2 = 1, TYPE_3 = 3;
	private int type;

	public Drink(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}
