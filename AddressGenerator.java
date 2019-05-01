package Simulation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Creates random sets of Orders and exports them to a file entitled
 * 'addresses.txt'
 * 
 * @author Dylan, Riyad
 *
 */
public class AddressGenerator {
	private static int blockNum, count, houseNumber, streetDir, streetNumber, randomHour, randomMinute, convertedTime,
			randomChip, randomDrink, randomSandwich;

	//public static int howManyAdrs;

	public static void generateAddresses(int bound,int howManyAddress) throws IOException {
		PrintWriter out = new PrintWriter(new File("addresses.txt"));
		Random rand = new Random();

		while (count < howManyAddress) {
			for (int i = 0; i < howManyAddress; i++) {

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
				//System.out.println(houseNumber);
				//Generates the house number for the address DH

				if ((houseNumber % 100) != 0 && houseNumber != 1000) {
					strBuilder.append(houseNumber);
				} else {
					houseNumber = houseNumber + 10;
					strBuilder.append(houseNumber);
				}

				//If house number dived by 100 does not equal zero and does not equal 1000 then append it, we do not want
				//houses on street corners DH

				streetDir = rand.nextInt((2 - 1) + 1);
				// System.out.println(southEastNum);
				if (streetDir == 0)
					strBuilder.append(" South");
				else
					strBuilder.append(" East");

				//Simply makes it random South and random East DH

				streetNumber = rand.nextInt((bound - 1) + 1);
				if (streetNumber == 0)
					streetNumber += 1;
				// System.out.println(streetNumber);
				strBuilder.append(" " + streetNumber + " Street");

				//Gives you a random street number between 1 and 20 DH

				if (randomHour <= 11) {
					if (randomMinute < 10)
						strBuilder.append(" " + randomHour + ":0" + randomMinute + "AM");
					else
						strBuilder.append(" " + randomHour + ":" + randomMinute + "AM");
				}

				//Gives you a random hour between 1 and 11 am

				else if (randomHour == 12) {

					if (randomMinute < 10)
						strBuilder.append(" " + randomHour + ":0" + randomMinute + "PM");
					else
						strBuilder.append(" " + randomHour + ":" + randomMinute + "PM");
				}

				//At noon we switch to PM designation

				else {
					convertedTime = randomHour - 12;
					// System.out.println(convertedTime);
					if (randomMinute < 10)
						strBuilder.append(" " + convertedTime + ":0" + randomMinute + "PM");
					else
						strBuilder.append(" " + convertedTime + ":" + randomMinute + "PM");

				}

				//Gives us random afternoon hours converted to normal 12 hour time

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

				// Gives us a random order, of chip 1 thru 3, sandwich 1 thru 3, and random drink 1 thru 3.

				out.print(strBuilder);



				count++;
				//Breaks the while loop when it reaches 100 random addresses DH
				out.println();

			}

		}
		out.close();
	}
}
