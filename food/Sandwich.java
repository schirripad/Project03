package Simulation.food;

import java.util.ArrayList;

import Simulation.Food;

/**
 * 
 * @author Daniel
 *
 */
public abstract class Sandwich extends Food {
	private ArrayList<Topping> toppings = new ArrayList<Topping>();
	private int totalPrice = 0;

	protected abstract String getType();

	public void addTopping(Topping t) {
		toppings.add(t);
		totalPrice += t.getPriceInCents();
	}

	public int getTotalPrice() {

		return totalPrice + (totalPrice / 10);
	}

	public String toString() {
		String ret = getType() + " with ";
		for (Topping t : toppings) {
			ret = ret + t.toString() + ", ";
		}
		ret = ret.substring(0, ret.length() - 2);
		return ret;
	}
}
