package Simulation.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.ImageIcon;

import Simulation.Clock;
import Simulation.SandwichTruck;

/**
 * Creates JFrame, adds GridPanel
 * 
 * @author Daniel ,
 *
 * add askUser method to ask the user which strategy will be used.
 * @author Riyad
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
		askUser();
		init();
	}

	public MapWindow(SandwichTruck truck, Rectangle neighborHoodSize) {
		this.truck = truck;
		this.hoodSize = neighborHoodSize;
		askUser();
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

		repaintTimer.setDelay(100);
		repaintTimer.start();
	}

	private void askUser() {

		String userInputStr;
		//int userInput;
		ImageIcon iconSch = new ImageIcon("Simulation/SchaperLogo2.png");

		while (true) {
//			userInputStr = JOptionPane.showInputDialog(null, "Choose a strategy movement for the truck: " +
//					" \n1: both left and right hand turns"+
//					"\n 2: only left hand turn", "Strategy Input", JOptionPane.QUESTION_MESSAGE);
			Object[] strategies = {"","both-hand turn", "left-hand turn"};
			userInputStr = (String) JOptionPane.showInputDialog(null,
					"Choose a strategy movement for the truck: ",
					"Select Strategy",
					JOptionPane.PLAIN_MESSAGE,
					iconSch,
					strategies,
					"");
			try {
				//userInput = Integer.parseInt(userInputStr);
				if (userInputStr.contains("both")){
					truck.selectedStrategy=1;
				}
				else if (userInputStr.contains("left")){
					truck.selectedStrategy=2;
				}
				else
					throw new RuntimeException("Not a valid choice!");
				break;
			}
//			catch(NumberFormatException ex) {
//				JOptionPane.showMessageDialog(null, "Choose a number!", "Error Dialog", JOptionPane.ERROR_MESSAGE);
//			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Dialog", JOptionPane.ERROR_MESSAGE);
			}
		}
		JOptionPane.showMessageDialog(null, "You chose a  " + userInputStr +" strategy \n Press OK to start delivering", "Result", JOptionPane.INFORMATION_MESSAGE,iconSch);
	}
}
