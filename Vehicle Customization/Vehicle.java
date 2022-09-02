public abstract class Vehicle {

    //These are the all options for all vehicle types. At the beginning they are false
    private Boolean airBag = false;
    private Boolean sunRoof = false;
    private Boolean musicSystem = false;
    private Boolean abs = false;
    private Boolean seatHeating = false;
    int costOfOptions = 0;


    public Boolean getAirBag() {
        return airBag;
    }

    public void setAirBag(Boolean airBag) {
        if (this instanceof Motorbike) { //if we try to add airbag to the motorbike
            try {
                if (airBag) {
                    throw new InvOptException("Motorbike cannot have Airbag option!");
                }
            } catch (InvOptException invOptException) {
                System.out.println(invOptException.getMessage());
            }

        }else{
            if(airBag && !getAirBag()){ //we check getAirBag() because airBag cannot be added two times
                costOfOptions += 3000;
                this.airBag = true;
            }
        }

    }

    public Boolean getSunRoof() {
        return sunRoof;
    }

    public void setSunRoof(Boolean sunRoof){
        if (this instanceof Motorbike) { //if we try to add sunroof to the motorbike
            try {
                if (sunRoof) {
                    throw new InvOptException("Motorbike cannot have Sunroof option!");
                }
            } catch (InvOptException invOptException) {
                System.out.println(invOptException.getMessage());
            }
        } else {
            if(sunRoof && !getSunRoof()){ //we check getSunRoof() because sunroof cannot be added two times
                costOfOptions += 2000;
                this.sunRoof = true;
            }
        }
    }

    public Boolean getMusicSystem() {
        return musicSystem;
    }

    public void setMusicSystem(Boolean musicSystem) {
        if (this instanceof Motorbike) { //if we try to add music system to the motorbike
            try {
                if (musicSystem) {
                    throw new InvOptException("Motorbike cannot have Music System option!");
                }
            } catch (InvOptException invOptException) {
                System.out.println(invOptException.getMessage());
            }


        }else{
            if(musicSystem && !getMusicSystem()){ //we check getMusicSystem() because music system cannot be added two times
                costOfOptions += 1000;
                this.musicSystem = true;
            }
        }
    }

    public Boolean getAbs() {
        return abs;
    }

    public void setAbs(Boolean abs) {
        if(abs && !getAbs()){ //we check getAbs because abs cannot be added two times
            costOfOptions += 5000;
            this.abs = true;
        }
    }

    public Boolean getSeatHeating() {
        return seatHeating;
    }

    public void setSeatHeating(Boolean seatHeating){
        if (this instanceof Car) {//if we try to add seat heating to the car
            try {
                if (seatHeating) {
                    throw new InvOptException("Car cannot have Seat Heating option!");
                }
            } catch (InvOptException invOptException) {
                System.out.println(invOptException.getMessage());
            }
        }else{
            if (seatHeating && !getSeatHeating()) { //we check getSeatHeating() because seat heating cannot be added two times
                costOfOptions += 2000;
                this.seatHeating = true;
            }
        }

    }

    public String toString() {

        //If vehicle does not have any options
        if (!this.getAbs() && !this.getMusicSystem() && !this.getAirBag() && !this.getSunRoof() && !this.getSeatHeating())
            return " without any options ";

        else {
            StringBuilder stc = new StringBuilder();
            stc.append(" with");
            if (this.getAbs()) {
                stc.append(" ABS");
                stc.append(",");
            }
            if (this.getMusicSystem()) {
                stc.append(" Music System");
                stc.append(",");
            }
            if (this.getAirBag()) {
                stc.append(" Air Bag");
                stc.append(",");
            }
            if (this.getSunRoof()) {
                stc.append(" Sunroof");
                stc.append(",");
            }
            if (this.getSeatHeating()) {
                stc.append(" Seat Heating");
                stc.append(",");
            }


            stc.setLength(stc.length() - 1);  //to delete last comma
            stc.append(" having a total price of ");

            return stc.toString();
        }
    }

    abstract int cost(); // I defined cost() here as abstract, it we want to create new class than it needs to override it
}
