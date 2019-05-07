package Simulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import Simulation.food.Sandwich;
import Simulation.food.SandwichFactory;

/**
 * 
 * @author Daniel
 *
 */
public class AddressLoader {

	public static PriorityQueue<Order> loadOrders(String fileName) throws IOException {
		PriorityQueue<Order> orders = new PriorityQueue<Order>();
		File address = new File(fileName);

		// Check if file exists
		if (!address.exists() || address.isDirectory()) {
			throw new FileNotFoundException();
		}

		List<String> lines = loadMetaData(address);

		// Read file
		for (String curLine : lines) {
			// Split line at spaces
			String[] addressParts = curLine.split(" ");

			// If not all parts are accounted for, inform user and ignore line
			if (addressParts.length < 12)
				System.out.println("Invalid address found!\n> " + curLine);
			else {
				int houseNum;
				int streetNum;
				StreetDirection streetDir;

				try {
					// Read House Number, Street Number, and StreetDirection from addressParts
					houseNum = Integer.parseInt(addressParts[0]);
					streetNum = Integer.parseInt(addressParts[2]);
					if (addressParts[1].equals("South")) {
						streetDir = StreetDirection.SOUTH;
					} else
						streetDir = StreetDirection.EAST;

					// Parse time data
					int hour, min, numChips, numDrinks, numSandwich;
					String[] time = addressParts[4].split(":");
					try {
						hour = Integer.parseInt(time[0]);
						// Convert to military time
						if (time[1].endsWith("PM") && hour != 12) {
							hour += 12;
							if (hour == 24)
								hour = 0;
						}
						// Parse minute data, truncate AM/PM off of end
						min = Integer.parseInt(time[1].substring(0, time[1].length() - 2));

						// Parse Food data
						SandwichFactory sf = new SandwichFactory();
						ArrayList<Sandwich> sandwiches = new ArrayList<Sandwich>();
						for (int i = 5; i < addressParts.length; i++) {
							if (addressParts[i].equals("Order:")) {
								if (addressParts.length < i + 12) {
									System.out.println("Invalid order!");
									continue;
								}
								Sandwich s = sf.createSandwich(addressParts[i + 2] + ":M" + addressParts[i + 4] + ":C"
										+ addressParts[i + 6] + ":V" + addressParts[i + 8] + ":D" + addressParts[i + 10]
										+ ":H" + addressParts[i + 12]);
								System.out.println("Created sandwich:");
								System.out.println(s.toString() + " priced at $" + s.getTotalPrice() / 100 + "."
										+ s.getTotalPrice() % 100);
								sandwiches.add(s);
								i = i + 13;
							}
						}
						Sandwich[] wiches = new Sandwich[sandwiches.size()];
						sandwiches.toArray(wiches);
						orders.add(new Order(new Address(houseNum, streetNum, streetDir), LocalTime.of(hour, min),
								wiches));
					} catch (NumberFormatException e) {
						System.out.println("Invalid address found!\n>" + curLine);
						continue;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid address found!\n>" + curLine);
				}
			}
		}

		return orders;
	}

	private static List<String> loadMetaData(File f) throws IOException {
		// Get a stream to the file, wrap with Scanner for String manipulation
		FileInputStream fileIn = new FileInputStream(f);
		Scanner sc = new Scanner(fileIn);

		List<String> lines = new ArrayList<String>();

		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}

		// Clean Up
		fileIn.close();
		sc.close();
		fileIn = null;
		sc = null;

		return lines;

	}

}
