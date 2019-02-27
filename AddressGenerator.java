package Simulation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class AddressGenerator {
	private static int blockNum, count, houseNumber, streetDir, streetNumber;

	public static void generateAddresses() throws IOException {
		PrintWriter out = new PrintWriter(new File("addresses.txt"));
		Random rand = new Random();

		while (count < 100) {
			for (int i = 0; i < 100; i++) {

				StringBuilder strBuilder = new StringBuilder();

				blockNum = rand.nextInt((200 - 1) + 1);
				// Minor bug fix of above ^ where we weren't getting a block num if random num
				// was 100 DH
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
				out.print(strBuilder);

				count++;
				out.println();

			}

		}
		out.close();
	}

}
