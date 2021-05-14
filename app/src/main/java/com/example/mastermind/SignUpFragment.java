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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpFragment extends Fragment implements View.OnClickListener{

    NavController navController;
    private EditText name, email, password;
    private Button register;
    private FirebaseAuth mAuth;
    private TextInputLayout x,y;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

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
        Button addUserBtn = view.findViewById(R.id.newUserBtn);
        x=getView().findViewById(R.id.userName22);
        y=getView().findViewById(R.id.passWord22);
        addUserBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //name = x.getEd.getEditText();
        //password = y.getEditText();
        String get_username = x.getEditText().getText().toString().trim();
        String get_password = y.getEditText().getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(get_username, get_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("testingg", "Successfully inserted");
                    navController.navigate(R.id.action_signUpFragment_to_ageFragment);
                }
                else{
                    Log.d("testingg", "Unsuccessfully inserted");
                }
            }
        });


    }
}