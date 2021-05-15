package com.example.mastermind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class showScoreFragment extends Fragment {
    private TextView score;
    NavController navController;


    public showScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_score, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);
        score = (TextView) view.findViewById(R.id.showScore);
        BottomNavigationView navMenu = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);
        Integer userScore = getArguments().getInt("score");
        //Toast.makeText(getActivity(),"Score: " + score, Toast.LENGTH_SHORT).show();
        //score.setText(getArguments().getInt("score"));
        score.setText("Score: " + userScore.toString());
        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.subjectMenu:
                        navController.navigate(R.id.action_showScoreFragment_to_DifficultyFragment);
                        break;
                        /*
                    case R.id.navigation_received_files_list:
                        navController.navigate(R.id.navigation_received_files_list);
                        break;
                         */
                }
                return true;
            }
        });
    }
}