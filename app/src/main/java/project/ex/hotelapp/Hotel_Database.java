package project.ex.hotelapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Hotel_Database extends SQLiteOpenHelper {
    public Hotel_Database(@Nullable Context context) {
        super(context, "Hoteldb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Createdb = "CREATE TABLE Hotels (HOTEL_NAME VARCHAR(100) NOT NULL,ADDRESS VARCHAR(255) NOT NULL,EGP VARCHAR(50) NOT NULL, RATING INT NOT NULL, DESCRIPTION VARCHAR(10000)NOT NULL)";
        db.execSQL(Createdb);
        getDB(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void getDB(SQLiteDatabase db){


        // try to execute directly without quotations
        String Insert1 = "Insert into Hotels VALUES(\'Sunrise Alex Avenue Hotel\',\"Cornish Road, Roushdy, 99998 Alexandria, Egypt\",\"1042\",5,\"Occupying a sea front location in the middle of Alexandria, SUNRISE Alex Avenue Hotel features a private beach. All rooms boast panoramic views of the Mediterranean Sea. The hotel has 2 outdoor pools and spa.\n" +
                "All rooms and suites at the hotel include a balcony and feature a modern décor. Each suite has a living room, a flat-screen TV and an electric kettle. The bathroom is fitted with bath and hairdryer.\n" +
                "Guests can enjoy an exquisite international cuisine, Lebanese cuisine and an oriental buffet breakfast at Mediterranean Restaurant. Featuring a seafront location, Laguna Terrace offers light snacks. Cocktails are served at Lounge Bar and Montreal.\n" +
                "The hotel has a fully equipped gym with cardiovascular machines and a spa that offers relaxing massage treatments to guests. For more relaxed options: a hot tub and steam room are also available.\n" +
                "SUNRISE Alex Avenue Hotel is located only 3 km from the city centre and the stunning Bibliotheca Alexandrina. Alexandria Airport is 10 km drive away. Wi-Fi is available upon request.\n" +
                "\")";

        db.execSQL(Insert1);
        String Insert2 = "Insert into Hotels VALUES(\'Royel Jewel ALRaml\',\"Raml Station, Al Attarin, 99999 Alexandria, Egypt\",\"1100\",4,\"Boasting a garden, terrace and views of city, Royal Jewel Al Raml Hotel is situated in Alexandria, 2.4 km from Al Ibrahimiyyah Beach. Among the facilities of this property are a restaurant, a 24-hour front desk and room service, along with free WiFi. Alexandria Zoo is 3.9 km away and Graeco-Roman Museum of Alexandria is 1.6 km from the hotel.\n" +
                "The hotel will provide guests with air-conditioned rooms with a wardrobe, a kettle, a minibar, a safety deposit box, a flat-screen TV and a private bathroom with a bidet. At Royal Jewel Al Raml Hotel rooms come with bed linen and towels.\n" +
                "Guests at the accommodation can enjoy a continental breakfast.\n" +
                "Popular points of interest near Royal Jewel Al Raml Hotel include Catacombs of Kom el Shoqafa, Alexandrina Library and Alexandria Stadium. The nearest airport is Borg el Arab International Airport, 37 km from the hotel.\n" +
                "\")";
        db.execSQL(Insert2);
        String Insert3 = "Insert into Hotels VALUES(\"Amoun Hotel Alexandria\",\"32 El Nasr St., El Manshia, 99999 Alexandria, Egypt \",\"1390\",3,\"With a panoramic view of Alexandria's roofs and marina, the 3-star Amoun Hotel is only 3 km from the ancient Lighthouse of Alexandria. Its rooms feature flat-screen TVs and private balconies.\n" +
                "Fitted with wooden floors and large windows, each room offers warm atmosphere. Guests can benefit from conveniently placed lighting, satellite TV and well stocked minibars.\n" +
                "Rich breakfast buffet can be enjoyed each morning in the modern restaurant. For a cup of tea or coffee, Amoun Hotel Alexandria offers a café featuring an outdoor area with wicker chairs.\n" +
                "The Amoun Alexandria is situated less than a 5-minute walk from the El Raml Train Station and approximately 3 km from The Royal Library of Alexandria. Alexandria El Nozha International Airport is also 6 km away.\n" +
                "\")";
        db.execSQL(Insert3);
        String Insert4 = "Insert into Hotels VALUES(\"Sea Star\",\"24 Amin Fikry st., 27500 Alexandria, Egypt\",\"236\",2,\"Located in Alexandria, 2.5 km from Al Ibrahimiyyah Beach, Sea Star provides air-conditioned rooms and a terrace. Among the facilities of this property are a restaurant, a 24-hour front desk and room service, along with free WiFi throughout the property. The hotel features family rooms.\n" +
                "At the hotel, each room includes a wardrobe, a flat-screen TV and a private bathroom. All units will provide guests with a fridge.\n" +
                "Continental and halal breakfast options are available daily at Sea Star.\n" +
                "Popular points of interest near the accommodation include Catacombs of Kom el Shoqafa, Alexandrina Library and Alexandria Stadium. The nearest airport is Borg el Arab International Airport, 37 km from Sea Star\n" +
                "\")";

        db.execSQL(Insert4);


    }

    public Hotel_model GetData(String hotel) {
            Hotel_model model = null;
            String query = "SELECT * FROM Hotels WHERE HOTEL_NAME=\"" + hotel + "\"";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                String Hotel = cursor.getString(0);
                String Address = cursor.getString(1);
                Address = "\t" + Address;
                String egp = cursor.getString(2);
                egp ="\t" +egp+ " EGP/Night";
                int rating = cursor.getInt(3);
                String description = cursor.getString(4);
                model = new Hotel_model(Hotel, Address, egp, rating, description);}
            cursor.close();
            db.close();
        return model;
    }
}