package com.example.mastermind;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Questions {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name="catagory")
    private String category;

    @ColumnInfo(name="questions")
    private String question;

    @ColumnInfo(name="answer")
    private String answer;

    public int getUid()
    {
        return uid;
    }
    public void setUid(int uid)
    {
        this.uid=uid;
    }

    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category=category;
    }

    public String getQuestion()
    {
        return question;
    }
    public void setQuestion(String question)
    {
        this.question=question;
    }

    public String getAnswer()
    {
        return answer;
    }
    public void setAnswer(String actual_question) {this.answer=answer; }
}


