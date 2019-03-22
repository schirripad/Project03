package Simulation.gui;

import javax.swing.JFrame;

import Simulation.SandwichTruck;

public class MapWindow extends JFrame {

	public MapWindow(SandwichTruck truck) {
		setSize(400, 400);
		setTitle("Sandwich Truck Simulation");
		GridPanel grid = new GridPanel(truck);
		grid.setSize(400, 400);
		add(grid);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
