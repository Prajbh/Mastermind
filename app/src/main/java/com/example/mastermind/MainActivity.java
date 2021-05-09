package com.example.mastermind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.RecognitionListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final Integer RecordAudioRequestFlag = 1;
    private SpeechRecognizer speechRecognizer;

    private EditText editText;
    private ImageView micButton;

    //private UserDao userDao;
    //private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creates database and populates
        //appDatabase=AppDatabase.getAppDatabase(MainActivity.this);
        //userDao=appDatabase.userDao();
        String newQuestions[]=new String[]{"What is the capital of Chile?","What is the highest mountain in Britain?","What is the smallest country in the world?","Alberta is a province of which country?","How many countries still have the shilling as currency?","Which is the only vowel not used as the first letter in a US State?","What is the largest country in the world?","Where would you find the River Thames?","What is the hottest continent on Earth?","What is the longest river in the world?"};
        String questAns[]=new String[]{"Santiago","Ben Nevis","Vatican City","Canada","4","E","Russia","London, UK","Africa","River Nile"};
        /*List<Questions> questionsList=new ArrayList<Questions>();
        for(int i=0;i<10;i++) {
            Questions newQuest = new Questions();
            newQuest.setCategory("Geography");
            newQuest.setAnswer(questAns[i]);
            newQuest.setQuestion(newQuestions[i]);
            //userDao.insert(newQuest);
        }
        //geting Geography questions
        //Questions[] geographyQuest =userDao.getCatagory("Geography");
        for(int i=0;i<10;i++) {
            Log.d("geo", geographyQuest[i].getQuestion()+" ");
        }

*/
        //speech recognition

        //check for permissions
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

        editText = findViewById(R.id.editTextTextPersonName);
        micButton = findViewById(R.id.imageView);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

                }


            @Override
            public void onBeginningOfSpeech() {
                editText.setText("Mic");
                editText.setHint("Listening..");

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {

                //micButton.setImageResource(R.drawable.);
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                editText.setText(data.get(0));

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        micButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();
                }

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                   //micButton.setImageResource(R.drawable.avatars);

                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestFlag);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestFlag && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }
}


