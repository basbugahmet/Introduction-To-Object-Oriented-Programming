public class Car extends Vehicle{
    //Car is superClass of Sivic and Sity
    //If we want to add new model for example CRV than it will extends from here
    //Apart from that, let us say we want to add Truck to our program, it can be subclass of Car, also we can define it
    //as superclass which is like Car or Motorbike.

    //Since the cost method is abstract, we define it here return is not important because Sivic and Sity
    //also have it

    @Override
    int cost() {
        return 0;
    }
}