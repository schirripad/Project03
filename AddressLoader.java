package Simulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AddressLoader {

	public static PriorityQueue<Address> loadAddresses(String fileName) throws IOException {
		PriorityQueue<Address> addresses = new PriorityQueue<Address>();
		File address = new File(fileName);

		// Check if file exists
		if (!address.exists() || address.isDirectory()) {
			throw new FileNotFoundException();
		}

		// Get a stream to the file, wrap with Scanner for String manipulation
		FileInputStream fileIn = new FileInputStream(address);
		Scanner sc = new Scanner(fileIn);

		// Read file
		String curLine;
		while (sc.hasNextLine()) {
			curLine = sc.nextLine();

			// Split line at spaces
			String[] addressParts = curLine.split("  ");

			// If not all parts are accounted for, inform user and ignore line
			if (addressParts.length < 4)
				System.out.println("Invalid address found!\n> " + curLine);
			else {
				int houseNum;
				int streetNum;
				StreetDirection streetDir;

				try {
					// Read House Number, Street Number, and StreetDirection from addressParts
					houseNum = Integer.parseInt(addressParts[0]);
					streetNum = Integer.parseInt(addressParts[2].substring(0, 1));
					if (addressParts[1].equals("South")) {
						streetDir = StreetDirection.SOUTH;
					} else
						streetDir = StreetDirection.EAST;

					// Create corresponding Address object, add it to 'addresses'
					addresses.add(new Address(houseNum, streetNum, streetDir));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}

		// Clean Up
		fileIn.close();
		sc.close();
		fileIn = null;
		sc = null;

		return addresses;
	}

}
