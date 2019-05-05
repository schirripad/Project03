package Simulation.gui;

import java.time.LocalTime;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Simulation.Address;
import Simulation.Instruction;
import Simulation.Observer;

public class ConsolePanel extends JPanel implements Observer {
	JTextArea console;

	public ConsolePanel(GridPanel p) {
		p.addObserver(this);
		console = new JTextArea();
		console.setEditable(false);
		console.append("Welcome to Schapers Sandwich Simulation!\n----------------------------------------\n");
		console.setSize(this.getWidth() - 100, 400);
		JScrollPane scroller = new JScrollPane(console);
		scroller.setAutoscrolls(true);
		add(scroller);
	}

	@Override
	public void update(Instruction i) {
		Address a = i.getAddress();
		LocalTime t = i.getLocalTime();

		String line = "";
		if (a.getHouseNumber() % 100 == 0) {
			// Turning
			line = line + "Truck turning at " + a.toString();
		} else {
			// Moving
			line = line + "Truck moving to " + a.toString();
		}
		line = line + " @ time " + t.getHour() + ":" + t.getMinute() + ":" + t.getSecond();
		console.append(line + "\n");
	}

}
