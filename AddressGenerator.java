package Simulation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * 
 * @author Dylan
 *
 */
public class AddressGenerator {
	private static int blockNum, count, houseNumber, streetDir, streetNumber, randomHour, randomMinute, convertedTime,
			randomChip, randomDrink, randomSandwich;

	public static void generateAddresses(int bound) throws IOException {
		PrintWriter out = new PrintWriter(new File("addresses.txt"));
		Random rand = new Random();

		while (count < 100) {
			for (int i = 0; i < 100; i++) {

				StringBuilder strBuilder = new StringBuilder();

				randomHour = rand.nextInt((18 - 10) + 1) + 10;
				randomMinute = rand.nextInt((59 - 0) + 1) + 0;
				randomChip = rand.nextInt((2 - 0) + 1) + 1;
				randomDrink = rand.nextInt((2 - 0) + 1) + 1;
				randomSandwich = rand.nextInt((2 - 0) + 1) + 1;

				blockNum = rand.nextInt(((bound * 10) - 1) + 1);
				if (blockNum == 0) {
					blockNum += 1;
				}
				// System.out.println(blockNum);
				houseNumber = (blockNum * 10);
				System.out.println(houseNumber);

				if ((houseNumber % 100) != 0 && houseNumber != 1000) {
					strBuilder.append(houseNumber);
				} else {
					houseNumber = houseNumber + 10;
					strBuilder.append(houseNumber);
				}

				streetDir = rand.nextInt((2 - 1) + 1);
				// System.out.println(southEastNum);
				if (streetDir == 0)
					strBuilder.append(" South");
				else
					strBuilder.append(" East");

				streetNumber = rand.nextInt((bound - 1) + 1);
				if (streetNumber == 0)
					streetNumber += 1;
				// System.out.println(streetNumber);
				strBuilder.append(" " + streetNumber + " Street");

				if (randomHour <= 11) {
					if (randomMinute < 10)
						strBuilder.append(" " + randomHour + ":0" + randomMinute + "AM");
					else
						strBuilder.append(" " + randomHour + ":" + randomMinute + "AM");
				}

				else if (randomHour == 12) {

					if (randomMinute < 10)
						strBuilder.append(" " + randomHour + ":0" + randomMinute + "PM");
					else
						strBuilder.append(" " + randomHour + ":" + randomMinute + "PM");
				}

				else {
					convertedTime = randomHour - 12;
					// System.out.println(convertedTime);
					if (randomMinute < 10)
						strBuilder.append(" " + convertedTime + ":0" + randomMinute + "PM");
					else
						strBuilder.append(" " + convertedTime + ":" + randomMinute + "PM");

				}

				if (randomChip == 1) {
					strBuilder.append(" Order: Chip: " + randomChip);
				}

				else if (randomChip == 2) {
					strBuilder.append(" Order: Chip: " + randomChip);
				}

				else {
					strBuilder.append(" Order: Chip: " + randomChip);
				}

				if (randomDrink == 1) {
					strBuilder.append(" Drink: " + randomDrink);
				}

				else if (randomDrink == 2) {
					strBuilder.append(" Drink: " + randomDrink);
				}

				else {
					strBuilder.append(" Drink: " + randomDrink);
				}

				if (randomSandwich == 1) {
					strBuilder.append(" Sandwich: " + randomSandwich);
				}

				else if (randomSandwich == 2) {
					strBuilder.append(" Sandwich: " + randomSandwich);
				}

				else {
					strBuilder.append(" Sandwich: " + randomSandwich);
				}

				// sort of spaghetti code, but it works ^ DH

				out.print(strBuilder);

				count++;
				out.println();

			}

		}
		out.close();
	}
}
