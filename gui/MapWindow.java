package Simulation.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Simulation.SandwichTruck;

/**
 * Creates JFrame, adds GridPanel
 * 
 * @author Daniel,
 *
 *         add askUser method to ask the user which strategy will be used.
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
		setSize(1000, 1000);
		setTitle("Sandwich Truck Simulation");
		JDesktopPane desktop = new JDesktopPane();
		GridPanel grid = new GridPanel(truck, hoodSize);
		grid.addObserver(truck);
		grid.setSize(400, 400);
		grid.setMaximumSize(new Dimension(400, 400));
		grid.setDoubleBuffered(true);
		grid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JInternalFrame intGrid = new JInternalFrame();
		intGrid.setSize(400, 420);
		intGrid.setResizable(true);
		intGrid.setLocation(500, 100);
		intGrid.setVisible(true);

		ConsolePanel cons = new ConsolePanel(grid);
		cons.setSize(300, 400);
		JPanel spacer = new JPanel();
		spacer.setSize(100, 100);
		JInternalFrame intCons = new JInternalFrame();
		intCons.add(spacer, BorderLayout.WEST);
		intCons.add(cons, BorderLayout.CENTER);
		intCons.add(spacer, BorderLayout.SOUTH);
		intCons.setSize(400, 400);
		intCons.setLocation(50, 100);
		intCons.setVisible(true);

		intGrid.add(grid);
		desktop.add(intGrid);
		desktop.add(intCons);
		add(desktop);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		Timer repaintTimer = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grid.repaint();
				desktop.repaint();
			}
		});

		repaintTimer.setDelay(100);
		repaintTimer.start();
	}

	private void askUser() {

		String userInputStr;
		// int userInput;
		ImageIcon iconSch = new ImageIcon("Simulation/SchaperLogo2.png");

		while (true) {
			Object[] strategies = { "both-hand turn", "left-hand turn" };
			userInputStr = (String) JOptionPane.showInputDialog(null, "Choose a strategy movement for the truck: ",
					"Select Strategy", JOptionPane.PLAIN_MESSAGE, iconSch, strategies, "");
			try {
				// userInput = Integer.parseInt(userInputStr);
				if (userInputStr.contains("both")) {
					truck.selectedStrategy = 1;
				} else if (userInputStr.contains("left")) {
					truck.selectedStrategy = 2;
				} else
					System.exit(0);
				break;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Dialog", JOptionPane.ERROR_MESSAGE);
			}
		}
		JOptionPane.showMessageDialog(null,
				"You chose a  " + userInputStr + " strategy \n Press OK to start delivering", "Result",
				JOptionPane.INFORMATION_MESSAGE, iconSch);
	}
}
