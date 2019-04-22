package Simulation.gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import Simulation.Clock;
import Simulation.SandwichTruck;

/**
 * Creates JFrame, adds GridPanel
 * 
 * @author Daniel
 */
public class MapWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SandwichTruck truck;
	private Rectangle hoodSize;

	public MapWindow(SandwichTruck truck) {
		this.truck = truck;
		this.hoodSize = new Rectangle(20, 20);
		init();
	}

	public MapWindow(SandwichTruck truck, Rectangle neighborHoodSize) {
		this.truck = truck;
		this.hoodSize = neighborHoodSize;
		init();
	}

	private void init() {
		setSize(400, 400);
		setTitle("Sandwich Truck Simulation");
		GridPanel grid = new GridPanel(truck, hoodSize);
		grid.setSize(400, 400);
		grid.setDoubleBuffered(true);
		add(grid);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		Timer repaintTimer = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grid.repaint();
				Clock.updateTime();
			}
		});

		repaintTimer.setDelay(5);
		repaintTimer.start();
	}
}
