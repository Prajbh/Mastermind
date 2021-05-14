package com.example.mastermind;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInFragment extends Fragment implements View.OnClickListener{

    NavController navController;
    private View rootview;
    private EditText name, email, password;
    private Button register;
    //Authenticator mAuth;
    private FirebaseAuth mAuth;
    private EditText login_UserName, login_Password;
    private Button login;
    private static final int SIGNIN = 1;
    private SignInButton signinbutton;
    private GoogleSignInOptions options;
    private GoogleSignInClient client;

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
        //initialise firebase

        //mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //public static String LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL"
        super.onViewCreated(view, savedInstanceState);
        //userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);


        navController = Navigation.findNavController(view);
        Button logInBtn = view.findViewById(R.id.logInBtn);
        Button regBtn = view.findViewById(R.id.regBtn);


        mAuth = FirebaseAuth.getInstance();



        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextInputLayout x= (TextInputLayout) getActivity().findViewById(R.id.userName);
                name = x.getEditText();
                TextInputLayout y= (TextInputLayout) getActivity().findViewById(R.id.passWord);
                password = y.getEditText();
                String get_username = name.getText().toString();
                String get_password = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(get_username, get_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("INFORMATION", "successfully inserted");
                            System.out.println("Successfully inserted");
                        }
                        else{
                            Log.d("INFORMATION", "unsuccessfulinserted");
                            System.out.println("not inserted");
                        }
                    }
                });
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