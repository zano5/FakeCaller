package macmain.co.za.fakecaller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import Classes.AppConstants;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(7000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivityForResult(intent,101);
                }
            }
        };
        timerThread.start();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(data !=null && resultCode == RESULT_CANCELED && requestCode == 101){

            String message = data.getStringExtra(AppConstants.EXIT);
            Toast.makeText(SplashScreen.this,message, Toast.LENGTH_SHORT).show();
            finish();


        }

    }

}
