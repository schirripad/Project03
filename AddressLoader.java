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
			// TODO Parse line, enter into 'addresses'
		}

		// Clean Up
		fileIn.close();
		sc.close();
		fileIn = null;
		sc = null;

		return addresses;
	}

}
