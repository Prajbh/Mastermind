package com.example.mastermind;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        rbGroup = (RadioGroup) view.findViewById(R.id.rbGroup);
        optionA = (RadioButton) view.findViewById(R.id.optionA);
        optionB = (RadioButton) view.findViewById(R.id.optionB);
        optionC = (RadioButton) view.findViewById(R.id.optionC);
        optionD = (RadioButton) view.findViewById(R.id.optionD);
        question = (TextView) view.findViewById(R.id.questionsTextView);
        questionCount = (TextView) view.findViewById(R.id.questionCounter);
        textViewScore = (TextView) view.findViewById(R.id.Score);
        buttonConfirmNext = (Button) view.findViewById(R.id.confirm);
        textColorDefaultRb = optionA.getTextColors();

        geographyQuest = userDao.loadByCategory("geography");
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
    }

    public void showNextQuestion(Questions[] geographyQuest) {
        //showSolution.setVisibility(View.INVISIBLE);
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
        } else {
            Toast.makeText(getActivity(), "End of quiz", Toast.LENGTH_SHORT).show();
            // pass score to showScore fragment
            Bundle bundle = new Bundle();
            bundle.putInt("score",score);
            navController.navigate(R.id.action_geoQuestionFragment_to_showScoreFragment, bundle );
            //Toast.makeText(getActivity(), "End of quiz", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkAnswers(Questions[] geographyQuest) {
        isAnswered = true;

        if (optionA.isChecked()) {
            if (optionA.getText().toString() == geographyQuest[counter - 1].getAnswer()) {
                score++;
                Toast.makeText(getActivity(), "button A chosen was Correct", Toast.LENGTH_SHORT).show();
            }
        }
        if (optionB.isChecked()) {
            if (optionB.getText().toString() == geographyQuest[counter - 1].getAnswer()) {
                score++;
                Toast.makeText(getActivity(), "button B chosen was Correct", Toast.LENGTH_SHORT).show();
            }
        }
        if (optionC.isChecked()) {
            if (optionC.getText().toString() == geographyQuest[counter - 1].getAnswer()) {
                score++;
                Toast.makeText(getActivity(), "button C chosen was Correct", Toast.LENGTH_SHORT).show();
            }
        }
        if (optionD.isChecked()) {
            if (optionD.getText().toString() == geographyQuest[counter - 1].getAnswer()) {
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
        if (optionA.getText().toString() == questions[counter - 1].getAnswer()) {
            optionA.setTextColor(Color.GREEN);
        }
        if (optionB.getText().toString() == questions[counter - 1].getAnswer()) {
            optionB.setTextColor(Color.GREEN);
        }
        if (optionC.getText().toString() == questions[counter - 1].getAnswer()) {
            optionC.setTextColor(Color.GREEN);
        }
        if (optionD.getText().toString() == questions[counter - 1].getAnswer()) {
            optionD.setTextColor(Color.GREEN);
        }

        if (counter < questionCountMax) {
            buttonConfirmNext.setText("Next");
        }
        else {
            buttonConfirmNext.setText("Finish");
        }
    }
}



