package com.example.manolo.reconocimientotextolectura;

import android.media.AudioAttributes;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private EditText etTextoParaLeer;
    private ImageButton btnLeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTextoParaLeer=(EditText)findViewById(R.id.etTextoParaLeer);
        btnLeer=(ImageButton)findViewById(R.id.btnLeer);
        tts=new TextToSpeech(this, this);
        btnLeer.setEnabled(false);


        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leer();
            }
        });
    }

    public void leer(){
        String texto=etTextoParaLeer.getText().toString();
        tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
    }


    @Override
    public void onInit(int status) {
        if(status ==TextToSpeech.SUCCESS){
            int result=tts.setLanguage(Locale.getDefault()); //Tomará el lenguaje que tenga configurado en el emulador o en disposito real, según donde lo ejecute
            if(result==TextToSpeech.LANG_NOT_SUPPORTED || result==TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(getApplicationContext(), "lenguaje no soportado o campo de texto vacío", Toast.LENGTH_LONG).show();
            }
            else {
                btnLeer.setEnabled(true);
            }

        }



    }
}
