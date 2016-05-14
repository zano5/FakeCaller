package macmain.co.za.fakecaller;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ProJava on 5/14/2016.
 */
public class FileManager {


    private static final String MY_PREF= "pref";
    private static final String KEY_NUMBER ="number";
    private static final String KEY_IMAGE ="image";
    private static final String KEY_DECISION ="decide";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;




    public void save(Context context, String number, String image, String decision)
    {

        settings = context.getSharedPreferences(MY_PREF,context.MODE_PRIVATE);

        editor = settings.edit();


        editor.putString(KEY_NUMBER,number);
        editor.putString(KEY_IMAGE,image);
        editor.putString(KEY_DECISION,decision);

        editor.apply();
        editor.commit();

    }

    public void saveNumber(Context context, String number)
    {

        settings = context.getSharedPreferences(MY_PREF,context.MODE_PRIVATE);

        editor = settings.edit();


        editor.putString(KEY_NUMBER,number);

        editor.apply();
        editor.commit();

    }

    public void saveImage(Context context, String image)
    {

        settings = context.getSharedPreferences(MY_PREF,context.MODE_PRIVATE);

        editor = settings.edit();


        editor.putString(KEY_IMAGE,image);


        editor.apply();
        editor.commit();

    }


    public void saveDecision(Context context, String decision)
    {

        settings = context.getSharedPreferences(MY_PREF,context.MODE_PRIVATE);

        editor = settings.edit();


        editor.putString(KEY_DECISION,decision);

        editor.apply();
        editor.commit();

    }



    public String[] getValues(Context context)
    {
        settings = context.getSharedPreferences(MY_PREF,context.MODE_PRIVATE);

        String[] values = {settings.getString(KEY_NUMBER,"Private Number"),settings.getString(KEY_IMAGE,"")};


        return  values;
    }

    public  String getNumber(Context context)
    {
        settings = context.getSharedPreferences(MY_PREF,context.MODE_PRIVATE);

        String number = settings.getString(KEY_NUMBER,"Private Number");


        return  number;
    }


    public  String getImage(Context context)
    {
        settings = context.getSharedPreferences(MY_PREF,context.MODE_PRIVATE);

        String image = settings.getString(KEY_IMAGE,"");


        return  image;
    }

    public  String getDecision(Context context)
    {
        settings = context.getSharedPreferences(MY_PREF,context.MODE_PRIVATE);

        String decision = settings.getString(KEY_NUMBER,"");


        return  decision;
    }



}
