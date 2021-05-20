import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mastermind.R;

import java.util.ArrayList;
import java.util.Locale;


public class Image_ques extends Fragment {

    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private EditText editText;
    private ImageView micButton;
    private Button buttonConfirmNext;
    private boolean isAnswered;
    private TextView textViewScore;
    private int score = 0;
    private ImageView mImageView;
    private int counter = 0;
    NavController navController;
    private TextView questionCount;
    private TextView question;



    public Image_ques() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_ques2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        editText = view.findViewById(R.id.speechtext);
        micButton = view.findViewById(R.id.imageButton3);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
        textViewScore = view.findViewById(R.id.Score1);
        questionCount = view.findViewById(R.id.questionCounter1);
        buttonConfirmNext = view.findViewById(R.id.confirm1);
        mImageView = view.findViewById(R.id.imageView);
        mImageView.setImageResource(R.drawable.image1);
        question = view.findViewById(R.id.questionsTextView);
        question.setText("What animal is this?");


        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                editText.setText("");
                editText.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                micButton.setImageResource(R.drawable.ic_baseline_mic_24);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                editText.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        micButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    speechRecognizer.stopListening();
                    // get text from textbox
                    //String ans = editText.toString();
                    //ans = ans.toLowerCase();
                    //if(ans.equals("elephant") {
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    micButton.setImageResource(R.drawable.ic_baseline_mic_24);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isAnswered) {
                    checkAnswers(editText.getText().toString());

                } else {
                    counter++;
                    showNextQuestion(counter);

                }


                //if(counter == 5){
                //Toast.makeText(getActivity(), "End of quiz", Toast.LENGTH_SHORT).show();
                // pass score to showScore fragment
                //Bundle bundle = new Bundle();
                //bundle.putInt("score", score);
                //navController.navigate(R.id.action_geoQuestionFragment_to_showScoreFragment, bundle);

            }

            private void showNextQuestion(int counter) {
                isAnswered = false;
                if (counter < 6) {
                    questionCount.setText("Question: " + (counter) + "/5");
                    if (counter == 2) {
                        mImageView.setImageResource(R.drawable.fresh_red_apple_stock_photo_167147);
                        question.setText("What fruit is this?");
                        buttonConfirmNext.setText("Confirm");
                    } else if (counter == 3) {
                        mImageView.setImageResource(R.drawable.google);
                        question.setText("What logo is this?");
                        buttonConfirmNext.setText("Confirm");
                    } else if (counter == 4) {
                        mImageView.setImageResource(R.drawable.horse);
                        question.setText("What animal is this?");
                        buttonConfirmNext.setText("Confirm");
                    } else if (counter == 5) {
                        mImageView.setImageResource(R.drawable.train);
                        question.setText("What is this?");
                        buttonConfirmNext.setText("Confirm");
                    }

                    //buttonConfirmNext.setText("Confirm");

                } else {
                    Toast.makeText(getActivity(), "End of quiz", Toast.LENGTH_SHORT).show();
                    // pass score to showScore fragment
                    Bundle bundle = new Bundle();
                    bundle.putInt("score", score);
                    navController.navigate(R.id.action_image_ques_to_showScoreFragment, bundle);
                    //Toast.makeText(getActivity(), "End of quiz", Toast.LENGTH_SHORT).show();
                }

            }


            private void checkAnswers(String ans) {
                isAnswered = true;
                //countDownTimer.cancel();
                String answer = ans;
                answer = answer.toLowerCase();
                if(counter < 6){
                    if(counter == 1){
                        if (answer.equals("elephant")) {
                            //if (optionD.getText().toString().equals(geographyQuest[counter - 1].getAnswer())) {
                            score++;
                            Toast.makeText(getActivity(), "the answer is right", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "the answer is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(counter == 2){
                        if (answer.equals("apple")) {
                            //if (optionD.getText().toString().equals(geographyQuest[counter - 1].getAnswer())) {
                            score++;
                            Toast.makeText(getActivity(), "the answer is right", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "the answer is wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(counter == 3){
                        if (answer.equals("google")) {
                            //if (optionD.getText().toString().equals(geographyQuest[counter - 1].getAnswer())) {
                            score++;
                            Toast.makeText(getActivity(), "the answer is right", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "the answer is wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(counter == 4){
                        if (answer.equals("horse")) {
                            //if (optionD.getText().toString().equals(geographyQuest[counter - 1].getAnswer())) {
                            score++;
                            Toast.makeText(getActivity(), "the answer is right", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "the answer is wrong", Toast.LENGTH_SHORT).show();
                        }

                    }

                    if(counter == 5){
                        if (answer.equals("train")) {
                            //if (optionD.getText().toString().equals(geographyQuest[counter - 1].getAnswer())) {
                            score++;
                            Toast.makeText(getActivity(), "the answer is right", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "the answer is wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }



                //}
                textViewScore.setText("Score: " + score + "/5" );
                //showSolution(geographyQuest);
                //Toast.makeText(getActivity(), "the answer is Elephant", Toast.LENGTH_SHORT).show();

            }

        });
    }



    @Override
    public void onDestroy () {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }


}
