package com.example.mastermind;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Questions.class},version=1,exportSchema=false)
abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract com.example.mastermind.UserDao userDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "test1.db").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

}