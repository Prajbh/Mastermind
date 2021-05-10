package com.example.mastermind;

import android.os.Bundle;

import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.net.Authenticator;

public class LogInFragment extends Fragment implements View.OnClickListener{

    NavController navController;
    private EditText name, email, password;
    private Button register;
    Authenticator mAuth;



    public LogInFragment() {

        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);
        Button logInBtn = view.findViewById(R.id.logInBtn);
        Button regBtn = view.findViewById(R.id.regBtn);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_logInFragment_to_ageFragment);
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_logInFragment_to_signUpFragment);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}