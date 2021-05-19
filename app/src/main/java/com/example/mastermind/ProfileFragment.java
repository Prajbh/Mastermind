package com.example.mastermind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {

    NavController navController;
    FirebaseFirestore db;
    User user;



    public ProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        TextView name, email;
        EditText newPass = view.findViewById(R.id.passwordBox);
        Button update = view.findViewById(R.id.updateBtn);
        name = view.findViewById(R.id.nameBox);
        email = view.findViewById(R.id.emailBox);

        db.collection("users").document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                name.setText(user.getName());
                email.setText(user.getEmail());
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String newPassword = newPass.getText().toString();
            if (newPass.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
            }
            else {
                user.updatePassword(newPassword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // store new password to firestore
                                    db.collection("users").document(FirebaseAuth.getInstance().getUid())
                                            .update(
                                                    "pass", newPassword
                                            );
                                    Toast.makeText(getContext(), "Password Updated", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(getContext(), "Password NOT updated", Toast.LENGTH_SHORT).show();

                            }
                        });
                }
            }
        });

        BottomNavigationView navMenu = (BottomNavigationView) view.findViewById(R.id.bottom_navigation3);
        navController = Navigation.findNavController(view);

        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.playScreen:
                    case R.id.homeMenu:
                        navController.navigate(R.id.action_profileFragment_to_subjectFragment2);
                        break;
                    case R.id.leaderboard:
                        navController.navigate(R.id.action_profileFragment_to_leaderBoardFragment);

                }
                return true;
            }

        });

    }
}