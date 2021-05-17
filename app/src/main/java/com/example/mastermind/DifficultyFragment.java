package com.example.mastermind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DifficultyFragment extends Fragment {

    NavController navController;

    public DifficultyFragment() {
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
        return inflater.inflate(R.layout.fragment_difficulty, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        navController = Navigation.findNavController(view);
        Button easyBtn = view.findViewById(R.id.easy);
        Button mediumBtn = view.findViewById(R.id.medium);
        Button hardBtn = view.findViewById(R.id.hard);


        // pass difficulty that the user chooses
        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("difficulty", "1");
                navController.navigate(R.id.action_ageFragment_to_subjectFragment2, bundle);
            }
        });
        mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("difficulty", "2");
                navController.navigate(R.id.action_ageFragment_to_subjectFragment2, bundle);
            }
        });
        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("difficulty", "3");
                navController.navigate(R.id.action_ageFragment_to_subjectFragment2, bundle);
            }
        });

    }
}