package Simulation;

import java.util.Collection;
import java.util.List;

/**
 * Interface used to define routing strategies
 * 
 * @author Daniel
 *
 *         add getTruckHeading method
 * @author Riyad
 *
 */
public interface Router {

	public void createRoute(Collection<Order> orders, SandwichTruck t);

	public double getRouteLength();

	public List<Instruction> getRoute();

	public void removeFirstInstruction();

	public int getTruckHeading();

}
