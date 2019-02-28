package Simulation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MapGraphics extends Canvas {
	
	public SandwichTruck parent;
	public int size = 400;
	double scale = 2.0;
	
	public MapGraphics(SandwichTruck truck, int size) {
		
		parent = truck;
		this.size = size;
		scale = size / 2000.0;
		
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLACK);

		for(int i=0; i<20; i++) {
			
			g.drawLine(0, (int) (i*100*scale), 400, (int) (i*100*scale));
			g.drawLine((int) (i*100*scale), 0, (int) (i*100*scale), 400);
			
		}
		
		g.setColor(Color.BLUE);
		
		Address truckAddress = parent.curAddress;
		int truckX = (int) (truckAddress.getHouseNumber() * scale);
		int truckY = (int) (truckAddress.getStreetNumber() * 100 * scale);
				
		if(truckAddress.getStreetDirection().equals(StreetDirection.SOUTH)) {
			
			int save = truckX;
			truckX = truckY;
			truckY = save;
			
		}
		
		g.fillOval(truckX, truckY, 3, 3);

		for(Address a : parent.targets) {

			g.setColor(new Color(255 - (255 * a.distanceFrom(truckAddress)) / 2000, 0, 0));
			int x = (int) (a.getHouseNumber() * scale);
			int y = (int) (a.getStreetNumber() * 100 * scale);
			
			if(a.getStreetDirection().equals(StreetDirection.SOUTH)) {
				
				int save = x;
				x = y;
				y = save;
				
			}
			
			g.fillOval(x, y, 3, 3);
			
		}
		
	}
	
}
