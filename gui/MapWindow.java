package Simulation.gui;

import javax.swing.JFrame;

import Simulation.SandwichTruck;

public class MapWindow extends JFrame {

	public MapWindow(SandwichTruck truck) {
		setTitle("Sandwich Truck Simulation");
		add(new GridPanel(truck));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);
	}
}
