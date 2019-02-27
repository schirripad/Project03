package Simulation;

import java.util.Random;
import java.util.*;
import java.io.*;

public class Address implements Comparable<Address> {

	private int houseNumber, streetNumber, blockNum, count;
	private StreetDirection streetDir;

	public Address(int houseNumber, int streetNum, StreetDirection street) {
		this.houseNumber = houseNumber;
		this.streetNumber = streetNum;
		this.streetDir = street;
	}

	public void RandomAddress(int houseNumber, int streetNumber, int streetDir) throws IOException{
        PrintWriter out = new PrintWriter(new File("addresses.txt"));
        Random rand = new Random();

        while(count<100)
        {
            for(int i = 0; i < 100; i++){

                StringBuilder strBuilder = new StringBuilder();

                blockNum = rand.nextInt((100 - 1) + 1 );
                //Minor bug fix of above ^ where we weren't getting a block num if random num was 100 DH
                //System.out.println(blockNum);
                houseNumber = (blockNum * 10);
                if((houseNumber / 10) != 0 && houseNumber != 1000){
                    strBuilder.append(houseNumber);
                }

                streetDir = rand.nextInt((2-1) + 1);
                //System.out.println(southEastNum);
                if(streetDir == 0)
                    strBuilder.append(" South");
                else
                    strBuilder.append(" East");




                streetNumber = rand.nextInt((20 - 1) + 1);
                strBuilder.append(" "+streetNumber+" Street");
                out.print(strBuilder);



                count++;
                out.println();

            }

        }
        out.close();
    }


	public int distanceFrom(Address a) {
		// Create address dictionary
		int hNum = a.getHouseNumber();
		int sNum = a.getStreetNumber();
		StreetDirection sDir = a.getStreetDirection();
		// Compute distance
		// If houses are on same street, simply subtract housenumbers
		if (sDir == streetDir && sNum == streetNumber) {
			return Math.abs(hNum - houseNumber);
		} else {
			// Otherwise calculate distance
			if (streetDir == sDir) {
				int xDis = Math.abs(hNum - houseNumber);
				int yDis = Math.abs((sNum * 100) - (streetNumber * 100));
				return xDis + yDis;
			} else {
				int xDis = Math.abs(hNum - (streetNumber * 100));
				int yDis = Math.abs((sNum * 100) - houseNumber);
				return xDis + yDis;
			}
		}
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public StreetDirection getStreetDirection() {
		return streetDir;
	}

	@Override
	public int compareTo(Address a) {
		int aDis, thisDis;
		aDis = a.distanceFrom(SandwichTruck.distribtutionCenter);
		thisDis = this.distanceFrom(SandwichTruck.distribtutionCenter);
		if (thisDis > aDis) {
			return 1;
		} else if (thisDis < aDis)
			return -1;
		return 0;
	}

}
