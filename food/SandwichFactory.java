package Simulation.food;

import Simulation.food.toppings.chips.PotatoChips;
import Simulation.food.toppings.chips.Pretzels;
import Simulation.food.toppings.condiments.Cheese;
import Simulation.food.toppings.condiments.Mayo;
import Simulation.food.toppings.condiments.Mustard;
import Simulation.food.toppings.drinks.Coke;
import Simulation.food.toppings.drinks.DietCoke;
import Simulation.food.toppings.drinks.Water;
import Simulation.food.toppings.extras.Bag;
import Simulation.food.toppings.extras.Cover;
import Simulation.food.toppings.meat.Ham;
import Simulation.food.toppings.meat.Turkey;
import Simulation.food.toppings.veggies.Lettuce;
import Simulation.food.toppings.veggies.Tomato;

/**
 * <p>
 * Sandwich factory generates a Sandwich object using a String input in the form
 * of
 * </p>
 * <p>
 * [bread]:M[meat]:C[condiment]:V[veggie]:D[drink]:H[chips]
 * </p>
 * <p>
 * Where meat is
 * <p>
 * 1: Turkey 2: Ham 3: Turkey & Ham
 * </p>
 * <p>
 * condiment is
 * </p>
 * <p>
 * 1: No condiments 2: Mayonnaise 3: Cheese 4: Mustard 5: Mayo & Cheese 6: Mayo
 * & Mustard 7: Cheese & Mustard 8: Mayo, Cheese, & Mustard
 * </p>
 * <p>
 * veggie is
 * </p>
 * <p>
 * 1: No veggies 2: Lettuce 3: Tomato 4: Lettuce & Tomato
 * </p>
 * <p>
 * drink is
 * </p>
 * <p>
 * 1: No drinks 2: Coca-Cola 3: Diet Coca-Cola 4: Bottled Water
 * </p>
 * <p>
 * And chips is
 * </p>
 * <p>
 * 1: No Chips 2: Potato Chips 3: Pretzels
 * </p>
 * 
 * @author Dan
 *
 */
public class SandwichFactory {

	public Sandwich createSandwich(String type) {
		String[] parts = type.split(":");
		Sandwich sandwich = null;
		if (parts[0].equals("1")) {
			sandwich = new SandwichOnWrap();
		} else if (parts[0].equals("2")) {
			sandwich = new SandwichOnRoll();
		}
		for (String s : parts) {
			s = s.trim();
			switch (s) {
			case "C3":
				sandwich.addTopping(new Cheese());
				break;
			case "C2":
				sandwich.addTopping(new Mayo());
				break;
			case "C4":
				sandwich.addTopping(new Mustard());
				break;
			case "C5":
				sandwich.addTopping(new Mayo());
				sandwich.addTopping(new Cheese());
				break;
			case "C6":
				sandwich.addTopping(new Mayo());
				sandwich.addTopping(new Mustard());
				break;
			case "C7":
				sandwich.addTopping(new Cheese());
				sandwich.addTopping(new Mustard());
				break;
			case "C8":
				sandwich.addTopping(new Cheese());
				sandwich.addTopping(new Mustard());
				sandwich.addTopping(new Mayo());
				break;
			case "M3":
				sandwich.addTopping(new Ham());
				sandwich.addTopping(new Turkey());
			case "M2":
				sandwich.addTopping(new Ham());
				break;
			case "M1":
				sandwich.addTopping(new Turkey());
				break;
			case "V2":
				sandwich.addTopping(new Lettuce());
				break;
			case "V3":
				sandwich.addTopping(new Tomato());
				break;
			case "V4":
				sandwich.addTopping(new Lettuce());
				sandwich.addTopping(new Tomato());
				break;
			case "D2":
				sandwich.addTopping(new Coke());
				break;
			case "D3":
				sandwich.addTopping(new DietCoke());
				break;
			case "D4":
				sandwich.addTopping(new Water());
				break;
			case "H2":
				sandwich.addTopping(new PotatoChips());
				break;
			case "H3":
				sandwich.addTopping(new Pretzels());
				break;
			default:
				break;
			}
		}
		sandwich.addTopping(new Bag());
		sandwich.addTopping(new Cover());
		return sandwich;
	}

}
