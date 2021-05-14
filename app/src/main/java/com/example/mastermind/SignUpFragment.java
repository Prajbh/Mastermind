package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.media.MediaPlayer;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import java.net.Authenticator;


public class SignUpFragment extends Fragment implements View.OnClickListener{

    NavController navController;
    private EditText name, email, password;
    private Button register;
    Authenticator mAuth;

    public SignUpFragment() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        navController = Navigation.findNavController(view);
        Button regBtn = view.findViewById(R.id.newUserBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_logInFragment_to_ageFragment);
            }
        });
        /*
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_logInFragment_to_signUpFragment);
            }
        });
        */

    }


    @Override
    public void onClick(View v) {

    }
}