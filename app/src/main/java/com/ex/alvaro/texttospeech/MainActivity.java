package com.ex.alvaro.texttospeech;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

// siguiendo las guias de http://code.tutsplus.com/tutorials/android-sdk-using-the-text-to-speech-engine--mobile-8540
public class MainActivity extends ActionBarActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    EditText txtFrase;
    private int MY_DATA_CHECK_CODE=0;
    private TextToSpeech myTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.antivitymain);

        Button btnReproducir =(Button)findViewById(R.id.btnReproducir);
        btnReproducir.setOnClickListener(this);

        Intent checkTTSIntent=new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

    }


    @Override
    public void onClick(View v) {
        txtFrase=(EditText)findViewById(R.id.txtFrase); //probar en el metodo ocCreate
        String palabras=txtFrase.getText().toString();
        speakWords(palabras);

    }

    private void speakWords (String speech){ //probar con otro nombre de texto
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {

        Locale loc = new Locale ("spa", "ESP");
        myTTS.setLanguage(loc);

        

        /*if (status==TextToSpeech.SUCCESS){
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE) myTTS.setLanguage(Locale.US);
        }else if (status==TextToSpeech.ERROR){
            Toast.makeText(this,"Error.. Que sera... no se", Toast.LENGTH_LONG).show();
        }*/
    }

    protected void onActivityResult (int request_code, int result_code, Intent data){
        if (request_code==MY_DATA_CHECK_CODE){
            if (result_code==TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                myTTS=new TextToSpeech(this,this);
            }else{
                Intent installTTSIntent=new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }


}
