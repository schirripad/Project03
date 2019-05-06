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
			randomChip, randomDrink, randomSandwichNumber, randomMeat, randomBread, randomCondiment, randomVegetables;

	//public static int howManyAdrs;

	public static void generateAddresses(int bound,int howManyAddress) throws IOException {
		PrintWriter out = new PrintWriter(new File("addresses.txt"));
		Random rand = new Random();

		while (count < howManyAddress) {
			for (int i = 0; i < howManyAddress; i++) {

				StringBuilder strBuilder = new StringBuilder();

				randomHour = rand.nextInt((18 - 10) + 1) + 10;
				randomMinute = rand.nextInt((59 - 0) + 1) + 0;


                //Creates required random integers to randomize orders and create real world data DH

				blockNum = rand.nextInt(((bound * 10) - 1) + 1);
				if (blockNum == 0) {
					blockNum += 1;
				}
				houseNumber = (blockNum * 10);
				//Generates the house number for the address DH

				if ((houseNumber % 100) != 0 && houseNumber != 1000) {
                    randomSandwichNumber = rand.nextInt((8 - 0) + 1) + 1;
                    //Handles multiple orders per address random number 1 thru 9 DH
					strBuilder.append(houseNumber);
				} else {
					houseNumber = houseNumber + 10;
                    randomSandwichNumber = rand.nextInt((8 - 0) + 1) + 1;
                    //Handles multiple orders per address random number 1 thru 9 DH
					strBuilder.append(houseNumber);
				}

				//If house number dived by 100 does not equal zero and does not equal 1000 then append it, we do not want
				//houses on street corners DH

				streetDir = rand.nextInt((2 - 1) + 1);
				if (streetDir == 0)
					strBuilder.append(" South");
				else
					strBuilder.append(" East");

				//Simply makes it random South and random East DH

				streetNumber = rand.nextInt((bound - 1) + 1);
				if (streetNumber == 0)
					streetNumber += 1;
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

				for(int j = 0; j < randomSandwichNumber; j++){
					// THIS IS THE FOR LOOP FOR MULTIPLE ORDERS PER ADDRESS I HAVE IT AS 1 THRU 9

					randomChip = rand.nextInt((2 - 0) + 1) + 1;
					randomDrink = rand.nextInt((2 - 0) + 1) + 1;
					randomMeat = rand.nextInt((2 - 0) + 1) + 1;
					randomBread = rand.nextInt((1 - 0) + 1) + 1;
					randomCondiment = rand.nextInt((7 - 0) + 1) + 1;
					randomVegetables = rand.nextInt((3 - 0) + 1) + 1;
					// MOVED RANDOM INTEGERS DOWN HERE SO ORDERS DO NOT REPEAT!


					if(randomMeat == 1){
						strBuilder.append(" Order: Meat: " + randomMeat);
					}

					else if (randomMeat == 2){
						strBuilder.append(" Order: Meat: " + randomMeat);
					}

					else{
						strBuilder.append(" Order: Meat: " + randomMeat);
					}

                    /*
                    RANDOM MEAT GENERATES A RANDOM NUMBER 1 THRU 3
                    1. Means that the meat is Turkey
                    2. Means that the meat is Ham
                    3. Means that the meat is BOTH TURKEY AND HAM
                     */


					if(randomBread == 1){
						strBuilder.append(" Bread: " + randomBread);
					}

					else{
						strBuilder.append(" Bread: " + randomBread);
					}

                    /*
                    RANDOM BREAD GENERATES A RANDOM NUMBER 1 THRU 2
                    1. Means that the bread is a WRAP
                    2. Means that the bread is a ROLL

                    YOU CANNOT HAVE BOTH!
                     */

					if(randomCondiment == 1){
						strBuilder.append(" Condiment: " + randomCondiment);
					}

					else if (randomCondiment == 2){
						strBuilder.append(" Condiment: " + randomCondiment);
					}

					else if (randomCondiment == 3){
						strBuilder.append(" Condiment: " + randomCondiment);
					}

					else if (randomCondiment == 4){
						strBuilder.append(" Condiment: " + randomCondiment);
					}

					else if (randomCondiment == 5){
						strBuilder.append(" Condiment: " + randomCondiment);
					}

					else if (randomCondiment == 6){
						strBuilder.append(" Condiment: " + randomCondiment);
					}

					else if (randomCondiment == 7){
						strBuilder.append(" Condiment: " + randomCondiment);
					}

					else{
						strBuilder.append(" Condiment: " + randomCondiment);
					}

                    /*
                    RANDOM CONDIMENT GENERATES A RANDOM NUMBER 1 THRU 8 (SORRY!)
                    1. Means that the customer wants NO CONDIMENTS!
                    2. Means that the condiment is JUST MAYO
                    3. Means that the condiment is JUST CHEESE
                    4. Means that the condiment is JUST MUSTARD
                    5. Means that the condiment is MAYO AND CHEESE
                    6. Means that the condiment is MAYO AND MUSTARD
                    7. Means that the condiment is CHEESE AND MUSTARD
                    8. Means that the condiment is MAYO AND CHEESE AND MUSTARD
                     */

					if(randomVegetables == 1){
						strBuilder.append(" Vegetable: " + randomVegetables);
					}

					else if (randomVegetables == 2){
						strBuilder.append(" Vegetable: " + randomVegetables);
					}

					else if (randomVegetables == 3){
						strBuilder.append(" Vegetable: " + randomVegetables);

					}

					else{
						strBuilder.append(" Vegetable: " + randomVegetables);
					}

                    /*
                    RANDOM VEGETABLES GENERATE A RANDOM NUMBER 1 THRU 4
                    1. Means that the customer wants NO VEGETABLES
                    2. Means that the customer wants LETTUCE
                    3. Means that the customer wants TOMATO
                    4. Means that the customer wants LETTUCE AND TOMATO
                     */

					if(randomDrink == 1){
						strBuilder.append(" Drink: " + randomDrink);
					}

					else if (randomDrink == 2){
						strBuilder.append(" Drink: " + randomDrink);
					}

					else if (randomDrink == 3){
						strBuilder.append(" Drink: " + randomDrink);

					}

					else{
						strBuilder.append(" Drink: " + randomDrink);
					}

                    /*
                    RANDOM DRINK GENERATES A RANDOM NUMBER 1 THRU 4 <-- THIS IS EXTRA SO I ADDED PRICES TOO!
                    1. Means NO DRINK (COST $0.00)
                    2. Means Coca-Cola (COST $1.25)
                    3. Means Diet Coca-Cola (COST $1.25)
                    4. Means Bottled Water (COST $1.25)
                     */

					if(randomChip == 1){
						strBuilder.append(" Chip: " + randomChip);
					}

					else if (randomChip == 2){
						strBuilder.append(" Chip: " + randomChip);
					}

					else{
						strBuilder.append(" Chip: " + randomChip);
					}

                    /*
                    RANDOM CHIP GENERATES A RANDOM NUMBER 1 THRU 3 <-- THIS IS EXTRA SO I ADDED PRICES TOO!
                    1. Means NO CHIPS (COST $0.00)
                    2. Means Potato Chips (COST $0.50)
                    3. Means Pretzels (COST $0.50)
                    */




					// REFER TO ORDER CODES ABOVE TO SEE WHAT EACH NUMBER MEANS ^ DH

				}

				// Gives us a random order including multiple orders according to client specifications DH

				out.print(strBuilder);



				count++;
				//Breaks the while loop when it reaches 100 random addresses DH
				out.println();

			}

		}
		out.close();
	}
}
