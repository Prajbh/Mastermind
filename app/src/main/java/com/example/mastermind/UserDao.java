
package com.example.mastermind;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mastermind.Questions;

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

    @Query("SELECT * FROM questions WHERE category LIKE:subject")
    public Questions[] loadByCategory(String subject);

    @Query("Delete FROM Questions")
    public void nukeTable();

    @Query("SELECT * FROM questions WHERE category LIKE:subject AND :level")
    public Questions[] loadSetOfQuestions(String subject, String level);











}