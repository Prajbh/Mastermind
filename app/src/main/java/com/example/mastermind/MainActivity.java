package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private com.example.mastermind.UserDao userDao;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //creates database and populates
        appDatabase=AppDatabase.getAppDatabase(MainActivity.this);
        userDao=appDatabase.userDao();
        userDao.nukeTable();
        String newQuestions[]=new String[]{"What is the capital of Chile?","What is the highest mountain in Britain?","What is the smallest country in the world?","Alberta is a province of which country?","How many countries still have the shilling as currency?","Which is the only vowel not used as the first letter in a US State?","What is the largest country in the world?","Where would you find the River Thames?","What is the hottest continent on Earth?","What is the longest river in the world?"};
        String questAns[]=new String[]{"Santiago","Ben Nevis","Vatican City","Canada","4","E","Russia","London, UK"};


        List<Questions> questionsList=new ArrayList<Questions>();
        for(int i=0;i<8;i++) {
            Questions newQuest = new Questions();
            newQuest.setCategory("Geography");
            newQuest.setAnswer(questAns[i]);
            newQuest.setQuestion(newQuestions[i]);
            userDao.insert(newQuest);
        }


        Questions[] geographyQuest = userDao.loadByCategory("Geography");
        for (int i = 0; i < 8; i++) {
            Log.d("geo", geographyQuest[i].getQuestion() + " " );
            Log.d("geo",geographyQuest[i].getAnswer() + " " );

        }


    }
}