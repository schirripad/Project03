package Simulation;

import java.util.List;

/**
 * 
 * @author Daniel
 *
 */
public interface Router {

	public double getRouteLength();

	public List<Instruction> getRoute();

	public void removeFirstInstruction();

}
