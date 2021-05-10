package com.example.mastermind;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.Authenticator;

public class LogInFragment extends Fragment implements View.OnClickListener{

    NavController navController;
    private EditText name, email, password;
    private Button register;
    Authenticator mAuth;

    //private UserViewModel userViewModel;
    private SavedStateHandle savedStateHandle;


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

        //public static String LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL"
        super.onViewCreated(view, savedInstanceState);
        //userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);


        navController = Navigation.findNavController(view);
        Button logInBtn = view.findViewById(R.id.logInBtn);
        Button regBtn = view.findViewById(R.id.regBtn);
        EditText name = view.findViewById(R.id.userName);
        EditText email = view.findViewById(R.id.email);
        EditText password = view.findViewById(R.id.passWord);

        //mAuth = FirebaseAuth.getInstance();


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get_username = name.getText().toString();
                String get_password = password.getText().toString();
                login(get_username, get_password);
                navController.navigate(R.id.action_logInFragment_to_ageFragment);
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new MediaPlayer.OnCompletionListener<AuthResult>());
                navController.navigate(R.id.action_logInFragment_to_signUpFragment);
            }
        });
    }

    private void login(String get_username, String get_password) {
        /*userViewModel.login(username, password).observe(viewLifecycleOwner, (Observer<LoginResult>) result -> {
            if (result.success) {
                savedStateHandle.set(LOGIN_SUCCESSFUL, true);
                NavHostFragment.findNavController(this).popBackStack();
            } else {
                showErrorMessage();
            }*/
    }

    @Override
    public void onClick(View v) {

    }
}