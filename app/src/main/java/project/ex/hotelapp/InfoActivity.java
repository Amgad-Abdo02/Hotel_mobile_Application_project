package project.ex.hotelapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{
    Hotel_Database db = new Hotel_Database(InfoActivity.this);
Hotel_model hotel = null;
androidx.appcompat.widget.AppCompatButton bookbtn;
Hotel_imgs [] hotelimg =new Hotel_imgs[5];
RatingBar rating ; TextView price, address,description,hotelname;
ImageView mainimg,img1,img2,img3,img4,img5,img6,img7,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullscreen();
        setContentView(R.layout.activity_info);

        findviews();
        bookbtn.setOnClickListener(this);
        back.setOnClickListener(this);
        setter();

       upgrade(db);
    }

    private void fullscreen(){
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        this.getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        );
    }

private void setter(){
    hotelimg[1] = new Hotel_imgs("Sunrise Alex Avenue Hotel",R.drawable.sunrisecard,R.drawable.sun1,R.drawable.sun2,R.drawable.sun3,R.drawable.sun4,R.drawable.sun5,R.drawable.sun6,R.drawable.sun7);
    hotelimg[2] = new Hotel_imgs("Royel Jewel ALRaml",R.drawable.royaljewelcardview,R.drawable.jewel1,R.drawable.jewel2,R.drawable.jewel3,R.drawable.jewel4,R.drawable.jewel5,R.drawable.jewel6,R.drawable.jewel7);
    hotelimg[3] = new Hotel_imgs("Amoun Hotel Alexandria",R.drawable.amounview,R.drawable.amoun1,R.drawable.amoun2,R.drawable.amoun3,R.drawable.amoun4,R.drawable.amoun5,R.drawable.amoun6,R.drawable.amoun7);
    hotelimg[4] = new Hotel_imgs("Sea Star",R.drawable.seastarview,R.drawable.sea1,R.drawable.sea2,R.drawable.sea3,R.drawable.sea4,R.drawable.sea5,R.drawable.sea6,R.drawable.sea7);

}

    private Hotel_imgs getter(String hotel) {
        Hotel_imgs  img = null;
        for(int i=1;i<5; i++){
            if(hotelimg[i].getHotelname().equals(hotel)) {
                img = hotelimg[i];
                break;
            }
        }
        return img;
    }

    private void upgrade(Hotel_Database db){
        Intent i = getIntent();
        hotelimg[0] = getter(i.getStringExtra("hotel name"));

            hotel = db.GetData(i.getStringExtra("hotel name"));
            rating.setRating(hotel.getRating());
            price.setText(hotel.getEGP());
            address.setText(hotel.getAddress());
            description.setText(hotel.getDescription());
            hotelname.setText(hotel.getHotel_name());
            mainimg.setImageResource(hotelimg[0].getMain());
            img1.setImageResource(hotelimg[0].getImg1());
            img2.setImageResource(hotelimg[0].getImg2());
            img3.setImageResource(hotelimg[0].getImg3());
            img4.setImageResource(hotelimg[0].getImg4());
            img5.setImageResource(hotelimg[0].getImg5());
            img6.setImageResource(hotelimg[0].getImg6());
            img7.setImageResource(hotelimg[0].getImg7());

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case(R.id.bookbtn):
        Intent j = new Intent(InfoActivity.this,Sounds.class);
        j.putExtra("turn",2);

        startService(j);
        String text = "You've Successfully booked " + hotelname.getText().toString()+"!";
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        break;
            case (R.id.back):
                Intent i = new Intent(InfoActivity.this,MainPage.class);
                i.putExtra("first visit",false);
                startActivity(i);
                overridePendingTransition(R.anim.appear,R.anim.disappear);
        }
    }
    private void findviews(){
        hotelname = findViewById(R.id.hotelname);
        rating = findViewById(R.id.rating);
        price = findViewById(R.id.price);
        address = findViewById(R.id.address);
        description = findViewById(R.id.description);
        bookbtn = findViewById(R.id.bookbtn);
        mainimg = findViewById(R.id.mainimg);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        back = findViewById(R.id.back);

    }

}