package Simulation;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;

public class Main {
	
    public static void main(String[] args) {
    	
    	SandwichTruck truck = new SandwichTruck(900, 9, StreetDirection.EAST);
    	
    	try {
    		
			AddressGenerator.generateAddresses();
			truck.targets = AddressLoader.loadAddresses("addresses.txt");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	        
        truck.showAreaMap();
        
    }
}