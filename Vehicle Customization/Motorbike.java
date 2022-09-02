public class Motorbike extends Vehicle{
    //Car is superClass of Sivic and Sity
    //If we want to add new model for example Touring than it will extends from here

    //Since the cost method is abstract, we define it here return is not important because Racer and Scooter
    //also have it
    @Override
    int cost() {
        return 0;
    }
}
