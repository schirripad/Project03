package Simulation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class AddressGenerator {
	private static int blockNum, count, houseNumber, streetDir, streetNumber, randomHour, randomMinute, convertedTime, randomChip, randomDrink, randomSandwich;

	public static void generateAddresses() throws IOException {
		PrintWriter out = new PrintWriter(new File("addresses.txt"));
		Random rand = new Random();

		while (count < 100) {
			for (int i = 0; i < 100; i++) {

				StringBuilder strBuilder = new StringBuilder();

				blockNum = rand.nextInt((200 - 1) + 1);
				randomHour = rand.nextInt((18 - 10) + 1) + 10;
				randomMinute = rand.nextInt((59 - 0) + 1) + 0;
				// Creating Random Hours and Random Minutes < -- DH

				// System.out.println(blockNum);
				houseNumber = (blockNum * 10);
				if ((houseNumber / 10) != 0 && houseNumber != 1000) {
					strBuilder.append(houseNumber);
				}

				streetDir = rand.nextInt((2 - 1) + 1);
				// System.out.println(southEastNum);
				if (streetDir == 0)
					strBuilder.append(" South");
				else
					strBuilder.append(" East");

				streetNumber = rand.nextInt((20 - 1) + 1);
				strBuilder.append(" " + streetNumber + " Street");

				if (randomHour <= 11) {
					if (randomMinute < 10)
						strBuilder.append(" " + randomHour + ":0" + randomMinute + "AM");
					else
						strBuilder.append(" " + randomHour + ":" + randomMinute + "AM");
				}

				// Above Code ^ if less than 11 for hour, it stays in the AM form <-- DH

				else if (randomHour == 12) {

					if (randomMinute < 10)
						strBuilder.append(" " + randomHour + ":0" + randomMinute + "PM");
					else
						strBuilder.append(" " + randomHour + ":" + randomMinute + "PM");
				}

				// Above Code ^ if 12 is the hour, it appends PM form for afternoon <-- DH

				else {
					convertedTime = randomHour - 12;
					// Debug line >
					// System.out.println(convertedTime);
					if (randomMinute < 10)
						strBuilder.append(" " + convertedTime + ":0" + randomMinute + "PM");
					else
						strBuilder.append(" " + convertedTime + ":" + randomMinute + "PM");

				}

				// Above Code ^ if greater than 12 for the hour, it converts time to the 12 hour
				// PM form for afternoon <-- DH

				if(randomChip == 1){
					strBuilder.append(" Order: Chip: " + randomChip);
				}

				else if (randomChip == 2){
					strBuilder.append(" Order: Chip: " + randomChip);
				}

				else{
					strBuilder.append(" Order: Chip: " + randomChip);
				}

				if(randomDrink == 1){
					strBuilder.append(" Drink: " + randomDrink);
				}

				else if (randomDrink == 2){
					strBuilder.append(" Drink: " + randomDrink);
				}

				else{
					strBuilder.append(" Drink: " + randomDrink);
				}

				if(randomSandwich == 1){
					strBuilder.append(" Sandwich: " + randomSandwich);
				}

				else if (randomSandwich == 2){
					strBuilder.append(" Sandwich: " + randomSandwich);
				}

				else{
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
