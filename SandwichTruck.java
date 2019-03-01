package Simulation;

import java.awt.Dimension;
import java.util.PriorityQueue;

import javax.swing.JFrame;

public class SandwichTruck {
	public static final Address distribtutionCenter = new Address(910, 9, StreetDirection.SOUTH);

	public Address curAddress;
	public PriorityQueue<Address> targets = new PriorityQueue<Address>();
	public static String baseMap = "";

	public SandwichTruck(int addressNum, int streetNum, StreetDirection streetDir) {
		curAddress = new Address(addressNum, streetNum, streetDir);
	}

	public Address getCurrentAddress() {
		return curAddress;
	}
	
	public void showAreaMap() {
		
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setPreferredSize(new Dimension(400, 400));
        frame.add(new MapGraphics(this, 400));
        
        frame.pack();
        frame.setVisible(true);
		
	}

}
