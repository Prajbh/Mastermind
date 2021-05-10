package com.example.mastermind;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final Integer RecordAudioRequestFlag = 1;
    private SpeechRecognizer speechRecognizer;

    private EditText editText;
    private ImageView micButton;
    private FirebaseAuth mAuth;
    private EditText login_UserName, login_Password;
    private Button login, register;
    private static final int SIGNIN = 1;
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
        appDatabase=AppDatabase.getAppDatabase(MainActivity.this);
        userDao=appDatabase.userDao();
        userDao.nukeTable();

        login_UserName = (EditText) findViewById(R.id.userName);
        login_Password = (EditText) findViewById(R.id.passWord);

        login = (Button) findViewById(R.id.logInBtn);
        register = (Button) findViewById(R.id.regBtn);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        configureSignIn();


        Questions[] geographyQuest = userDao.loadByCategory("Geography");
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

        //speech recognition

        //check for permissions
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

        //editText = findViewById(R.id.editTextTextPersonName);
        micButton = findViewById(R.id.cardView12);

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

    private void configureSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("969262305380-b4gok644spmqui0bv8v5e8npa52tat5k.apps.googleusercontent.com")
                .requestEmail().build();
        client = GoogleSignIn.getClient(this, gso);

    }

    private void signToGoogle() {
        Intent signinIntent = client.getSignInIntent();
        startActivityForResult(signinIntent, SIGNIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGNIN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handledSignIn(task);

        }
    }

    private void firebaseauthwithGoogle(String idToken){
        AuthCredential credencial = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credencial)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //task is successfull

                        }
                        else{
                            //task not successfull
                        }
                    }
                });
    }

    private void handledSignIn(Task<GoogleSignInAccount> task){

        try{
            GoogleSignInAccount account = task.getResult();
            firebaseauthwithGoogle(account.getIdToken());
        }
        catch(Exception e){
            e.printStackTrace();
        }
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

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.logInBtn:
                break;
            case R.id.regBtn:
                break;
        }

    }
}


