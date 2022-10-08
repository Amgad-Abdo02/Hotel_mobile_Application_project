package project.ex.hotelapp;

public class Hotel_model {
    private String Hotel_name, Address,EGP, Description;
    private int Rating;

    public Hotel_model(String hotel_name, String address, String EGP, int rating, String description) {
        Hotel_name = hotel_name;
        Address = address;
        this.EGP = EGP;
        Rating = rating;
        Description = description;
    }

    @Override
    public String toString() {
        return "Hotel_model{" +
                "Hotel_name='" + Hotel_name + '\'' +
                ", Address='" + Address + '\'' +
                ", EGP=" + EGP +
                ", Rating=" + Rating +
                ", Description='" + Description + '\'' +
                '}';
    }

    public String getHotel_name() {
        return Hotel_name;
    }


    public String getAddress() {
        return Address;
    }

    public String getEGP() {
        return EGP;
    }


    public int getRating() {
        return Rating;
    }


    public String getDescription() {
        return Description;
    }
    
}
