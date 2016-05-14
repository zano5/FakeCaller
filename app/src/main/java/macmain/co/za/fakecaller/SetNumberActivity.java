package macmain.co.za.fakecaller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Classes.AppConstants;


public class SetNumberActivity extends AppCompatActivity {

    private Button btnConfirm, btnCancel;
    private EditText etNumber;
    private FileManager dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_number);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dao = new FileManager();
        etNumber = (EditText) findViewById(R.id.etNumber);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = etNumber.getText().toString();

                dao.saveNumber(SetNumberActivity.this,number);


                Intent intent = new Intent();





                intent.putExtra(AppConstants.Number, number);

                setResult(RESULT_OK, intent);


                finish();


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });
    }



}
