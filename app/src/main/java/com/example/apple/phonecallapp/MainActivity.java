package com.example.apple.phonecallapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    EditText edittext1;
    ImageView imageView;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext1 = findViewById(R.id.editText1);
        imageView = findViewById(R.id.imageView);

        //Performing action on button click
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick( View arg0 ) {
                makePhoneCall();

            }
            });


    }

    private void makePhoneCall() {
            String number = edittext1.getText().toString();
            if(number.trim().length() > 0) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);

                }else {
                    String dial = "tel: " + number;
                    startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));

                }


            }else {
                Toast.makeText(MainActivity.this,"Enter the Phone number" ,Toast.LENGTH_LONG).show();
            }



        }




    @Override
    public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults ) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else  {
                Toast.makeText(this,"Permission deined",Toast.LENGTH_LONG).show();
            }
        }
    }
}
