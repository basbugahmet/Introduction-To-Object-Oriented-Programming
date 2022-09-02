public class Sivic extends Car { //This is a subclass of Car

    //We defined it here final because it will not be changed later. Every vehicle should be BASEPRICE here.
    final int BASEPRICE = 50000;

    //In this program all types should have cost() function in it, it should be defined like this.
    public int cost(){
        return BASEPRICE + costOfOptions;
    }

}
