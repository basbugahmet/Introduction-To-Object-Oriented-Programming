public class Racer extends Motorbike{ //This is a subclass of Motorbike

    //We defined it here final because it will not be changed later. Every vehicle should be BASEPRICE here.
    final int BASEPRICE = 60000;

    //In this program all types should have cost() function in it, it should be defined like this.
    public int cost(){
        return BASEPRICE + costOfOptions;
    }

}
