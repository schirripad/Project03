package Simulation.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Simulation.Address;
import Simulation.SandwichTruck;
import Simulation.StreetDirection;

public class GridPanel extends JPanel {
	// 20 Lines, 17 total spaces
	private final int NUM_SPACES = 17;
	private SandwichTruck t;
	public int lineDistance = 1;

	public GridPanel(SandwichTruck truck) {
		t = truck;
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		drawGrid(g);
		drawTruck(g);
		drawRoute(g);
	}

	private void drawGrid(Graphics2D g) {
		// Get distance between each line
		// Implement scaling in MapWindow? Make sure GridPanel is always a square
		// relative to size of window
		if (getWidth() < getHeight())
			lineDistance = this.getWidth() / 18;
		else
			lineDistance = this.getHeight() / 18;
		for (int i = 0; i < 20; i++) {
			int currentXY = i * lineDistance;
			g.drawLine(currentXY, 0, currentXY, getHeight());
			g.drawLine(0, currentXY, getWidth(), currentXY);
		}
	}

	private void drawTruck(Graphics2D g) {
		Address truckAddress = t.getCurrentAddress();
		Color c = g.getColor();
		g.setColor(Color.RED);

		int houseDistance = lineDistance / 9;
		int blockX, blockY;
		if (truckAddress.getStreetDirection() == StreetDirection.EAST) {
			// Y value is street number, house is X
			blockY = truckAddress.getStreetNumber() * lineDistance;
			blockX = ((truckAddress.getHouseNumber() / 100) * lineDistance)
					- ((truckAddress.getHouseNumber() % 100) * houseDistance);
		} else {
			// X value is street number, house is Y
			blockX = truckAddress.getStreetNumber() * lineDistance;
			blockY = ((truckAddress.getHouseNumber() / 100) * lineDistance)
					- ((truckAddress.getHouseNumber() % 100) * houseDistance);
		}

		g.fillOval(blockX, blockY, 10, 10);
		g.setColor(c);

	}

	private void drawRoute(Graphics2D g) {

	}
}
