package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private com.example.mastermind.UserDao userDao;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //creates database and populates
        appDatabase=AppDatabase.getAppDatabase(MainActivity.this);
        userDao=appDatabase.userDao();
        userDao.nukeTable();
        String newQuestions[]=new String[]{"What is the capital of Chile?","What is the highest mountain in Britain?","What is the smallest country in the world?","Alberta is a province of which country?","How many countries still have the shilling as currency?","Which is the only vowel not used as the first letter in a US State?","What is the largest country in the world?","Where would you find the River Thames?","What is the hottest continent on Earth?","What is the longest river in the world?"};
        String questAns[]=new String[]{"Santiago","Ben Nevis","Vatican City","Canada","4","E","Russia","London","Africa","Nile"};
        String wrongAnswers[][] = {{"Copenhagen","Sucre","Belmopan"},{"Broad Peak","Ngadi Chuli","Kamet"},{"Monaco","Tuvalu","San Marino"},{"France","Belgium","Wales"},{"0","3","8"},{"A","I","U"},{"Canada","United States","China"},{"Lisbon","Tegucigalpa","Port Louis"},{"North America","South America","Asia"},{"Amazon","Yangtze","Mississippi-Missouri-Jefferson"}};
        String historyQuestions[]=new String[]{"What did the Romans call Scotland?",
                "Who was made Lord Mayor of London In 1397, 1398, 1406 And 1419?",
                "Who was Henry VIIIs last wife?",
                "Who was the youngest British Prime Minister?",
                "In which year was Joan of Arc burned at the stake?",
                "Which nationality was the polar explorer Roald Amundsen?",
                "Who was the first female Prime Minister of Australia?",
                "Which English explorer was executed in 1618, fifteen year after being found guilty of conspiracy against King James I of England and VI of Scotland?",
                "Which English city was once known as Duroliponte?",
                "The first successful vaccine was introduced by Edward Jenner in 1796. Which disease did it guard against?"};
        String historyAns[]=new String[]{
                "Caledonia",
                "Richard Whittington",
                "Catherine Parr",
                "William Pitt (The Younger)",
                "1431",
                "Norwegian",
                "Julia Gillard (2010-2013)",
                "Sir Walter Raleigh",
                "Cambridge",
                "Smallpox"};
        String wrongAnswersHistory[][] = {{"Hittites","Assyria","Mesopotamia"},{" Gregory de Rokesley","Andrew Buckerel","Serlo le Mercer"},{"Catherine Howard","Anne of Cleves","Anne Boleyn"},{"John Stuart","Augustus FitzRoy","Frederick North"},{"1433","1522","1421"},{"Swedish","Greek","Belgian"},{"Golda Meir","Elisabeth Domitien","Agatha Barbara"},{"Sir Francis Drake","Martin Frobisher","Sir John Hawkins"},{"London","York","Oxford"},{"Chickenpox","Polio","Tetanus"}};
        String sportsQuestions[]=new String[]{"What are the five colours of the Olympic rings?",
                "In football, which team has won the Champions League (formerly the European Cup) the most?",
                "How many players are there in a rugby league team?",
                "Which horse is the only three-time winner of the Grand National?",
                "Since 1977, where has snooker's World Championship taken place?",
                "In tennis, what piece of fruit is found at the top of the men's Wimbledon trophy?",
                "Who won the FIFA Women's World Cup in 2019?",
                "In bowling, what is the term given for three consecutive strikes?",
                "How many world titles has Phil Talyor won in darts?",
                "In golf, where does the Masters take place?"};
        String sportsAns[]=new String[]{
                "Blue, yellow, black, green and red",
                "Real Madrid (13)",
                "13",
                "Red Rum",
                "Crucible Theatre",
                "Pineapple",
                "USA",
                "turkey",
                "16",
                "Augusta National"};
        String wrongAnswersSports[][] = {{"Blue, yellow and black","Blue, purple, yellow, black, green and red","Blue, black, green and red"},
                {"Chelsea","Atletico Madrid","Juventus"},
                {"12","8","15"},
                {"Foinavon","Aldaniti","Mr Frisk"},
                {"Globe Theatre","National Theatre","Shakespeare's Globe"},
                {"Apple","Strawberry","Mango"},
                {"China","Sweden","Germany"},
                {"three bagger","triple","sparse"},
                {"12","19","15"},
                {"Westminster National","Hartefeld National","Cypress National"}};
        String movieQuestions[]=new String[]{"What animal was framed in the unfinished paint-by-number in Rizzo’s room in Grease?",
                "What was the name of the boat in Jaws?",
                "What are the names of the stepsisters from Disney’s Cinderella?",
                "Which six films are the only horror movies to ever compete for an Oscar?",
                "Who was the first African American to win the Academy Award for best actor?",
                "What is a nickname for the Academy Awards?",
                "Which James Bond movie was the first for Pierce Brosnan as 007?",
                "What year did Sean Connery star in the James Bond movie Dr. No?",
                "Which film written, directed, and produced by James Cameron went on to become the highest-grossing film of its time?",
                "Who directed the movie Schindler’s List?"};
        String movieAns[]=new String[]{
                "Horse",
                "Orca",
                "Anastasia and Drizella",
                "The Exorcist, Jaws, The Silence of the Lambs, The Sixth Sense, Black Swan, and Get Out.",
                "Sidney Poitier",
                "The Oscars",
                "GoldenEye",
                "1962",
                "Titanic",
                "Steven Spielberg"};
        String wrongAnswersMovies[][] = {{"Chicken","Dragon","Coyote"},
                {"Liberty","Spirit","Destiny"},
                {"Anna and Elsa","Lilo and Nani","Ariel and Arista"},
                {"The Exorcist, Jaws, The Silence of the Lambs, The Sixth Sense, Black Swan, and Halloween.","Black Box, Jaws, The Silence of the Lambs, The Sixth Sense, Black Swan, and Get Out.","The Exorcist, Jaws, The Silence of the Lambs, Don't Look Now, Black Swan, and Get Out."},
                {"Paul Winfield","Will Smith","Forest Whitaker"},
                {"Emmys","Golden Globes","Grammys"},
                {"Goldfinger","Thunderball","Dr.No"},
                {"1963","1966","1960"},
                {"Avatar","Solaris","Sanctum"},
                {"Martin Scorsese","Ridley Scott","Christopher Nolan"}};
        /*
        String wrongAnswersMovies[][] = {{"","",""},
                {"","",""},
                {"","",""},
                {"","",""},
                {"","",""},
                {"","",""},
                {"","",""},
                {"","",""},
                {"","",""},
                {""," ",""}};
        */
        List<Questions> questionsList=new ArrayList<Questions>();
        for(int i=0;i<10;i++) {
            Questions newQuest = new Questions();
            newQuest.setCategory("Geography");
            newQuest.setAnswer(questAns[i]);
            newQuest.setQuestion(newQuestions[i]);
            newQuest.setWrongAns1(wrongAnswers[i][0]);
            newQuest.setWrongAns2(wrongAnswers[i][1]);
            newQuest.setWrongAns3(wrongAnswers[i][2]);
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("History");
            newQuest.setAnswer(historyAns[i]);
            newQuest.setQuestion(historyQuestions[i]);
            newQuest.setWrongAns1(wrongAnswersHistory[i][0]);
            newQuest.setWrongAns2(wrongAnswersHistory[i][1]);
            newQuest.setWrongAns3(wrongAnswersHistory[i][2]);
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("Movies");
            newQuest.setAnswer(movieAns[i]);
            newQuest.setQuestion(movieQuestions[i]);
            newQuest.setWrongAns1(wrongAnswersMovies[i][0]);
            newQuest.setWrongAns2(wrongAnswersMovies[i][1]);
            newQuest.setWrongAns3(wrongAnswersMovies[i][2]);
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("Sports");
            newQuest.setAnswer(sportsAns[i]);
            newQuest.setQuestion(sportsQuestions[i]);
            newQuest.setWrongAns1(wrongAnswersSports[i][0]);
            newQuest.setWrongAns2(wrongAnswersSports[i][1]);
            newQuest.setWrongAns3(wrongAnswersSports[i][2]);
            userDao.insert(newQuest);
        }


        Questions[] geographyQuest = userDao.loadByCategory("Geography");
        for (int i = 0; i < 8; i++) {
            Log.d("geo", geographyQuest[i].getQuestion() + " " );
            Log.d("geo",geographyQuest[i].getAnswer() + " " );
            Log.d("geo",geographyQuest[i].getWrongAns1() + " " );
            Log.d("geo",geographyQuest[i].getWrongAns2() + " " );
            Log.d("geo",geographyQuest[i].getWrongAns3() + " " );
        }



    }
}