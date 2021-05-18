package com.example.mastermind;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

//import androidx.appcompat.app.AppCompatActivity;

public class geoQuestionFragment extends Fragment {

    private UserDao userDao;
    private TextView question;
    private RadioButton optionC;
    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionD;
    private TextView questionCount;
    Questions[] geographyQuest;
    private List<String> choices = new ArrayList<>(4);
    private TextView textViewScore;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultRb;
    private RadioGroup rbGroup;
    private int questionCountMax = 10;
    private int counter = 0;
    private int score = 0;
    private boolean isAnswered;
    NavController navController;
    private TextView textViewCountDown;

    //speech part
    private ImageView micButton;
    public static final Integer RecordAudioRequestFlag = 1;
    private SpeechRecognizer speechRecognizer;

    // 20 seconds for each question
    private static final long COUNTDOWN_IN_MILLIS = 20000;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    public geoQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questions, container, false);
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {

        navController = Navigation.findNavController(view);
        Context context = getContext();
        AppDatabase db = AppDatabase.getAppDatabase(context);
        userDao = db.userDao();
        rbGroup = view.findViewById(R.id.rbGroup);
        optionA = view.findViewById(R.id.optionA);
        optionB = view.findViewById(R.id.optionB);
        optionC = view.findViewById(R.id.optionC);
        optionD = view.findViewById(R.id.optionD);
        question = view.findViewById(R.id.questionsTextView);
        textViewCountDown = view.findViewById(R.id.countDown);
        questionCount = view.findViewById(R.id.questionCounter);
        textViewScore = view.findViewById(R.id.Score);
        buttonConfirmNext = view.findViewById(R.id.confirm);
        textColorDefaultRb = optionA.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        // get subject and difficulty
        String subject = getArguments().getString("subject");
        String diff = getArguments().getString("userDiff");

        geographyQuest = userDao.loadSetOfQuestions(subject, diff);

        Collections.shuffle(Arrays.asList(geographyQuest));
        showNextQuestion(geographyQuest);
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAnswered) {
                    if (rbGroup.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getActivity(), "No buttons chosen", Toast.LENGTH_SHORT).show();
                    } else {
                        checkAnswers(geographyQuest);
                    }
                } else {
                    showNextQuestion(geographyQuest);
                }
            }
        });

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }
        //editText = findViewById(R.id.editTextTextPersonName);
        micButton = getView().findViewById(R.id.imageButton2);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        //speech override functions

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
            }

            @Override
            public void onBeginningOfSpeech() {
                //editText.setText("Mic");
                //editText.setHint("Listening..");
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
                micButton.setImageResource(R.drawable.ic_baseline_mic_24);
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                //editText.setText(data.get(0));
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
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    speechRecognizer.stopListening();
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //micButton.setImageResource(R.drawable.avatars);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });

        /*
        @override
        protected void onDestroy() {
            super.onDestroy();
            speechRecognizer.destroy();
        }*/


    }

    public void showNextQuestion(Questions[] geographyQuest) {
        optionA.setTextColor(textColorDefaultRb);
        optionB.setTextColor(textColorDefaultRb);
        optionC.setTextColor(textColorDefaultRb);
        optionD.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();
        if (counter < questionCountMax) {
            question.setText(geographyQuest[counter].getQuestion());
            String a = geographyQuest[counter].getAnswer();
            String b = geographyQuest[counter].getWrongAns1();
            String c = geographyQuest[counter].getWrongAns2();
            String d = geographyQuest[counter].getWrongAns3();
            choices.add(a);
            choices.add(b);
            choices.add(c);
            choices.add(d);
            Collections.shuffle(choices);
            optionA.setText(String.valueOf(choices.get(0)));
            optionB.setText(String.valueOf(choices.get(1)));
            optionC.setText(String.valueOf(choices.get(2)));
            optionD.setText(String.valueOf(choices.get(3)));
            questionCount.setText("Question: " + (counter + 1) + "/" + questionCountMax);
            choices.removeAll(choices);
            counter++;
            isAnswered = false;
            buttonConfirmNext.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown(geographyQuest);

        } else {
            Toast.makeText(getActivity(), "End of quiz", Toast.LENGTH_SHORT).show();
            // pass score to showScore fragment
            Bundle bundle = new Bundle();
            bundle.putInt("score", score);
            navController.navigate(R.id.action_geoQuestionFragment_to_showScoreFragment, bundle);
            //Toast.makeText(getActivity(), "End of quiz", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkAnswers(Questions[] geographyQuest) {
        isAnswered = true;
        countDownTimer.cancel();

        if (optionA.isChecked()) {
            if (optionA.getText().toString().equals(geographyQuest[counter - 1].getAnswer())) {
                score++;
                Toast.makeText(getActivity(), "button A chosen was Correct", Toast.LENGTH_SHORT).show();
            }
        }
        if (optionB.isChecked()) {
            if (optionB.getText().toString().equals(geographyQuest[counter - 1].getAnswer())) {
                score++;
                Toast.makeText(getActivity(), "button B chosen was Correct", Toast.LENGTH_SHORT).show();
            }
        }
        if (optionC.isChecked()) {
            if (optionC.getText().toString().equals(geographyQuest[counter - 1].getAnswer())) {
                score++;
                Toast.makeText(getActivity(), "button C chosen was Correct", Toast.LENGTH_SHORT).show();
            }
        }
        if (optionD.isChecked()) {
            if (optionD.getText().toString().equals(geographyQuest[counter - 1].getAnswer())) {
                score++;
                Toast.makeText(getActivity(), "button D chosen was Correct", Toast.LENGTH_SHORT).show();
            }
        }
        textViewScore.setText("Score: " + score + "/" + questionCountMax);
        showSolution(geographyQuest);

    }


    private void showSolution(Questions[] questions) {
        optionA.setTextColor(Color.RED);
        optionB.setTextColor(Color.RED);
        optionC.setTextColor(Color.RED);
        optionD.setTextColor(Color.RED);

        // highlight the right answer green
        if (optionA.getText().toString().equals(questions[counter - 1].getAnswer())) {
            optionA.setTextColor(Color.GREEN);
        }
        if (optionB.getText().toString().equals(questions[counter - 1].getAnswer())) {
            optionB.setTextColor(Color.GREEN);
        }
        if (optionC.getText().toString().equals(questions[counter - 1].getAnswer())) {
            optionC.setTextColor(Color.GREEN);
        }
        if (optionD.getText().toString().equals(questions[counter - 1].getAnswer())) {
            optionD.setTextColor(Color.GREEN);
        }


        if (counter < questionCountMax) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    public void startCountDown(Questions[] questions) {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswers(questions);
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);
        // indicator turns red when there is less than 10 seconds
        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestFlag);
        }
    }
    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestFlag && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            //end of speech code
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}









