public class Test {

    /*
    PROGRAM MANUAL
    *************

    To create object, it should be like that Vehicle vehicle = new Sivic();

    To add an option to a vehicle;

    .setAbs(true);
    .setMusicSystem(true);
    .setAirBag(true);
    .setSunRoof(true);
    .setSeatHeating(true);

    to create inventory Inventory inv = new Inventory();
    to add to inventory inv.add(vehicle)
    to display contents of inventory inv.display();

    If we introduce a new type such as Truck, we can add it to inventory too.
    If there are types in the truck itself, basePrice should be added to them separately instead of in truck, as in Sivic and other models.
    However, since there is no distinction in this way in the assignment, I designed it accordingly.
    Since the cost() method is abstract in the abstract vehicle class we need to override it.
    New type class should be created like that;
------------------------------------------
    public class Truck extends Vehicle{

    final int BASEPRICE = 60000;

    public int cost(){
        return BASEPRICE + costOfOptions;
    }
}

---------------------------------------
    Thus, the program will be able to support new types that can be introduced in the future

    */


    public static void main(String[] args){



        Inventory inv = new Inventory();

        Vehicle anyVehicle = new Sivic();
        anyVehicle.setAbs(true);
        anyVehicle.setMusicSystem(true);
        anyVehicle.setAirBag(true);
        inv.add(anyVehicle);

        anyVehicle = new Sivic();
        anyVehicle.setAbs(true);
        anyVehicle.setSunRoof(true);
        inv.add(anyVehicle);

        anyVehicle = new Sity();
        anyVehicle.setMusicSystem(true);
        anyVehicle.setSunRoof(true);
        inv.add(anyVehicle);

        anyVehicle = new Racer();
        anyVehicle.setAbs(true);
        anyVehicle.setSeatHeating(true);
        inv.add(anyVehicle);

        anyVehicle = new Scooter();
        anyVehicle.setSeatHeating(true);
        inv.add(anyVehicle);


        //If we want to introduce truck

/*        anyVehicle = new Truck();
        inv.add(anyVehicle);*/

        //then when we will display inventory it will give us something like this;
        //TOTAL: 6 Vehicles including 3 Cars 1 Trucks 2 Motorbikes having a total price of 308000 TL

        inv.display();

    }
}
