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

import Simulation.food.Chip;
import Simulation.food.Drink;
import Simulation.food.Sandwich;

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
						numChips = Integer.parseInt(addressParts[7]);
						numDrinks = Integer.parseInt(addressParts[9]);
						numSandwich = Integer.parseInt(addressParts[11]);
					} catch (NumberFormatException e) {
						System.out.println("Invalid address found!\n>" + curLine);
						continue;
					}

					// Create corresponding Address object, add it to 'addresses'
					orders.add(new Order(new Address(houseNum, streetNum, streetDir), LocalTime.of(hour, min),
							new Chip(numChips), new Drink(numDrinks), new Sandwich(numSandwich)));
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
