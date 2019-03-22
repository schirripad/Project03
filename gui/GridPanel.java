package Simulation.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Simulation.Address;
import Simulation.SandwichTruck;
import Simulation.StreetDirection;

public class GridPanel extends JPanel {
	private static final long serialVersionUID = 1L;
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
		// drawRoute(g);
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
		// Store current address
		Address truckAddress = t.getCurrentAddress();
		// Store original graphics color, set to Red
		Color c = g.getColor();
		g.setColor(Color.RED);

		// Get trucks x and y
		Dimension curXY = getTruckXY();
		int blockX = (int) curXY.getWidth();
		int blockY = (int) curXY.getHeight();
		g.fillOval(blockX, blockY, 5, 5);
		g.setColor(c);

	}

	private Dimension getTruckXY() {
		// Nine houses on a block, so in between two streets there are nine available
		// spots
		int houseDistance = lineDistance / 9;

		Address truckAddress = t.getCurrentAddress();
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

		return new Dimension(blockX, blockY);
	}

	private void drawRoute(Graphics2D g) {
		// Set Color, change to blue
		Color c = g.getColor();
		g.setColor(Color.BLUE);

		// Store trucks current address
		Dimension cur = getTruckXY();
		int tX = (int) cur.getWidth();
		int tY = (int) cur.getHeight();
		// Draw Route based on instructions, EC, go a certain distance, turn right, go a
		// certain distance, turn left
		// for(/*Instruction i : instructions*/) {
		// Deal with instruction
		/*
		 * if (i.getDirection() == Directions.LEFT || i.getDirection() ==
		 * Directions.RIGHT) { g.drawLine(tX, tY, tX + i.getDistance(), tY); } else {
		 * g.drawLine(tX, tY, tX, tY + i.getDistance()); } // if(/*Turn) { // Move in
		 * that direction by 1 unit, decrement instruction by 1 unit, push back // to
		 * top of list // Adjust Truck address to match where it currently is
		 * 
		 * } // } // }
		 */
	}
}
