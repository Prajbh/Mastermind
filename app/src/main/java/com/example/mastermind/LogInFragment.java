package com.example.mastermind;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

public class LogInFragment extends Fragment {

    NavController navController;
    FirebaseAuth auth;
    TextInputLayout userEmail, userPass;
    private static final int SIGNIN = 1;
    private SignInButton signinbutton;
    private GoogleSignInOptions options;
    private GoogleSignInClient client;
    //private FirebaseAuth mAuth;

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

        //super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        Button logInBtn = view.findViewById(R.id.logInBtn);
        Button regBtn = view.findViewById(R.id.regBtn);
        SignInButton googleButton = view.findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        //String user, pass;
        userEmail =  view.findViewById(R.id.userName);
        userPass =  view.findViewById(R.id.passWord);

        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("619630226950-gks8av6hjdcu3f64kfie0d2c6v1mj7rc.apps.googleusercontent.com")
                .requestEmail().build();
        client = GoogleSignIn.getClient(getContext(), gso);*/

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user, pass;
                user = userEmail.getEditText().getText().toString().trim();
                pass = userPass.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(getActivity(), "Please Enter Your Information", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.action_logInFragment_to_subjectFragment2);
                            } else {
                                Toast.makeText(getActivity(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_logInFragment_to_signUpFragment);
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signToGoogle();

            }

        });
        configureSignIn();
    }
    private void signToGoogle() {
        Intent signinIntent = client.getSignInIntent();
        startActivityForResult(signinIntent, SIGNIN);
        //configureSignIn();
    }

    private void configureSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("619630226950-gks8av6hjdcu3f64kfie0d2c6v1mj7rc.apps.googleusercontent.com")
                .requestEmail().build();
        client = GoogleSignIn.getClient(getContext(), gso);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGNIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handledSignIn(task);
            //GoogleSignInAccount account = null;
            //try {
            //    account = task.getResult(ApiException.class);
            //    firebaseauthwithGoogle(account.getIdToken());
            //} catch (ApiException e) {
             //   e.printStackTrace();
            //}


        }
    }

    private void firebaseauthwithGoogle(String idToken) {
        AuthCredential credencial = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credencial)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            //task is successfull
                            //Log.d(TAG, "signInWithCredential:success");
                            //FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            //task not successfull
                            Toast.makeText(getActivity(), "Login not Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void handledSignIn(Task<GoogleSignInAccount> task) {

        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.action_logInFragment_to_subjectFragment2);

        try {
            GoogleSignInAccount account = task.getResult();
            firebaseauthwithGoogle(account.getIdToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}