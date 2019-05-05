package Simulation.food;

import Simulation.food.toppings.condiments.Cheese;
import Simulation.food.toppings.condiments.Mayo;
import Simulation.food.toppings.condiments.Mustard;
import Simulation.food.toppings.extras.Bag;
import Simulation.food.toppings.extras.Cover;
import Simulation.food.toppings.meat.Ham;
import Simulation.food.toppings.meat.Turkey;
import Simulation.food.toppings.veggies.Lettuce;
import Simulation.food.toppings.veggies.Tomato;

public class SandwichFactory {

	public Sandwich createSandwich(String type) {
		String[] parts = type.split(":");
		Sandwich sandwich = null;
		if (parts[0].equals("wrap")) {
			sandwich = new SandwichOnWrap();
		} else if (parts[0].equals("roll")) {
			sandwich = new SandwichOnRoll();
		}
		for (String s : parts) {
			s = s.trim();
			switch (s) {
			case "cheese":
				sandwich.addTopping(new Cheese());
				break;
			case "mayo":
				sandwich.addTopping(new Mayo());
				break;
			case "mustard":
				sandwich.addTopping(new Mustard());
				break;
			case "ham":
				sandwich.addTopping(new Ham());
				break;
			case "turkey":
				sandwich.addTopping(new Turkey());
				break;
			case "lettuce":
				sandwich.addTopping(new Lettuce());
				break;
			case "tomato":
				sandwich.addTopping(new Tomato());
				break;
			case "bag":
				sandwich.addTopping(new Bag());
				break;
			case "cover":
				sandwich.addTopping(new Cover());
				break;
			}
		}
		return sandwich;
	}

}
