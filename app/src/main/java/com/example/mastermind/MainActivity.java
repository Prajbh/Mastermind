package com.example.mastermind;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private com.example.mastermind.UserDao userDao;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        //FirebaseApp.initializeApp(getApplicationContext());
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //creates database and populates
        appDatabase=AppDatabase.getAppDatabase(MainActivity.this);
        userDao=appDatabase.userDao();
        userDao.nukeTable();
        String newQuestions[]=new String[]{"What is the capital of Chile?","What is the highest mountain in Britain?","What is the smallest country in the world?","Alberta is a province of which country?","How many countries still have the shilling as currency?","Which is the only vowel not used as the first letter in a US State?","What is the largest country in the world?","Where would you find the River Thames?","What is the hottest continent on Earth?","What is the longest river in the world?","Which of the following states of India do not have coal deposits?","How many oil refineries are there in India?","Which of the following union territories is an excellent source of geothermal energy?","Which of the following sources of energy are harmful to birds?","Which of the following gases is released during the harnessing of geothermal energy?"};
        String questAns[]=new String[]{"Santiago","Ben Nevis","Vatican City","Canada","4","E","Russia","London","Africa","Nile","Maharashtra","13","Ladakh","Wind energy","Hydrogen Sulphide"};
        String wrongAnswers[][] = {{"Copenhagen","Sucre","Belmopan"},{"Broad Peak","Ngadi Chuli","Kamet"},{"Monaco","Tuvalu","San Marino"},{"France","Belgium","Wales"},{"0","3","8"},{"A","I","U"},{"Canada","United States","China"},{"Lisbon","Tegucigalpa","Port Louis"},{"North America","South America","Asia"},{"Amazon","Yangtze","Mississippi-Missouri-Jefferson"},{"Odisha","Bihar","West Bengal"},{"11","15","17"},{"Chandigarh","Daman and Diu","Pondicherry"},{"Thermal energy","Hydel energy","Biomass"},{"Nitric Oxide","Carbon Monoxide","Propane"}};
        String historyQuestions[]=new String[]{"What did the Romans call Scotland?",
                "Who was made Lord Mayor of London In 1397, 1398, 1406 And 1419?",
                "Who was Henry VIIIs last wife?",
                "Who was the youngest British Prime Minister?",
                "In which year was Joan of Arc burned at the stake?",
                "Which nationality was the polar explorer Roald Amundsen?",
                "Who was the first female Prime Minister of Australia?",
                "Which English explorer was executed in 1618, fifteen year after being found guilty of conspiracy against King James I of England and VI of Scotland?",
                "Which English city was once known as Duroliponte?",
                "The first successful vaccine was introduced by Edward Jenner in 1796. Which disease did it guard against?",
                "Which of these countries did the Soviet Union NEVER invade?",
                "Who was the first person to orbit the Earth?",
                "Which of these cities was NOT founded by the Romans?",
                "Where did Zoroastrianism originate?",
                "Which of these writers was NOT English?"};
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
                "Smallpox",
                "Sweden",
                "Yuri Gagarin",
                "Alexandria",
                "Persia",
                "Oscar Wilde"};
        String wrongAnswersHistory[][] = {{"Hittites","Assyria","Mesopotamia"},{" Gregory de Rokesley","Andrew Buckerel","Serlo le Mercer"},{"Catherine Howard","Anne of Cleves","Anne Boleyn"},{"John Stuart","Augustus FitzRoy","Frederick North"},{"1433","1522","1421"},{"Swedish","Greek","Belgian"},{"Golda Meir","Elisabeth Domitien","Agatha Barbara"},{"Sir Francis Drake","Martin Frobisher","Sir John Hawkins"},{"London","York","Oxford"},{"Chickenpox","Polio","Tetanus"},{"Finland","Afghanistan","Poland"},{"John Glenn","Neil Armstrong","Valentina Tereshkova"},{"Cologne","London","Paris"},{"South America","Egypt","India"},{"Agatha Christie","Charles Dickens","Jane Austen"}};
        String sportsQuestions[]=new String[]{"What are the five colours of the Olympic rings?",
                "In football, which team has won the Champions League (formerly the European Cup) the most?",
                "How many players are there in a rugby league team?",
                "Which horse is the only three-time winner of the Grand National?",
                "Since 1977, where has snooker's World Championship taken place?",
                "In tennis, what piece of fruit is found at the top of the men's Wimbledon trophy?",
                "Who won the FIFA Women's World Cup in 2019?",
                "In bowling, what is the term given for three consecutive strikes?",
                "How many world titles has Phil Talyor won in darts?",
                "In golf, where does the Masters take place?",
                "What country won the 2017 World Junior Ice Hockey Championships on January 5, 2017?",
                "What horse won the 2017 Kentucky Derby on May 6th, 2017?",
                "The longest running broadcaster in TV and radio history, what legendary New York sports broadcaster died on July 15, 2017?",
                "Which NHL team won the 2017 Stanley Cup finals against the Nashville Predators?",
                "What NFL team won Super Bowl 51 in overtime on February 5, 2017?"};
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
                "Augusta National",
                "United States",
                "Always Dreaming",
                "Bob Wolff",
                "Pittsburgh Penguins",
                "New England Patriots"};
        String wrongAnswersSports[][] = {{"Blue, yellow and black","Blue, purple, yellow, black, green and red","Blue, black, green and red"},
                {"Chelsea","Atletico Madrid","Juventus"},
                {"12","8","15"},
                {"Foinavon","Aldaniti","Mr Frisk"},
                {"Globe Theatre","National Theatre","Shakespeare's Globe"},
                {"Apple","Strawberry","Mango"},
                {"China","Sweden","Germany"},
                {"three bagger","triple","sparse"},
                {"12","19","15"},
                {"Westminster National","Hartefeld National","Cypress National"},
                {"Russia","Finland","Canada"},
                {"Lookin At Lee","Classic Empire","Battle of Midway"},
                {"Howard Cosell","Jack Buck","Curt Gowdy"},
                {"Ottawa Senators","Columbus Blue Jackets","Washington Capitals"},
                {"Dallas Cowboys","Los Angeles Chargers","Atlanta Falcons"}};
        String movieQuestions[]=new String[]{"What animal was framed in the unfinished paint-by-number in Rizzo’s room in Grease?",
                "What was the name of the boat in Jaws?",
                "What are the names of the stepsisters from Disney’s Cinderella?",
                "Which six films are the only horror movies to ever compete for an Oscar?",
                "Who was the first African American to win the Academy Award for best actor?",
                "What is a nickname for the Academy Awards?",
                "Which James Bond movie was the first for Pierce Brosnan as 007?",
                "What year did Sean Connery star in the James Bond movie Dr. No?",
                "Which film written, directed, and produced by James Cameron went on to become the highest-grossing film of its time?",
                "Who directed the movie Schindler’s List?",
                "The Moon of Barods, a diamond that Marilyn Monroe wore when singing \"Diamonds Are A Girl's Best Friend\" in the film Gentlemen prefer Blondes, was auctioned off at Christies for how much in 1990?",
                "Which one of these Academy Awards did Gone With the Wind not win?",
                "Which one of these talented actors did not star in the 1989 movie \"Family Business\"?",
                "In the 1933 movie where May West spoke the line \"Come up and see me sometime\" , called She Done Him Wrong,",
                "Clint Eastwood gave us the immortal line, \"Go ahead... make my day\", in what film?"};
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
                "Steven Spielberg",
                "$297,000",
                "best actor",
                "Tom Cruise",
                "Cary Grant",
                "Dirty Harry"};
        String wrongAnswersMovies[][] = {{"Chicken","Dragon","Coyote"},
                {"Liberty","Spirit","Destiny"},
                {"Anna and Elsa","Lilo and Nani","Ariel and Arista"},
                {"The Exorcist, Jaws, The Silence of the Lambs, The Sixth Sense, Black Swan, and Halloween.","Black Box, Jaws, The Silence of the Lambs, The Sixth Sense, Black Swan, and Get Out.","The Exorcist, Jaws, The Silence of the Lambs, Don't Look Now, Black Swan, and Get Out."},
                {"Paul Winfield","Will Smith","Forest Whitaker"},
                {"Emmys","Golden Globes","Grammys"},
                {"Goldfinger","Thunderball","Dr.No"},
                {"1963","1966","1960"},
                {"Avatar","Solaris","Sanctum"},
                {"Martin Scorsese","Ridley Scott","Christopher Nolan"},
                {"$97,000","$497,000","$797,000"},
                {"best actress","best picture","best supporting actor"},
                {"Sean Connery","Matthew Broderick","Dustin Hoffman"},
                {"W.C. Fields","James Stewart","John Wayne"},
                {"Magnum Force","Sudden Impact","Tightrope"}};
        String geoLevel1Questions[]=new String[]{"Ceylon is the former name of which country?","Which is the largest US state in terms of population?","What is the official language of Brazil?","Which is the only country with a coastline on both the Red Sea and the Persian Gulf?","What is the capital of Thailand?","At which height does a hill become a mountain?","What do you call smaller rivers that flow into another river?","Which climate can reach 40 degrees Celsius during the day and 0 degrees Celsius at night?","Which two continents does Russia belong to?","What is the currency used in Britain?","How many states are in the United States of America?"," Which of these geographic features (landforms) would NOT be found in Kentucky?","When studying a globe or map, what can latitude & longitude lines help you determine?","When settlers came to the “New World,” why did they build their settlements near the ocean?","Which landform provides natural protection from enemies and is a natural barrier to weather? It often provides plenty of timber for building and fuel."};
        String geoLevel1Ans[]=new String[]{"Sri Lanka","California","Portuguese","Saudi Arabia","Bangkok","600m","Tributaries","Arid or Deserts","Europe and Asia","Pound sterling","50","Plains","absolute location","They needed the water as a trade route","Mountain"};
        String geoLevel1wrongAns[][] = {{"Bangladesh","Nepal","Pakistan"},{"Texas","Florida","New York"},{"Spanish","Italian","German"},{"India","Pakistan","Bangladesh"},{"Seoul","Jerusalem","Dhaka"},{"550m","350m","485m"},{"Stream","Channel","Peninsula"},{"Tropical","Temperate","Tropical and Continental"},{"Europe and Africa","Asia and Africa","North America and Asia"},{"Euros","Swiss","Danish Krone"},{"80","13","51"},{"Mountains","Rivers","Caves"},{"amount of rainfall in a given area","relative location","population density"},{"That's the first place they landed","They had no way to travel further inland","They needed ocean water for their crops"},{"Peninsula","Plain","Desert"}};

        String movieLevel1Questions[]=new String[]{"In the movie \"Monsters, Inc.\" what does the company Monsters, Inc. do?","In the film \"Cheaper by the Dozen\", who co-starred with Bonnie Hunt?","When Nemo was put in a fish tank in \"Finding Nemo\", what new name did the other fish give him?","Spider-Pig from what film became the shortest song to reach the British Top 40?","What major event occurred in \"The Rugrats Movie\"?","The Lorax is based on a book by which author?","Which star became a celebrity after her role as Gertie in \"E.T.\"?","What kind of animal is Winn-Dixie in the 2005 movie \"Because of Winn-Dixie\"?","In \"Bridge to Terabithia\", at which sport was Jesse trying to beat the other boys?","What kind of animal is Aslan in \"The Chronicles of Narnia\"?","What animal was framed in the unfinished paint-by-number in Rizzo’s room in Grease?",
                "What was the name of the boat in Jaws?",
                "What are the names of the stepsisters from Disney’s Cinderella?",
                "Which six films are the only horror movies to ever compete for an Oscar?",
                "Who was the first African American to win the Academy Award for best actor?"};
        String movieLevel1Ans[]=new String[]{"Generates Electricity","Steve Martin","Sharkbait","The Simpsons Movie","Baby Dil Was Born","Dr. Seuss","Drew Barrymore","Dog","Running","Lion","Horse",
                "Orca",
                "Anastasia and Drizella",
                "The Exorcist, Jaws, The Silence of the Lambs, The Sixth Sense, Black Swan, and Get Out.",
                "Sidney Poitier",
                "The Oscars",};
        String movieLevel1wrongAns[][] = {{"Sales","Laboratory","Movies"},{"Shawn Levy","Frank B. Gilbreth","Bonnie Hunt"},{"Bruce","Chum","Anchor"},{"Finding Nemo","Onward","The Godfather"},{"","",""},{"Robert Munsch","Maurice Sendak","Kate DiCamillo"},{"Erika Eleniak","Dee Wallace","Henry Thomas"},{"Frog","Pig","Cat"},{"Swimming","Baseball","Soccar"},{"Mouse","Turtle","Tiger"},{"Chicken","Dragon","Coyote"},
                {"Liberty","Spirit","Destiny"},
                {"Anna and Elsa","Lilo and Nani","Ariel and Arista"},
                {"The Exorcist, Jaws, The Silence of the Lambs, The Sixth Sense, Black Swan, and Halloween.","Black Box, Jaws, The Silence of the Lambs, The Sixth Sense, Black Swan, and Get Out.","The Exorcist, Jaws, The Silence of the Lambs, Don't Look Now, Black Swan, and Get Out."},
                {"Paul Winfield","Will Smith","Forest Whitaker"}};

        String sportsLevel1Questions[]=new String[]{"What does NBA stand for?","How many holes are played in a typical round of golf?","How many bases are there on a baseball field?","What part of the body can’t touch the ball in soccer? (Except the goalie.)","What color flag is waved in motor racing to indicate the winner?","How many regulation strokes are there in swimming?","In what sport would you find a pommel horse?","What color are the goal posts in football?","What tool is used in striking a tennis ball in the sport of tennis?","How long is a marathon?","How many players are on a baseball team?","What are the five colours of the Olympic rings?",
                "In football, which team has won the Champions League (formerly the European Cup) the most?",
                "How many players are there in a rugby league team?",
                "Which horse is the only three-time winner of the Grand National?",
                "Since 1977, where has snooker's World Championship taken place?"};
        String sportsLevel1Ans[]=new String[]{"National Basketball Association","18","4","Hands","Checkered flag ","Four","Gymnastics ","Yellow","26.2 miles ","9","Blue, yellow, black, green and red",
                "Real Madrid (13)",
                "13",
                "Red Rum",
                "Crucible Theatre"};
        String sportsLevel1wrongAns[][] = {{"National Baseball Association","National Bocce Association","National Badminton Association"},{"17","15","20"},{"3","2","5"},{"Knees","Head","Feet"},{"Black flag","White flag","Green Flag"},{"One","Five","Eight"},{"Soccar","Basketball","Baseball"},{"Green","Black","Orange"},{"26.2 miles ","26.2 miles ","26.2 miles "},{"10","8","6"},{"Blue, yellow and black","Blue, purple, yellow, black, green and red","Blue, black, green and red"},
                {"Chelsea","Atletico Madrid","Juventus"},
                {"12","8","15"},
                {"Foinavon","Aldaniti","Mr Frisk"},
                {"Globe Theatre","National Theatre","Shakespeare's Globe"}};


        String history1Questions[]=new String[]{"Alexander Graham Bell invented which of the following devices?","King received the Nobel Peace Prize for combating racial inequality through nonviolence in 1964.Complete the title of the famous Martin Luther King Jr. speech \"I have a ____\"?","Victoria was Queen of the United Kingdom from 1837 until 1901.In what century was Queen Victoria ruler of the British Empire?","The modern Olympic Games currently host more than two hundred participating nations.In which country were the Modern Olympics held for the first time in 1896?","Eriksson is considered by some historians as the first European to land in North America.Leif Eriksson was the son of which famous Viking?","The Mayan people were known for art, architecture, mathematics, the calendar and their astronomical system.The Mayan civilization was located in which modern day country?","The Titanic sank in the North Atlantic Ocean in the early morning of April 15th, 1912.Where was the Titanic heading when it hit an iceberg and sank?","Which US Apollo mission was the first to land on the moon?","Henry VIII - Henry's three wives were Seymour, Howard and Parr.Not including annulled marriages, which English King was famous for having 3 wives?","A wall divided Berlin from 1961 to 1989. Which of these cities was divided by a \"wall\" from 1961 to 1989?","What did the Romans call Scotland?",
                "Who was made Lord Mayor of London In 1397, 1398, 1406 And 1419?",
                "Who was Henry VIIIs last wife?",
                "Who was the youngest British Prime Minister?",
                "In which year was Joan of Arc burned at the stake?"};
        String history1Ans[]=new String[]{"Telephone","Dream","19th","Greece","Erik the Red","Mexico","New York","11","Henry VIII","Berlin","Caledonia",
                "Richard Whittington",
                "Catherine Parr",
                "William Pitt (The Younger)",
                "1431"};
        String history1wrongAns[][] = {{"Airplane","Car","Computer"},{"Friend","Home","Country"},{"20th","17th","18th"},{"Germany","France","Spain"},{"Rolo the White","Dan the Blue","Simon the Yellow"},{"Brazil","Peru","Russia"},{"Australia","Moscow","Paris"},{"99","13","15"},{"Edward VIII","Richard VIII","Richard the Lionheart"},{"Washington, D.C.","London","Rome"},{"Hittites","Assyria","Mesopotamia"},
                {" Gregory de Rokesley","Andrew Buckerel","Serlo le Mercer"}
                ,{"Catherine Howard","Anne of Cleves","Anne Boleyn"}
                ,{"John Stuart","Augustus FitzRoy","Frederick North"}
                ,{"1433","1522","1421"}};

        String geoLevel2Questions[]=new String[]{"Which of these U.S. states does NOT border Canada?","Which of these countries was NOT a part of the Soviet Union?","Which of these cities is NOT a national capital?"," Which of these cities DOESN'T border the Mediterranean Sea?","Which of these countries was NEVER part of the British Empire?"," Which one of these cities is NOT in the Southern Hemisphere?","Which one of these countries is NOT in Central America?","Which of these cities does NOT border the Great Lakes?","Which of these countries is NOT majority-Muslim?"," Which of these countries is NOT recognized by the United Nations?","TERI was established in the year?","To generate energy from tides, the tidal range must be above?","Which of the following uses kinetic energy for its harnessing?","Which of the following states of India has the lowest sex ratio?","Fission and fusion take place in which energy propulsion?"};
        String geoLevel2Ans[]=new String[]{"Indiana","Poland","Sydney","Lisbon","Thailand","Colombo","Suriname","Pittsburgh","Ethiopia","Taiwan","1974","5m","Hydel Energy","Haryana","Nuclear Energy"};
        String geoLevel2wrongAns[][] = {{"Maine","Minnesota","Alaska"},{"Belarus","Georgia","Ukraine"},{"Cario","Prague","Bangkok"},{"Alexandria","Monaco","Barcelona"},{"Kenya","Ireland","New Zealand"},{"Brasilla","Brisbane","Johannesburg"},{"Belize","Honduras","Panama"},{"Toronto","Chicago","Cleveland"},{"Morocco","Indonesia","Albania"},{"Cyprus","Iran","Israel"},{"1984","1994","2004"},{"7.5m","10m","12.5m"},{"Tidal Energy","Wind Energy","Geothermal Energy"},{"Bihar","Uttar Pradesh","Odisha"},{"Solar Energy","Tidal Energy","Wind Energy"}};

        String moviesLevel2Questions[]=new String[]{"Which of these is NOT a real job title that appears in movie credits?","What was the first movie in the Marvel Cinematic Universe?","Which of these actors DIDN'T appear in \"Pulp Fiction\"?","What is it called when an actor breaks character to directly address the audience?","Which of these movies was NOT directed by M. Night Shyamalan?","Which of the following is filmmaker Michael Bay known for?","Which of the following characters would you be most likely to see in a film noir movie?","What term is used to describe the the western films directed by Italian director Sergio Leone?","In the movie \"Frozen\", who is Olaf?","Which of these lines DIDN'T come from \"Monty Python and the Holy Grail\"?","In the 1951 science fiction movie, The Day The Earth Stood Still, what was the name of the robot?","Jack Walsh and Jonathan Mardukas  are the names of the two main characters in what movie?","What is the film crew's chief electrician called?","After winning the 1988 Oscar, who said \"I never thought I'd have a nomination... I never thought anybody ever took any of my pictures seriously\"?","In the 1946 movie \"The She-Wolf of London\", who played the title role?"};
        String moviesLevel2Ans[]=new String[]{"Splicer","Iron Man","John Turturro","Breaking the 4th wall","The ring","Explosions","A cynical private investigator","Spaghetti westerns","A snowman","You killed my father. Prepare to die.","Gort","Midnight Run","gaffer","Jodie Foster","June Lockhart"};
        String moviesLevel2wrongAns[][] = {{"Key grip","Gaffer","Best boy"},{"THe avengers","Spider-man","Batman"},{"Smauel Jackson","Uma THurman","Bruce WIllis"},{"Sweeping the rug","Bending the narriative","Following the loose thread"},{"Glass","The Sixth Sense","Signs"},{"Sweeping Western Landscapes","Romantic comedy","Fanciful costume design"},{"A fighter pilot","A beach volleyball player","An African tribal leader"},{"Mafia Westerns","Tuscan Westerns","Neapolitan Westerns"},{"A knight","A reindeer","A ghost"},{"It's just a flesh wound.","Bring out yer dead!","We want... a shrubbery!!"},{"Klaatu","Robby","Tightrope"},{"Midnight Express","Midnight Cowboy","Midnight sparks"},{"big L","sparks","Cher"},{"Cher","Shirley Maclaine","June Lockhart"},{"Boris Karloff","Eva Gabor","Jodie Foster"}};

        String sportsLevel2Questions[]=new String[]{"With which sport is the term “butterfly stroke” linked?","Where did the game of Chess originate?","Subroto Cup’ is associated with which game/ sports?","Santosh Trophy is associated with","Nehru Trophy is associated with","Indira Gandhi Cup is associated with","Who is named as the Flying Sikh of India?","Serena Williams is one of the top ranked sportswomen of","Hopman Cup is related with which sports?","2018 FIFA World Cup would be held in","Retaining his title, who won the 2017 World Snooker Championships on May 1st, 2017?"," Retaining his title, who won the 2017 World Snooker Championships on May 1st, 2017?","Going to the Cleveland Browns, who was the first pick in the 2017 NFL draft?","Held in Bermuda, the 2017 Louis Vuitton Cup was awarded to Team New Zealand in which sport?","Held from June 15 to 18, who won the 2017 U.S. Open Golf Championship?"};
        String sportsLevel2Ans[]=new String[]{"Swimming","India","Football","Football","Football","Boxing","Milkha Singh","Tennis","Tennis","Russia","Russell Westbrook","Mark Selby","Myles Garrett","Sailing","Brooks Koepka"};
        String sportsLevel2wrongAns[][] = {{"Boxing","Karate","Judo"},{"Persia","Arabia","Europe"},{"Hockey","Basketball","Badminton"},{"Hockey","Basketball","Badminton"},{"Hockey","Kabbadi","Table Tennis (Women)"},{"Basketball","Football","Cricket"},{"Mohinder Singh","Ajit Pal Singh","Joginder Singh"},{"Badminton","Shooting","Chess"},{"Football","Badminton","Hockey"},{"Qatar","France","Netherlands"},{"Draymond Green","James Harden","Kawhi Leonard"},{"Ding Junhui","John Higgins","Barry Hawkins"},{"Mitchell Trubisky","Leonard Fournette","Solomon Thomas"},{"Tennis","Rugby Sevens","Athletics"},{"Scott Gregory","Maverick McNealy","Brad Dalke"}};

        String historyLevel2Questions[]=new String[]{"Whose death sparked World War I?","Which of these nations was neutral in World War I?","Which of these ships was sunk by a German submarine?","Which weapon was first used at the Battle of the Somme in World War I?","World War I ended in:","Which of these people was a spy in World War I?","How many republics made up the former Soviet Union?","When was the first Nobel Prize in economics awarded?","Which book was written by Niccolò Machiavelli?"," Of what country was Simón Bolívar president?","What does a dendrochronologist use to establish dates"," In terms of weapons, what is a pike?","Why did whalers hunt sperm whales?","The spinning jenny was one of the earliest innovations in the Industrial\n" +
                "Revolution. What was a spinning jenny?","Which of the following was a result of Mao's \"Great Leap Forward\", a plan to\n" +
                "remake China's agrarian economy?"};
        String historyLevel2Ans[]=new String[]{"Archduke Franz Ferdinand","Norway","Lusitania","Tank","1918","Mata Hari","15","1969","The Prince","Peru","Tree rings","A very long spear","For oil to make candles","A device for making cloth","A famine that killed 36 million people"};
        String historyLevel2wrongAns[][] = {{"Queen Victoria","Archbishop Ussher","Kaiser Wilhelm"},{"Germany","Italy","England"},{"Arizona","Titanic","Andrea Doria"},{"Submarine","Jet fighter","Chariot"},{"1925","1920","1915"},{"James Bond","Serge Plekhanov","Benedict Arnold"},{"12","20","10"},{"1949","1909","1929"},{"The Once and Future King","The Good Earth","War and Peace"},{"Bolivia","Argentina","Chile"},{"Solar eclipses","Carbon isotopes","Ice cores"},{"An improvised explosive device","A trench knife","A spiked helmet"},{"For skin to make leather","For meat","For sport"},{"A water pump","A steam engine","A device for storing energy"},{"The overthrow of Mao","Improved standard of living","Not much really changed"}};
        List<Questions> questionsList=new ArrayList<Questions>();
        for(int i=0;i<15;i++) {
            Questions newQuest = new Questions();
            newQuest.setCategory("Geography");
            newQuest.setAnswer(questAns[i]);
            newQuest.setQuestion(newQuestions[i]);
            newQuest.setWrongAns1(wrongAnswers[i][0]);
            newQuest.setWrongAns2(wrongAnswers[i][1]);
            newQuest.setWrongAns3(wrongAnswers[i][2]);
            newQuest.setLevel("3");
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("History");
            newQuest.setAnswer(historyAns[i]);
            newQuest.setQuestion(historyQuestions[i]);
            newQuest.setWrongAns1(wrongAnswersHistory[i][0]);
            newQuest.setWrongAns2(wrongAnswersHistory[i][1]);
            newQuest.setWrongAns3(wrongAnswersHistory[i][2]);
            newQuest.setLevel("3");
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("Movies");
            newQuest.setAnswer(movieAns[i]);
            newQuest.setQuestion(movieQuestions[i]);
            newQuest.setWrongAns1(wrongAnswersMovies[i][0]);
            newQuest.setWrongAns2(wrongAnswersMovies[i][1]);
            newQuest.setWrongAns3(wrongAnswersMovies[i][2]);
            newQuest.setLevel("3");
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("Sports");
            newQuest.setAnswer(sportsAns[i]);
            newQuest.setQuestion(sportsQuestions[i]);
            newQuest.setWrongAns1(wrongAnswersSports[i][0]);
            newQuest.setWrongAns2(wrongAnswersSports[i][1]);
            newQuest.setWrongAns3(wrongAnswersSports[i][2]);
            newQuest.setLevel("3");
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("Geography");
            newQuest.setAnswer(geoLevel1Ans[i]);
            newQuest.setQuestion(geoLevel1Questions[i]);
            newQuest.setWrongAns1(geoLevel1wrongAns[i][0]);
            newQuest.setWrongAns2(geoLevel1wrongAns[i][1]);
            newQuest.setWrongAns3(geoLevel1wrongAns[i][2]);
            newQuest.setLevel("1");
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("Movies");
            newQuest.setAnswer(movieLevel1Ans[i]);
            newQuest.setQuestion(movieLevel1Questions[i]);
            newQuest.setWrongAns1(movieLevel1wrongAns[i][0]);
            newQuest.setWrongAns2(movieLevel1wrongAns[i][1]);
            newQuest.setWrongAns3(movieLevel1wrongAns[i][2]);
            newQuest.setLevel("1");
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("Sports");
            newQuest.setAnswer(sportsLevel1Ans[i]);
            newQuest.setQuestion(sportsLevel1Questions[i]);
            newQuest.setWrongAns1(sportsLevel1wrongAns[i][0]);
            newQuest.setWrongAns2(sportsLevel1wrongAns[i][1]);
            newQuest.setWrongAns3(sportsLevel1wrongAns[i][2]);
            newQuest.setLevel("1");
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("History");
            newQuest.setAnswer(history1Ans[i]);
            newQuest.setQuestion(history1Questions[i]);
            newQuest.setWrongAns1(history1wrongAns[i][0]);
            newQuest.setWrongAns2(history1wrongAns[i][1]);
            newQuest.setWrongAns3(history1wrongAns[i][2]);
            newQuest.setLevel("1");
            userDao.insert(newQuest);

            newQuest = new Questions();
            newQuest.setCategory("Geography");
            newQuest.setAnswer(geoLevel2Ans[i]);
            newQuest.setQuestion(geoLevel2Questions[i]);
            newQuest.setWrongAns1(geoLevel2wrongAns[i][0]);
            newQuest.setWrongAns2(geoLevel2wrongAns[i][1]);
            newQuest.setWrongAns3(geoLevel2wrongAns[i][2]);
            newQuest.setLevel("2");

            newQuest = new Questions();
            newQuest.setCategory("Movies");
            newQuest.setAnswer(moviesLevel2Ans[i]);
            newQuest.setQuestion(moviesLevel2Questions[i]);
            newQuest.setWrongAns1(moviesLevel2wrongAns[i][0]);
            newQuest.setWrongAns2(moviesLevel2wrongAns[i][1]);
            newQuest.setWrongAns3(moviesLevel2wrongAns[i][2]);
            newQuest.setLevel("2");

            newQuest = new Questions();
            newQuest.setCategory("Sports");
            newQuest.setAnswer(sportsLevel2Ans[i]);
            newQuest.setQuestion(sportsLevel2Questions[i]);
            newQuest.setWrongAns1(sportsLevel2wrongAns[i][0]);
            newQuest.setWrongAns2(sportsLevel2wrongAns[i][1]);
            newQuest.setWrongAns3(sportsLevel2wrongAns[i][2]);
            newQuest.setLevel("2");

            newQuest = new Questions();
            newQuest.setCategory("History");
            newQuest.setAnswer(historyLevel2Ans[i]);
            newQuest.setQuestion(historyLevel2Questions[i]);
            newQuest.setWrongAns1(historyLevel2wrongAns[i][0]);
            newQuest.setWrongAns2(historyLevel2wrongAns[i][1]);
            newQuest.setWrongAns3(historyLevel2wrongAns[i][2]);
            newQuest.setLevel("2");
            userDao.insert(newQuest);
        }


        /*Questions[] geographyQuest = userDao.loadByCategory("Geography");
        for (int i = 0; i < 8; i++) {
            Log.d("geo", geographyQuest[i].getQuestion() + " " );
            Log.d("geo",geographyQuest[i].getAnswer() + " " );
            Log.d("geo",geographyQuest[i].getWrongAns1() + " " );
            Log.d("geo",geographyQuest[i].getWrongAns2() + " " );
            Log.d("geo",geographyQuest[i].getWrongAns3() + " " );
        }*/



    }
}