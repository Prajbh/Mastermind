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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


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
        FirebaseFirestore database = FirebaseFirestore.getInstance().getInstance();
        BottomNavigationView navMenu = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);
        Integer userScore = getArguments().getInt("score");
        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("score", FieldValue.increment(userScore));
        score.setText(userScore.toString() + "/10");



        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.leaderboard:
                        navController.navigate(R.id.action_showScoreFragment_to_leaderBoardFragment);
                        break;
                    case R.id.playScreen:
                    case R.id.homeMenu:
                        navController.navigate(R.id.action_showScoreFragment_to_subjectFragment2);
                        break;
                    case R.id.profileScreen:
                        navController.navigate(R.id.action_showScoreFragment_to_profileFragment);

                }
                return true;
            }
        });

    }
}