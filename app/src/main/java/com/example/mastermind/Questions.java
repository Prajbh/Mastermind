package com.example.mastermind;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Questions {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name="category")
    private String category;

    @ColumnInfo(name="questions")
    private String question;

    @ColumnInfo(name="answer")
    private String answer;

    @ColumnInfo(name="wrongAns1")
    private String wrongAns1;

    @ColumnInfo(name="wrongAns2")
    private String wrongAns2;

    @ColumnInfo(name="wrongAns3")
    private String wrongAns3;

    @ColumnInfo(name="level")
    private String level;
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

    public void setAnswer(String answer) {
        this.answer=answer;
    }
    public String getAnswer()
    {
        return answer;
    }

    public void setWrongAns1(String wrongAns1){this.wrongAns1=wrongAns1;}
    public String getWrongAns1(){return wrongAns1;}

    public void setWrongAns2(String wrongAns2){this.wrongAns2=wrongAns2;}
    public String getWrongAns2(){return wrongAns2;}

    public void setWrongAns3(String wrongAns3){this.wrongAns3=wrongAns3;}
    public String getWrongAns3(){return wrongAns3;}

    public void setLevel(String level){this.level=level;}
    public String getLevel(){return level;}

}
