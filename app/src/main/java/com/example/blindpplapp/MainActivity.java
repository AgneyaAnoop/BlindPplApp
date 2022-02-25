package com.example.blindpplapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;




import com.github.dhaval2404.imagepicker.ImagePicker;



public class MainActivity extends AppCompatActivity {


    ImageView img, scan;
    TextView Text;
    TextToSpeech textToSpeech;
    //EditText Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imageView2);
        scan = findViewById(R.id.scan);
        Text = findViewById(R.id.scannedtext);



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(MainActivity.this )
                        //.crop()	    			//Crop image
                        //.compress(1024)
                        //.maxResultSize(1080, 1080)
                        .start();
            }
        });



        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(Text.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         Uri uri = data.getData();
         img.setImageURI(uri);

    }

}