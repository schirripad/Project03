package Simulation;

import java.util.List;

/**
 * Interface used to define routing strategies
 * 
 * @author Daniel
 *
 * add getTruckHeading method
 * @author Riyad
 *
 */
public interface Router {

	public double getRouteLength();

	public List<Instruction> getRoute();

	public void removeFirstInstruction();

	public int getTruckHeading();

}
