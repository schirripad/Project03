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
import Simulation.RouteTo;
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
	int houseDistance = lineDistance / 9;
	private RouteTo route;
	private int distanceToNext = 0;
	private Dimension next;

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
		drawRouteEvenNewer(g);
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
		// Store original graphics color, set to Red
		Color c = g.getColor();
		g.setColor(Color.RED);

		// Get trucks x and y
		Dimension curXY = getAddressXY(t.getCurrentAddress());

		g.fillOval(curXY.width - 2, curXY.height - 2, 5, 5);
		g.setColor(c);

	}

	private Dimension getAddressXY(Address a) {
		// Nine houses on a block, so in between two streets there are nine available
		// spots
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

	private Address getXYAddress(Dimension d, StreetDirection s) {
		if (s == StreetDirection.EAST) {
			return new Address((d.width / (hoodWidth * houseDistance)), d.height / lineDistance, s);
		} else {
			
		}
	}

	private void drawRoute(Graphics2D g) {
		Address next = t.peekNextRouteInstruction();
		Address cur = t.getCurrentAddress();

		System.out.println(
				"Going to: " + next.getHouseNumber() + " " + next.getStreetDirection() + " " + next.getStreetNumber());

		System.out.println(
				"Coming from: " + cur.getHouseNumber() + " " + cur.getStreetDirection() + " " + cur.getStreetNumber());

		if (next.getHouseNumber() == cur.getHouseNumber() && cur.getStreetNumber() == next.getStreetNumber()
				&& next.getStreetDirection() == cur.getStreetDirection()) {
			next = t.getNextRouteInstruction();
		}

		if (next.getStreetDirection() == cur.getStreetDirection()) {
			if (next.getHouseNumber() > cur.getHouseNumber()) {
				// Add one to house number
				Address a = new Address(cur.getHouseNumber() + 1, cur.getStreetNumber(), cur.getStreetDirection());
				t.setAddress(a);
			} else if (next.getHouseNumber() < cur.getHouseNumber()) {
				// Subtract one
				Address a = new Address(cur.getHouseNumber() - 1, cur.getStreetNumber(), cur.getStreetDirection());
				t.setAddress(a);
			} else {
				Address a = new Address(cur.getStreetNumber() * 100 - 1, cur.getHouseNumber() / 100,
						next.getStreetDirection());
				t.setAddress(a);
			}
		} else {
			Address a = new Address(cur.getStreetNumber() * 100, cur.getHouseNumber() / 100,
					(cur.getStreetDirection() == StreetDirection.SOUTH) ? StreetDirection.EAST : StreetDirection.SOUTH);
			t.setAddress(a);
		}

		// TODO Use last intermediate address as turning point rather than calculating
		// new turning point
	}

	// Move truck based on XY coordinates rather than address
	public void drawRouteEvenNewer(Graphics2D g) {
		if (route == null || route.getRoute().isEmpty()) {
			route = t.nextRoute();
		}
		Dimension cur = getAddressXY(t.getCurrentAddress());
		Dimension intermediate = cur;
		if (distanceToNext == 0)
			next = getAddressXY(route.getRoute().get(0));

		System.out.println("(" + cur.width + "," + cur.height + ");(" + next.width + "," + next.height + ")");

		if (cur.width == next.width && cur.height == next.height) {
			// Next instruction
			distanceToNext = 0;
		} else if (cur.width == next.width) {
			distanceToNext = cur.height - next.height;
			if (distanceToNext > 0) {
				// Subtract one from height
				intermediate = new Dimension(cur.width, cur.height - 1);
			} else {
				// Add one to height
				intermediate = new Dimension(cur.width, cur.height + 1);
			}
		} else if (cur.height == next.height) {
			distanceToNext = cur.width - next.width;
			if (distanceToNext > 0) {
				// Subtract one from width
				intermediate = new Dimension(cur.width - 1, cur.height);
			} else {
				// Add one to width
				intermediate = new Dimension(cur.width + 1, cur.height);
			}
		} else {
			System.out.println(
					"INVALID ROUTE: (" + cur.width + "," + cur.height + ");(" + next.width + "," + next.height + ")");
		}
		if (intermediate.height == cur.height) {
			// Get Address from XY when height is street number
			
		} else if (intermediate.width == cur.width) {
			
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
