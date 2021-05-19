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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class subjectFragment extends Fragment {

    NavController navController;
    private TextView name, score;
    FirebaseFirestore db;
    User user;


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
        BottomNavigationView navMenu = (BottomNavigationView) view.findViewById(R.id.bottom_navigation2);
        Button geoBtn = view.findViewById(R.id.geo);
        Button sportsBtn = view.findViewById(R.id.sports);
        Button moviesBtn = view.findViewById(R.id.movies);
        Button histBtn = view.findViewById(R.id.history);
        //String diff = getArguments().getString("difficulty");
        db = FirebaseFirestore.getInstance();
        name = (TextView) view.findViewById(R.id.currentUsersName1);


        db.collection("users").document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                name.setText(user.getName());
                //score.setText("HighScore: " + user.getScore());
            }
        });



        geoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get and pass difficulty
                Bundle bundle = new Bundle();
                bundle.putString("subject", "geography");
                navController.navigate(R.id.action_subjectFragment2_to_DifficultyFragment, bundle);
            }
        });
        histBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get and pass difficulty
                Bundle bundle = new Bundle();
                bundle.putString("subject", "history");
                navController.navigate(R.id.action_subjectFragment2_to_DifficultyFragment, bundle);
            }
        });
        sportsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get and pass difficulty
                Bundle bundle = new Bundle();
                bundle.putString("subject", "sports");
                navController.navigate(R.id.action_subjectFragment2_to_DifficultyFragment, bundle);            }
        });
        moviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get and pass difficulty
                Bundle bundle = new Bundle();
                bundle.putString("subject", "movies");
                navController.navigate(R.id.action_subjectFragment2_to_DifficultyFragment, bundle);            }
        });



        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profileScreen:
                        navController.navigate(R.id.action_subjectFragment2_to_profileFragment);
                        break;
                    case R.id.leaderboard:
                        navController.navigate(R.id.action_subjectFragment2_to_leaderBoardFragment);
                        break;
                }
                return true;
            }
        });


    }
}