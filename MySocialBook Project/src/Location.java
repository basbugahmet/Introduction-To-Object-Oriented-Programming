public class Location {
    //we created this class for the coordinates of the location information when the user posted

    double longitude;
    double latitude;

    public Location(double longitude, double latitude) {
        this.setLongitude(longitude);
        this.setLatitude(latitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return getLatitude() + ", " + getLongitude();

    }
}
