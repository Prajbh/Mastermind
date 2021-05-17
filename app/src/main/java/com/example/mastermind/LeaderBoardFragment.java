package com.example.mastermind;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


import com.example.mastermind.databinding.FragmentLeaderBoardBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.widget.Toast;


public class LeaderBoardFragment extends Fragment {

    NavController navController;
    FragmentLeaderBoardBinding binding;


    public LeaderBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_leader_board, container, false);


        //return inflater.inflate(R.layout.fragment_leader_board, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView rV = view.findViewById(R.id.recyclerView);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ArrayList<User> usersArrayList = new ArrayList<User>();
        CustomAdapter adapter = new CustomAdapter(usersArrayList, getContext());

        rV.setAdapter(adapter);
        rV.setLayoutManager(new LinearLayoutManager(getContext()));

        db.collection("users")
                .orderBy("score", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        User user = d.toObject(User.class);
                        usersArrayList.add(user);
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                }

            }
        });



        BottomNavigationView navMenu = (BottomNavigationView) view.findViewById(R.id.bottom_navigation1);
        navController = Navigation.findNavController(view);
        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.subjectMenu:
                        navController.navigate(R.id.action_leaderBoardFragment_to_DifficultyFragment);
                        break;
                    case R.id.homeMenu:
                        navController.navigate(R.id.action_leaderBoardFragment_to_homeFragment);
                        break;
                }
                return true;
            }
        });


    }

}












