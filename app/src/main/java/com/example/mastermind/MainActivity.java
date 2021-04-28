package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    public static final Integer RecordAudioRequestFlag = 1;
    private SpeechRecognizer speechRecognizer;

    private UserDao userDao;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creates database and populates
        appDatabase=AppDatabase.getAppDatabase(MainActivity.this);
        userDao=appDatabase.userDao();
        String newQuestions[]=new String[]{"What is the capital of Chile?","What is the highest mountain in Britain?","What is the smallest country in the world?","Alberta is a province of which country?","How many countries still have the shilling as currency?","Which is the only vowel not used as the first letter in a US State?","What is the largest country in the world?","Where would you find the River Thames?","What is the hottest continent on Earth?","What is the longest river in the world?"};
        String questAns[]=new String[]{"Santiago","Ben Nevis","Vatican City","Canada","4","E","Russia","London, UK","Africa","River Nile"};
        List<Questions> questionsList=new ArrayList<Questions>();
        for(int i=0;i<10;i++) {
            Questions newQuest = new Questions();
            newQuest.setCategory("Geography");
            newQuest.setAnswer(questAns[i]);
            newQuest.setQuestion(newQuestions[i]);
            userDao.insert(newQuest);
        }
        //geting Geography questions
        Questions[] geographyQuest =userDao.getCatagory("Geography");
        for(int i=0;i<10;i++) {
            Log.d("geo", geographyQuest[i].getQuestion()+" ");
        }


        //speech recognition

        //check for permissions
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }

    }
}