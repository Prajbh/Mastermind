package com.example.mastermind;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class geoQuestionFragment extends Fragment {

    private UserDao userDao;
    private TextView question;
    private RadioButton optionC;
    Questions[] geographyQuest;

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

        View returnView = inflater.inflate(R.layout.fragment_questions, container, false);
        Context context = getContext();
        AppDatabase db = AppDatabase.getAppDatabase(context);
        userDao = db.userDao();
        /*
        for (int i = 0; i < 10; i++) {
            Log.d("geo", geographyQuest[i].getQuestion() + " ");
            Log.d("geo",geographyQuest[i].getAnswer() + " ");

        }
         */
        Questions[] geographyQuest = userDao.loadByCategory("geography");
        question = (TextView) returnView.findViewById(R.id.questionsTextView);
        question.setText(geographyQuest[0].getQuestion());
        optionC = (RadioButton) returnView.findViewById(R.id.optionC);
        optionC.setText(geographyQuest[0].getAnswer());

        //question.setText("hello");
        //defaultOptionsView();
        // Inflate the layout for this fragment

        return returnView;

    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        /*

        Context context = getContext();
        AppDatabase db = AppDatabase.getAppDatabase(context);
        userDao = db.userDao();
        Questions[] geographyQuest = userDao.getCatagory("Geography");
        for (int i = 0; i < 10; i++) {
            Log.d("geo", geographyQuest[i].getQuestion() + " ");
        }
        defaultOptionsView();

         */
    }





}