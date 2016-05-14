package macmain.co.za.fakecaller;

import android.content.Intent;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

import android.os.Handler;


import Classes.AppConstants;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView ivAccept;
    private ImageView ivCancel;
    private TextView tvNumber;
    private String setNumber;
    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private FileManager dao;
    private String decide = "no";




    private static final String PREFER_NAME = "IMAGE";




    TextView timerTextView;
    long startTime = 0;



    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };





    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);












        dao = new FileManager();
        timerTextView = (TextView) findViewById(R.id.text2);
        imageView = (ImageView) findViewById(R.id.imageView);
        ivAccept = (ImageView) findViewById(R.id.ivAccept);
        ivCancel = (ImageView) findViewById(R.id.ivCancel);
        tvNumber = (TextView) findViewById(R.id.tvName);



        dao.saveDecision(MainActivity.this,decide);


        if(dao != null)
        {


            tvNumber.setText(dao.getNumber(MainActivity.this));



            String decision = dao.getDecision(MainActivity.this);


            if(decision.equals("yes"))
            {

                String image = dao.getImage(MainActivity.this);


                imageView.setImageURI(Uri.parse(image));

            }

        }
















        final MediaPlayer mp = MediaPlayer.create(MainActivity.this,R.raw.pilot);

        mp.start();




        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.stop();
                Intent intent = new Intent(MainActivity.this, SetNumberActivity.class);

                startActivityForResult(intent, 100);

            }
        });

      imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.stop();

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Contact Image"), 1);



            }
        });



        ivAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                TextView txtClicks = (TextView) findViewById(R.id.tvTimer);
                                // task to be done every 1000 milliseconds
                                iClicks = iClicks + 1;
                                txtClicks.setText(String.valueOf(iClicks));
                            }
                        });

                    }
                }, 0, 1000);*/

                mp.stop();

                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);







            }
        });


        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //timerHandler.removeCallbacks(timerRunnable);
                mp.stop();

                Intent intent = new Intent();

                intent.putExtra(AppConstants.EXIT,"Exiting application");

                setResult(RESULT_CANCELED, intent);
                finish();


            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK && data !=null){

            tvNumber.setText(data.getStringExtra(AppConstants.Number));
        }else if(requestCode == 1 && resultCode == RESULT_OK && data != null){

            Uri uri = data.getData();



            imageView.setImageURI(data.getData());

            dao.saveDecision(MainActivity.this,"yes");
            dao.saveImage(MainActivity.this,data.getData().toString());






        }
    }
}
