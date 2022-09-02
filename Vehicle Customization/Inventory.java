import java.util.*;


public class Inventory { //This is our storage class that we store all of vehicles, and also in order to display vehicles

    //This is the ArrayList that we keep all vehicles as Vehicle type.
    ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();

/*    This is to store types of each vehicles e.g (Sivic >> Car, Scooter >>Motorbike).
    We use them when we display total vehicles.*/
    ArrayList<String> typesOfObjectInString = new ArrayList<>();

    int totalCostOfInventory = 0; //At the beginning the total cost of inventory is 0, later we will  increase it
                                  //according to cost() of each vehicle.

    public void add(Vehicle vhc) { //This is the method to add vehicles to the allVehicles ArrayList
        allVehicles.add(vhc);
    }


    public void display() {  //This method for displaying all vehicles

        for (int i = 0; i < allVehicles.size(); i++) {
            System.out.println(allVehicles.get(i).getClass().getName() + allVehicles.get(i).toString() + allVehicles.get(i).cost() + " TL");
            totalCostOfInventory += allVehicles.get(i).cost(); //for increase the total cost of Inventory

            if (allVehicles.get(i) instanceof Car || allVehicles.get(i) instanceof Motorbike) {
/*                Here, we will group them according their superclass, and see how many different vehicles there are from one type of vehicle.
                For instance, scooter is a motorbike, racer is a motorbike then we can say that there are two motorbikes here.*/
                typesOfObjectInString.add(allVehicles.get(i).getClass().getSuperclass().getName()); //So we will get superclass name.
            } else {
                typesOfObjectInString.add(allVehicles.get(i).getClass().getName());
                //If we add any other type vehicle for example Truck, then we will it own name not superClass name.
                //If Truck has also its own models for example, Actros, then we can use;
                //typesOfObjectInString.add(allVehicles.get(i).getClass().getSuperclass().getName());
            }

        }

        StringBuilder totalInventoryOutput = new StringBuilder(); //We use here stringBuilder to display total Inventory

        totalInventoryOutput.append("TOTAL: " + allVehicles.size() + " Vehicles including");

        Map<String, Integer> whichVehicleAndHowMany = new HashMap<String, Integer>();
/*        I use HashMap here which we learned in our classes at the beginning of exception handling course.
        It will have two things one of them is String(here type of car as String), another one is
        Integer which will keep number of the vehicle type
        As we know above, we add the types of each vehicle to the an ArrayList which name is typesOfObjectInString
        Here, our goal is what are the vehicle types in this list and how many of each vehicle type there are
        Thus, the program will be able to display any other Vehicle types such as Truck successfully.*/



        //In fact, if there are just car and motorbike vehicles we do not need to use it
        //However, Truck superclass can be introduced our program with its price
        for (String str : typesOfObjectInString) {
            if (whichVehicleAndHowMany.containsKey(str)) { //containsKey: Queries whether a particular key has already been entered.
                whichVehicleAndHowMany.put(str, whichVehicleAndHowMany.get(str) + 1);
            } else {
                whichVehicleAndHowMany.put(str, 1);
            }
        }

        for (Map.Entry<String, Integer> everyVehicle : whichVehicleAndHowMany.entrySet()) {
            totalInventoryOutput.append(" " + everyVehicle.getValue() + " "+ everyVehicle.getKey() + "s");
        }                         //getValue() >> number of the type    //getKey() >> name of this type

        totalInventoryOutput.append(" having a total price of " + totalCostOfInventory + " TL");
        System.out.println(totalInventoryOutput);



    }
}


