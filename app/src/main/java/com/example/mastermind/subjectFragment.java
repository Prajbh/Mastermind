package com.example.mastermind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class subjectFragment extends Fragment {

    NavController navController;
    




    public subjectFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);
        Button geoBtn = view.findViewById(R.id.geo);
        geoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start a example quiz
                navController.navigate(R.id.action_subjectFragment2_to_geoQuestionFragment);
            }
        });
    }
}