package Simulation.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

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

		Timer repaintTimer = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grid.repaint();
			}
		});

		repaintTimer.setDelay(1000);
		repaintTimer.start();
	}
}
