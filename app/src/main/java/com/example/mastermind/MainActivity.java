package com.example.mastermind;

import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    public static final Integer RecordAudioRequestFlag = 1;
    private SpeechRecognizer speechRecognizer;

    private EditText editText;
    private ImageView micButton;
    private FirebaseAuth mAuth;
    private EditText login_UserName, login_Password;
    private Button login, register;
    private static final int SIGNIN = 1;
    private SignInButton signinbutton;
    private GoogleSignInOptions options;
    private GoogleSignInClient client;

    private UserDao userDao;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //creates database and populates
        appDatabase = AppDatabase.getAppDatabase(MainActivity.this);
        userDao = appDatabase.userDao();
        userDao.nukeTable();


    }


        /*Questions[] geographyQuest = userDao.loadByCategory("Geography");
        for (int i = 0; i < 8; i++) {
            Log.d("geo", geographyQuest[i].getQuestion() + " " );
            Log.d("geo",geographyQuest[i].getAnswer() + " " );

        }

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
            //userDao.insert(newQuest);
        }
        //geting Geography questions
        //Questions[] geographyQuest =userDao.getCatagory("Geography");
        for(int i=0;i<10;i++) {
            Log.d("geo", geographyQuest[i].getQuestion() + " ");
        }



   /* @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.logInBtn:
                break;
            case R.id.regBtn:
                break;
        }

    }*/
}


