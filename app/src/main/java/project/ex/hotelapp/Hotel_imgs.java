package project.ex.hotelapp;

public class Hotel_imgs {
    private int main,img1,img2,img3,img4,img5,img6,img7;
    private String hotelname;

    public Hotel_imgs(String hotelname, int main, int img1, int img2, int img3, int img4, int img5, int img6, int img7) {
        this.main = main;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
        this.img6 = img6;
        this.img7 = img7;
        this.hotelname = hotelname;
    }
    public int getMain() {
        return main;
    }

    public int getImg1() {
        return img1;
    }

    public int getImg2() {
        return img2;
    }

    public int getImg3() {
        return img3;
    }

    public int getImg4() {
        return img4;
    }

    public int getImg5() {
        return img5;
    }

    public int getImg6() {
        return img6;
    }

    public int getImg7() {
        return img7;
    }

    public String getHotelname() {
        return hotelname;
    }}

