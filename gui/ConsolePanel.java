package Simulation.gui;

import java.time.LocalTime;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Simulation.Address;
import Simulation.Instruction;
import Simulation.Observer;

public class ConsolePanel extends JPanel implements Observer {
	private JTextArea console;

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
		int hour = t.getHour();
		line = line + " @ time " + hour + ":";
		int min = t.getMinute();
		int sec = t.getSecond();
		if (min < 10) {
			line = line + "0" + min + ":";
		} else {
			line = line + min + ":";
		}
		if (sec < 10) {
			line = line + "0" + sec;
		} else {
			line = line + sec;
		}
		console.append(line + "\n");
	}

}
