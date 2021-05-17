package com.example.mastermind;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

import android.content.Context;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<User> usersArrayList;
    private Context context;

    // creating constructor for our adapter class
    public CustomAdapter(ArrayList<User> usersArrayList, Context context) {
        this.usersArrayList = usersArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        User users= usersArrayList.get(position);
        holder.name.setText(users.getName());
        holder.score.setText(String.valueOf(users.getScore()));
        holder.index.setText(String.format("#%d",position+1));



    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return usersArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView name;
        private final TextView score;
        private final TextView index;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            name = itemView.findViewById(R.id.name1);
            score = itemView.findViewById(R.id.score1);
            index = itemView.findViewById(R.id.index);

        }
    }
}

