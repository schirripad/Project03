package Simulation;

import java.util.LinkedList;
import java.util.List;


/**
 *
 * Contains data representing instructions of routing between two addresses using only right-hand turn (truck current location and the address of the next order)
 * with the delaying units of right-hand turn, besides the total route length.
 *
 * @author Rio and Dylan
 *
 */
// Right hand
public class RouteToByRightHand implements Router{
    public List<Instruction> instructions ;
    public static double routeLength = 0;
    protected static int truckHeading = 0;  // 1; (←)South, 2; East(↓), 3; North(→), 4; West(↑)
    public int unitsofTime;
    public double timeUnits;
    Address truck;
    Address a ;

    RouteToByRightHand(Order o, SandwichTruck t){
        truck = t.getCurrentAddress();
        a = o.getAddress();
        unitsofTime = 0;

        /*
           test the code by setting a truck address and the address that truck going to
        */

        //truck = new Address(640,7,StreetDirection.SOUTH);
        //a = new Address(640,8,StreetDirection.SOUTH);

        if (truck==SandwichTruck.distributionCenter)
            truckHeading = 2;
        else
            truckHeading = t.getHeading();

        int hNum;
        int sNum;
        StreetDirection sDir;
        int truckHouseNum;
        int truckStreetNum;
        StreetDirection truckDir;
        instructions = new LinkedList<>();
        boolean sameDirection;
        boolean sameStreetNum = a.getStreetNumber() == truck.getStreetNumber();


        // Deal with the U-turn case in same street situation
        if (a.getStreetDirection() == truck.getStreetDirection() && sameStreetNum ){
            int corner = truck.getHouseNumber()- (truck.getHouseNumber() % 100);

            // Good!
            if ((truck.getStreetDirection() == StreetDirection.SOUTH && truckHeading == 1 && a.getHouseNumber() > truck.getHouseNumber()) ) {
                instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.EAST),2000));
                instructions.add(new Instruction(new Address(corner,truck.getStreetNumber()-1,StreetDirection.SOUTH),2000));
                routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 2;
                truck= new Address(corner,truck.getStreetNumber()-1,StreetDirection.SOUTH);
                truckHeading = 3;
            }
            // Good!
            else if ((truck.getStreetDirection() == StreetDirection.SOUTH && truckHeading == 3 && a.getHouseNumber() < truck.getHouseNumber())){
                instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,(corner+100)/100,StreetDirection.EAST),2000));
                instructions.add(new Instruction(new Address(corner+100,truck.getStreetNumber()+1,StreetDirection.SOUTH),2000));
                truckHeading = 1;
                routeLength += Math.abs(truck.getHouseNumber() - (corner+100));
                unitsofTime += 2;
                truck= new Address(corner+100,truck.getStreetNumber()+1,StreetDirection.SOUTH);
            }
            // Good!
            else if ((truck.getStreetDirection() == StreetDirection.EAST && truckHeading == 2 && a.getHouseNumber() < truck.getHouseNumber())){
                instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,(corner+100)/100,StreetDirection.SOUTH),2000));
                instructions.add(new Instruction(new Address(corner+100,truck.getStreetNumber()-1,StreetDirection.EAST),2000));
                truckHeading = 4;
                routeLength += Math.abs(truck.getHouseNumber() -(corner+100));
                unitsofTime += 2;
                truck= new Address(corner+100,truck.getStreetNumber()-1,StreetDirection.EAST);

            }
            // Good!
            else if ((truck.getStreetDirection() == StreetDirection.EAST && truckHeading == 4 && a.getHouseNumber() > truck.getHouseNumber())){
                instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.SOUTH),2000));
                instructions.add(new Instruction(new Address(corner,truck.getStreetNumber()+1,StreetDirection.EAST),2000));
                truckHeading = 2;
                routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 2;
                truck= new Address(corner,truck.getStreetNumber()+1,StreetDirection.EAST);
            }
            routeLength +=100;
        }


        if (truck.getStreetDirection()!= a.getStreetDirection() && truck.getStreetDirection()==StreetDirection.EAST){
            if(truckHeading ==4 && truck.getHouseNumber() - (truck.getHouseNumber() % 100) < a.getStreetNumber()*100){
                int corner= truck.getHouseNumber() - (truck.getHouseNumber() % 100);
                instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.SOUTH),2000));
                truckHeading=3;
                routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 2;
                truck =new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.SOUTH);
            }
            // Good! -- Good
            else if(truckHeading == 2 && truck.getHouseNumber() - ((truck.getHouseNumber()+100) % 100) > a.getStreetNumber()*100){

                int corner= (truck.getHouseNumber()) - (truck.getHouseNumber() % 100);
                instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,(corner+100)/100,StreetDirection.SOUTH),2000));
                truckHeading=1;
                routeLength += Math.abs(truck.getHouseNumber() -(corner+100));
                unitsofTime += 2;
                truck =new Address(truck.getStreetNumber()*100,(corner+100)/100,StreetDirection.SOUTH);
            }
        }
        // Good!
        if (truck.getStreetDirection()!= a.getStreetDirection() && truck.getStreetDirection()==StreetDirection.SOUTH){
            if(truckHeading ==3 && truck.getHouseNumber() - (truck.getHouseNumber() % 100) > a.getStreetNumber()*100){

                int corner= (truck.getHouseNumber()) - (truck.getHouseNumber() % 100);
                instructions.add(new Instruction(new Address((truck.getStreetNumber()*100),(corner+100)/100,StreetDirection.EAST),2000));
                truckHeading=2;
                routeLength += Math.abs(truck.getHouseNumber() - (corner+100));
                unitsofTime += 2;
                truck =new Address((truck.getStreetNumber()*100),(corner+100)/100,StreetDirection.EAST);
            }
            else if(truckHeading == 1 && truck.getHouseNumber() - (truck.getHouseNumber() % 100) < a.getStreetNumber()*100){

                int corner= (truck.getHouseNumber()) - (truck.getHouseNumber() % 100);
                instructions.add(new Instruction(new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.EAST),2000));
                truckHeading=4;
                routeLength += Math.abs(truck.getHouseNumber() -corner);
                unitsofTime += 2;
                truck =new Address(truck.getStreetNumber()*100,corner/100,StreetDirection.EAST);
            }
        }


        while (!(a.getStreetDirection().equals(truck.getStreetDirection()))|| (a.getStreetNumber()!=truck.getStreetNumber())){

            hNum = a.getHouseNumber();
            sNum = a.getStreetNumber();
            sDir = a.getStreetDirection();
            truckHouseNum = truck.getHouseNumber();
            truckStreetNum = truck.getStreetNumber();
            truckDir = truck.getStreetDirection();
            sameDirection = sDir == truckDir;


            if(sameDirection) {
                // more that two moves; eg:            *---
                //                                         |
                //                                         |
                // corner, so now it is in 2 moves case -> ()------*
                int corner;
                if (truckDir == StreetDirection.SOUTH ) {
                    // if Address in the right side
                    if (hNum > truckHouseNum) {
                        if (truckHeading == 1) { //(←)South

                            corner = truckHouseNum - (truckHouseNum % 100);
                            if (truckHouseNum%100==0)
                                corner -=100;
                            if (sNum<truckStreetNum){
                                instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),2000));
                                truckHeading = 4;
                            }
                            // working on
                            else {
                                instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),2000));
                                routeLength += Math.abs(truck.getHouseNumber() -corner);
                                unitsofTime += 2;
                                // house is near corner, still only making one right hand turn DH
                                instructions.add(new Instruction(new Address(corner,truckStreetNum-1, StreetDirection.SOUTH),2000));
                                routeLength += 100;
                                truckHeading = 3;
                                truck =(new Address(corner,truckStreetNum-1, StreetDirection.SOUTH));

                                continue;
                            }
                            routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 2;
                            truck =(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST));
                        }
                        else if (truckHeading == 3) {// North(→)

                            corner =hNum - (hNum  % 100);
                            // working on
                            if (sNum < truckStreetNum) {
                                corner =truckHouseNum - (truckHouseNum  % 100);
                                instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.EAST),2000));
                                routeLength += Math.abs(truck.getHouseNumber() -(corner+100));
                                unitsofTime += 2;
                                instructions.add(new Instruction(new Address((corner+100), truckStreetNum+1,StreetDirection.SOUTH),2000));
                                routeLength += 100;
                                unitsofTime += 2;
                                truckHeading=1;
                                truck=(new Address((corner+100), truckStreetNum+1, StreetDirection.SOUTH));
                                continue;
                            }
                            else {
                                instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.EAST),2000));
                                truckHeading=2;
                            }
                            routeLength += Math.abs(truckHouseNum -(corner+100));
                            unitsofTime += 2;
                            truck=(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.EAST));
                        }

                    }
                    // if Address in the left side
                    else {
                        if (truckHeading==1){// (←)South

                            corner =hNum - (hNum  % 100);
                            if (sNum<truckStreetNum){
                                instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),2000));
                                truckHeading =4;
                            }
                            else {
                                corner =truckHouseNum - (truckHouseNum  % 100);
                                instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST),2000));
                                routeLength += Math.abs(truck.getHouseNumber() -corner);
                                unitsofTime += 2;
                                instructions.add(new Instruction(new Address(corner,truckStreetNum-1, StreetDirection.SOUTH),2000));
                                routeLength += 100;
                                truckHeading =3;
                                truck=(new Address(corner,truckStreetNum-1, StreetDirection.SOUTH));
                                continue;

                            }
                            //review route length
                            routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 2;
                            truck=(new Address(truckStreetNum*100,corner/100, StreetDirection.EAST));
                        }
                        else if (truckHeading==3) {//North(→)
                            corner =(truckHouseNum) - (truckHouseNum  % 100);
                            // working on
                            if (sNum<truckStreetNum) {
                                instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.EAST), 2000));
                                routeLength += Math.abs(truck.getHouseNumber() -(corner+100));
                                unitsofTime += 2;
                                instructions.add(new Instruction(new Address((corner+100),truckStreetNum+1, StreetDirection.SOUTH), 2000));
                                routeLength +=100;
                                truckHeading=1;
                                truck= new Address((corner+100),truckStreetNum+1, StreetDirection.SOUTH);
                                continue;
                            }
                            else {
                                instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.EAST), 2000));
                                truckHeading=2;
                            }
                            //review
                            routeLength += Math.abs((corner+100) - truckHouseNum);
                            unitsofTime += 2;
                            truck=(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.EAST));

                        }

                    }

                }
                // Truck On East; eg:                *
                //                                   |
                //                                   |
                //                                   ()------
                //                                           |
                //                                           *
                else if (truckDir == StreetDirection.EAST) {
                    //  down side
                    if (hNum>truckHouseNum){
                        if(truckHeading==4){// West(↑)
                            /// work on
                            corner =truckHouseNum - (truckHouseNum  % 100);
                            if (truckHouseNum%100 ==0)
                                corner -=100;
//							else
//							corner =truckHouseNum - (truckHouseNum  % 100);
                            if (sNum>truckStreetNum){
                                instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH),2000));
                                truckHeading=3;
                            }
                            else {
                                instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH), 2000));
                                routeLength += Math.abs(truck.getHouseNumber() -corner);
                                unitsofTime += 2;
                                instructions.add(new Instruction(new Address(corner,truckStreetNum+1, StreetDirection.EAST), 2000));
                                truckHeading=2;
                                routeLength +=100;
                                truck=(new Address(corner,truckStreetNum+1, StreetDirection.EAST));
                                continue;
                            }// see here
                            //truckHeading = (truck.getHouseNumber() > a.getHouseNumber()) ? 2:4;
                            routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 2;
                            truck=(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH));
                        }

                        else if (truckHeading==2){//2; East(↓)
                            //Done
                            if (sNum>truckStreetNum){
                                corner =(truckHouseNum) - (truckHouseNum  % 100);
                                instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.SOUTH),2000));
                                routeLength += Math.abs(truck.getHouseNumber() -(corner+100));
                                unitsofTime += 2;
                                instructions.add(new Instruction(new Address((corner+100),truckStreetNum-1, StreetDirection.EAST),2000));
                                truckHeading=4;
                                routeLength += 100;
                                truck= new Address((corner+100),truckStreetNum-1, StreetDirection.EAST);
                                continue;

                            }
                            else{
                                // work here & re check
                                corner =hNum - (hNum  % 100);
                                if (hNum%100 ==0)
                                    corner +=100;
                                instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.SOUTH),2000));
                                truckHeading =1;
                                //truck= new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH);
                            }
                            routeLength += Math.abs(truckHouseNum -(corner+100));
                            unitsofTime += 2;
                            truck= new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.SOUTH);

                        }
                    }
                    //up side
                    else {
                        if (truckHeading==4){// West(↑)
                            corner= hNum - (hNum  % 100);
                            if (sNum>truckStreetNum){
                                instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH),2000));
                                truckHeading =3;
                            }
                            else {
                                // working here
                                corner= truckHouseNum - (truckHouseNum  % 100);
                                instructions.add(new Instruction(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH),2000));
                                routeLength += Math.abs(truck.getHouseNumber() -corner);
                                unitsofTime += 2;
                                instructions.add(new Instruction(new Address((corner),truckStreetNum+1, StreetDirection.EAST),2000));
                                routeLength += 100;
                                truckHeading =2;
                                truck=(new Address((corner),truckStreetNum+1, StreetDirection.EAST));
                                continue;
                            }
                            routeLength += Math.abs(corner - truckHouseNum);
                            unitsofTime += 2;
                            truck=(new Address(truckStreetNum*100,corner/100, StreetDirection.SOUTH));
                        }
                        else if (truckHeading==2){//2; East(↓)
                            corner =(truckHouseNum) - (truckHouseNum  % 100);
                            //done
                            if (sNum>truckStreetNum) {
                                instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.SOUTH), 2000));
                                routeLength += Math.abs(truck.getHouseNumber() -(corner+100));
                                unitsofTime += 2;
                                instructions.add(new Instruction(new Address((corner+100),(truckStreetNum-1), StreetDirection.EAST), 2000));
                                routeLength += 100;
                                truckHeading=4;
                                truck=new Address((corner+100),(truckStreetNum-1), StreetDirection.EAST);
                                continue;
                            }
                            else {
                                instructions.add(new Instruction(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.SOUTH), 2000));
                                truckHeading=1;
                            }
                            routeLength += Math.abs((corner+100) - truckHouseNum);
                            unitsofTime += 2;
                            truck=(new Address(truckStreetNum*100,(corner+100)/100, StreetDirection.SOUTH));

                        }
                    }
                }
            }

            // In two moves eg: *---() <- new address
            //                      |
            //                      |
            //                      *
            else {
                int y1,x2;
                Address tem;
                if (truckDir== StreetDirection.SOUTH){
                    x2 = sNum;
                    y1 = truckStreetNum ;
                    if (truckHeading==1){

                        if (hNum > y1*100){
                            int corner= truckHouseNum-(truckHouseNum%100);
                            instructions.add(new Instruction(new Address(y1*100,corner/100,StreetDirection.EAST),2000));
                            routeLength += Math.abs(truck.getHouseNumber() -corner);
                            unitsofTime += 2;
                            instructions.add(new Instruction(new Address(corner,y1-1,StreetDirection.SOUTH),2000));
                            instructions.add(new Instruction(new Address((y1-1)*100,(corner+100)/100,StreetDirection.EAST),2000));
                            routeLength +=200;
                            truckHeading =2;
                            truck =new Address((y1-1)*100,(corner+100)/100,StreetDirection.EAST);
                            continue;

                        }
                        else
                            instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.EAST),2000));}
                    else if (truckHeading==3){

                        if (hNum > y1*100)
                            instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.EAST),2000));
                        else{
                            //Done
                            int corner= truckHouseNum-(truckHouseNum%100);
                            instructions.add(new Instruction(new Address(y1*100,(corner+100)/100,StreetDirection.EAST),2000));
                            routeLength += Math.abs(truck.getHouseNumber() - (corner+100));
                            unitsofTime += 2;
                            instructions.add(new Instruction(new Address((corner+100),y1+1,StreetDirection.SOUTH),2000));
                            instructions.add(new Instruction(new Address((y1+1)*100,(corner/100),StreetDirection.EAST),2000));
                            routeLength +=200;
                            truck =new Address((y1+1)*100,(corner/100),StreetDirection.EAST);
                            truckHeading=4;
                            continue;
                        }
                    }
                    routeLength += Math.abs( x2*100 - truckHouseNum);
                    unitsofTime += 2;
                    truck=(new Address(y1*100,x2,StreetDirection.EAST));
                    //break;
                }
                else if (truckDir == StreetDirection.EAST){
                    x2 = sNum;
                    y1 = truckStreetNum ;
                    if (truckHeading==2){
                        if (hNum > y1*100) {
                            int corner= truckHouseNum-(truckHouseNum%100);
                            instructions.add(new Instruction(new Address(y1 * 100, (corner+100)/100, StreetDirection.SOUTH), 2000));
                            routeLength += Math.abs(truck.getHouseNumber() - (corner+100));
                            unitsofTime += 2;
                            instructions.add(new Instruction(new Address((corner+100),y1 -1, StreetDirection.EAST), 2000));
                            instructions.add(new Instruction(new Address((y1 -1)*100,(corner)/100, StreetDirection.SOUTH), 2000));
                            routeLength +=200;
                            truckHeading=3;
                            truck=(new Address((y1 -1)*100,(corner)/100, StreetDirection.SOUTH));
                            continue;
                        }

                        else
                            instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.SOUTH),2000));
                    }
                    else if (truckHeading==4){

                        if (hNum > y1*100)
                            instructions.add(new Instruction(new Address(y1*100,x2,StreetDirection.SOUTH),2000));
                        else{
                            ////working
                            int corner = truckHouseNum- (truckHouseNum%100);
                            instructions.add(new Instruction(new Address(y1*100,corner/100,StreetDirection.SOUTH),2000));
                            routeLength += Math.abs(truck.getHouseNumber() -corner);
                            unitsofTime += 2;
                            instructions.add(new Instruction(new Address(corner,y1+1,StreetDirection.EAST),2000));
                            instructions.add(new Instruction(new Address((y1+1)*100,(corner+100)/100,StreetDirection.SOUTH),2000));
                            routeLength +=200;
                            truckHeading= 1;
                            truck = new Address((y1+1)*100,(corner+100)/100,StreetDirection.SOUTH);
                            continue;
                        }
                    }
                    routeLength += Math.abs( x2*100 - truckHouseNum);
                    unitsofTime += 2;
                    truck=(new Address(y1*100,x2,StreetDirection.SOUTH));

                }
            }
        } //close while

        // To remember 1; (←)South, 2; East(↓), 3; North(→), 4; West(↑)
        if (truck.getStreetDirection()==StreetDirection.EAST){
            truckHeading = (truck.getHouseNumber() < a.getHouseNumber()) ? 2:4;
            instructions.add(new Instruction(a,1000,truckHeading));}
        else if (truck.getStreetDirection()==StreetDirection.SOUTH){
            truckHeading = (truck.getHouseNumber() > a.getHouseNumber()) ? 1:3;
            instructions.add(new Instruction(a,1000,truckHeading));}

        routeLength += Math.abs(truck.getHouseNumber() - a.getHouseNumber());
        unitsofTime += 2;
        t.setAddress(a);
    }

    public double getRouteLength() {
        return routeLength;
    }

    public int getUnitsofTime(){
        return unitsofTime;
    }

    public List<Instruction> getRoute() {
        return instructions;
    }

    public void removeFirstInstruction() {
        if (instructions.size() != 1)
            instructions.remove(0);
    }

    public double getTimeUnits(){
        timeUnits = (routeLength / 30);
        //Getting real time as the length is divided by 30MPH DH
        return  timeUnits;
    }
    public int getTruckHeading(){return truckHeading;}

}


