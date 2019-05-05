package Simulation.food;

import java.util.ArrayList;

import Simulation.Food;

/**
 * 
 * @author Daniel
 *
 */
public class Sandwich extends Food {
	private ArrayList<Topping> toppings = new ArrayList<Topping>();
	private int totalPrice = 0;

	public void addTopping(Topping t) {
		toppings.add(t);
		totalPrice += t.getPriceInCents();
	}

	public int getTotalPrice() {
		return totalPrice;
	}
}
