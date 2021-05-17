package com.example.mastermind;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

public class LogInFragment extends Fragment implements View.OnClickListener {

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
    private FirebaseAuth mauth;
    //private UserViewModel userViewModel;
    private SavedStateHandle savedStateHandle;
    private TextInputLayout x, y;

    public LogInFragment() {

        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mauth = FirebaseAuth.getInstance();

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
        SignInButton googleButton = view.findViewById(R.id.button);
        x = getView().findViewById(R.id.userName);
        y = getView().findViewById(R.id.passWord);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("969262305380-b4gok644spmqui0bv8v5e8npa52tat5k.apps.googleusercontent.com")
                .requestEmail().build();

        client = GoogleSignIn.getClient(getContext(), gso);
        mAuth = FirebaseAuth.getInstance();


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1, s2;
                s1 = x.getEditText().getText().toString().trim();
                s2 = y.getEditText().getText().toString().trim();
                mauth.signInWithEmailAndPassword(s1, s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User login was successful", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getActivity(), "User login was unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                navController.navigate(R.id.action_logInFragment_to_ageFragment);
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


    }


    //start of google sign in
    /*private void configureSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("969262305380-b4gok644spmqui0bv8v5e8npa52tat5k.apps.googleusercontent.com")
                .requestEmail().build();
        client = GoogleSignIn.getClient(getContext(), gso);

    }*/

    private void signToGoogle() {
        Intent signinIntent = client.getSignInIntent();
        startActivityForResult(signinIntent, SIGNIN);
        //configureSignIn();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGNIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //handledSignIn(task);
            GoogleSignInAccount account = null;
            try {
                account = task.getResult(ApiException.class);
                firebaseauthwithGoogle(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
            }


        }
    }

    private void firebaseauthwithGoogle(String idToken) {
        AuthCredential credencial = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credencial)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //task is successfull
                            //Log.d(TAG, "signInWithCredential:success");
                            //FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            //task not successfull
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    private void handledSignIn(Task<GoogleSignInAccount> task) {

        try {
            GoogleSignInAccount account = task.getResult();
            firebaseauthwithGoogle(account.getIdToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //end of google sign-in


    @Override
    public void onClick(View view) {

    }
}
