package Simulation.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.PriorityQueue;

import javax.swing.JPanel;

import Simulation.Address;
import Simulation.Order;
import Simulation.SandwichTruck;
import Simulation.StreetDirection;

/**
 * Creates a grid that paints the truck and all addresses
 * 
 * @author Daniel
 * 
 */
public class GridPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	// 20 Lines, 17 total spaces
	private SandwichTruck t;
	private int hoodHeight = 20, hoodWidth = 20;
	private int lineDistance = 1;

	// TODO Make grid adjustable

	public GridPanel(SandwichTruck truck) {
		t = truck;
	}

	public GridPanel(SandwichTruck truck, Rectangle neighborHoodSize) {
		this.t = truck;
		hoodHeight = neighborHoodSize.height;
		hoodWidth = neighborHoodSize.width;
	}

	@Override
	public void setSize(int x, int y) {
		super.setSize(x, x);
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		drawGrid(g);
		drawAddresses(g);
		drawTruck(g);
		drawRoute(g);
	}

	private void drawGrid(Graphics2D g) {
		// Get distance between each line
		// Implement scaling in MapWindow? Make sure GridPanel is always a square
		// relative to size of window
		if (getWidth() < getHeight())
			lineDistance = this.getWidth() / (hoodWidth - 2);
		else
			lineDistance = this.getHeight() / (hoodHeight - 2);
		// Make XY Ambiguous
		for (int i = 0; i < hoodHeight; i++) {
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
		Dimension curXY = getAddressXY(t.getCurrentAddress());
		int blockX = (int) curXY.getWidth();
		int blockY = (int) curXY.getHeight();
		g.fillOval(blockX, blockY, 5, 5);
		g.setColor(c);

	}

	private Dimension getAddressXY(Address a) {
		// Nine houses on a block, so in between two streets there are nine available
		// spots
		int houseDistance = lineDistance / 9;
		int blockX, blockY;
		if (a.getStreetDirection() == StreetDirection.EAST) {
			// Y value is street number, house is X
			blockY = a.getStreetNumber() * lineDistance;
			blockX = ((a.getHouseNumber() / 100) * lineDistance) - ((a.getHouseNumber() % 100) * houseDistance);
		} else {
			// X value is street number, house is Y
			blockX = a.getStreetNumber() * lineDistance;
			blockY = ((a.getHouseNumber() / 100) * lineDistance) - ((a.getHouseNumber() % 100) * houseDistance);
		}

		return new Dimension(blockX - lineDistance, blockY - lineDistance);
	}

	private void drawRoute(Graphics2D g) {
		Address next = t.peekNextRouteInstruction();
		Address cur = t.getCurrentAddress();

		if (next.getHouseNumber() == cur.getHouseNumber() && cur.getStreetNumber() == next.getStreetNumber()
				&& next.getStreetDirection() == cur.getStreetDirection()) {
			next = t.getNextRouteInstruction();
		}

		if (next.getStreetDirection() == cur.getStreetDirection()) {
			if (next.getHouseNumber() > cur.getHouseNumber()) {
				// Add one to house number
				Address a = new Address(cur.getHouseNumber() + 1, cur.getStreetNumber(), cur.getStreetDirection());
				t.setAddress(a);
			} else {
				// Subtract one
				Address a = new Address(cur.getHouseNumber() - 1, cur.getStreetNumber(), cur.getStreetDirection());
				t.setAddress(a);
			}
		} else {
			Address a = new Address(cur.getStreetNumber() * 100, cur.getHouseNumber() / 100,
					(cur.getStreetDirection() == StreetDirection.SOUTH) ? StreetDirection.EAST : StreetDirection.SOUTH);
			t.setAddress(a);
		}
	}

	private void drawAddresses(Graphics2D g) {
		PriorityQueue<Order> orders = t.getAllOrders();
		for (Order o : orders) {
			Dimension xy = getAddressXY(o.getAddress());
			g.fillOval(xy.width, xy.height, 5, 5);
		}
	}
}
