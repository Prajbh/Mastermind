package com.example.mastermind;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import java.util.ArrayList;
@Dao
public interface UserDao {
    @Query("select * from QUESTIONS LIMIT 1")
    Questions getOne();

    @Insert
    void insert(Questions questions);

    @Delete
    void delete(Questions questions);

    @Query("select * from QUESTIONS where questions.catagory like :catagory LIMIT 10 ")
    Questions[] getCatagory(String catagory);
}
