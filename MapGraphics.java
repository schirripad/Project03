package Simulation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MapGraphics extends Canvas {
	
	public SandwichTruck parent;
	public int size = 400;
	int scale = 2;
	
	public MapGraphics(SandwichTruck truck, int size) {
		
		parent = truck;
		this.size = size;
		scale = size / 200;
		
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLACK);
		
		for(int i=0; i<20; i++) {
			
			g.drawLine(0, i*10*scale, 400, i*10*scale);
			g.drawLine(i*10*scale, 0, i*10*scale, 400);
			
		}
		
		g.setColor(Color.RED);
		
		for(Address a : parent.targets) {
			
			int x = a.getHouseNumber() * scale;
			int y = a.getStreetNumber() * 100 * scale;
			
			if(a.getStreetDirection().equals(StreetDirection.SOUTH)) {
				
				int save = x;
				x = y;
				y = save;
				
			}
			
			g.fillOval(x, y, 3, 3);
			
		}
		
		g.setColor(Color.BLUE);
		
		Address truckAddress = parent.curAddress;
		int truckX = truckAddress.getHouseNumber() * scale;
		int truckY = truckAddress.getStreetNumber() * 100 * scale;
		
		if(truckAddress.getStreetDirection().equals(StreetDirection.SOUTH)) {
			
			int save = truckX;
			truckX = truckY;
			truckY = save;
			
		}
		
		g.fillOval(truckX, truckY, 3, 3);
		
	}
	
}
