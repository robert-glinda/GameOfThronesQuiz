package com.example.android.gameofthronesquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String KEY_QUESTION1 = "StateQuestion1";
    static final String KEY_QUESTION4 = "StateQuestion4";
    static final String KEY_SUBMITTED = "StateSubmitted";
    int valueQuestion1 = 0;
    String savedAnswerQuestion4;
    boolean submitted = false;
    boolean isCorrectAnswer1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Toast.makeText(this, "Please scroll down for more questions.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method is called when the + button in Question 1 is clicked.
     */
    public void increment(View view) {
        if (valueQuestion1 == 9) {
            Toast.makeText(this, "That's too much", Toast.LENGTH_SHORT).show();
            return;
        }
        valueQuestion1 = valueQuestion1 + 1;
        displayValueQuestion1(valueQuestion1);
    }

    /**
     * This method is called when the - button in Question 1 is clicked.
     */
    public void decrement(View view) {
        if (valueQuestion1 == 0) {
            Toast.makeText(this, "Common! Seriously?", Toast.LENGTH_SHORT).show();
            return;
        }
        valueQuestion1 = valueQuestion1 - 1;
        displayValueQuestion1(valueQuestion1);
    }

    /**
     * This method displays the given  value for Question 1 on the screen.
     */
    private void displayValueQuestion1(int number) {
        TextView valueQ1TextView = findViewById(R.id.valueQuestion1_text_view);
        valueQ1TextView.setText("" + number);
    }

    /**
     * This method displays the given answer for Question 3 on the screen after app restart.
     */
    private void displayAnswerQuestion4(String answer) {
        EditText answerQuestion4_Input = findViewById(R.id.question4_answer_input);
        answerQuestion4_Input.setText(answer);
    }

    /**
     * Method for saving data.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_QUESTION1, valueQuestion1);
        savedInstanceState.putBoolean(KEY_SUBMITTED, submitted);
        EditText answerQuestion4_Input = findViewById(R.id.question4_answer_input);
        String answerQuestion4 = answerQuestion4_Input.getText().toString();
        savedInstanceState.putString(KEY_QUESTION4, answerQuestion4);
    }

    /**
     * Method for restoring and displaying data after switching device orientation.
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            valueQuestion1 = savedInstanceState.getInt(KEY_QUESTION1);
            savedAnswerQuestion4 = savedInstanceState.getString(KEY_QUESTION4);
            submitted = savedInstanceState.getBoolean(KEY_SUBMITTED);
        }
        displayValueQuestion1(valueQuestion1);
        displayAnswerQuestion4(savedAnswerQuestion4);
        if (submitted) {
            countScore();
        }
    }

    /**
     * Method that evaluates the score and displays correct / incorrect answers.
     */
    public void submitScore(View view) {
        if (!submitted) {
            int score = countScore();
            Toast.makeText(this, "Final score: " + score + "/4", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method that evaluates the score and displays correct / incorrect answers.
     * It is also called by onRestoreInstanceState  to show the correct / incorrect answers
     * in case of orientation change after the score was already counted.
     */
    public int countScore() {
        submitted = true;
        int score = 0;
        TextView valueQ1TextView = findViewById(R.id.valueQuestion1_text_view);
        TextView correctAnswer1 = findViewById(R.id.correctAnswer1);
        TextView correctAnswer2 = findViewById(R.id.correctAnswer2);
        TextView correctAnswer3 = findViewById(R.id.correctAnswer3);
        TextView correctAnswer4 = findViewById(R.id.correctAnswer4);
        RadioButton question2_button1 = findViewById(R.id.question2_button1);
        RadioButton question2_button2 = findViewById(R.id.question2_button2);
        RadioButton question2_button3 = findViewById(R.id.question2_button3);
        RadioButton question2_button4 = findViewById(R.id.question2_button4);
        CheckBox question3_checkbox1 = findViewById(R.id.question3_checkbox1);
        CheckBox question3_checkbox2 = findViewById(R.id.question3_checkbox2);
        CheckBox question3_checkbox3 = findViewById(R.id.question3_checkbox3);
        CheckBox question3_checkbox4 = findViewById(R.id.question3_checkbox4);
        CheckBox question3_checkbox5 = findViewById(R.id.question3_checkbox5);
        CheckBox question3_checkbox6 = findViewById(R.id.question3_checkbox6);
        CheckBox question3_checkbox7 = findViewById(R.id.question3_checkbox7);
        CheckBox question3_checkbox8 = findViewById(R.id.question3_checkbox8);
        EditText answerQuestion4_Input = findViewById(R.id.question4_answer_input);
        String question4_answer = answerQuestion4_Input.getText().toString().toUpperCase();

        if (valueQuestion1 == 6) {
            isCorrectAnswer1 = true;
            score += 1;
            valueQ1TextView.setBackgroundColor(0x5500FF00);
            valueQ1TextView.setText("" + valueQuestion1);
            String correct = getString(R.string.correct);
            correctAnswer1.setText(correct);
        } else {
            valueQ1TextView.setBackgroundColor(0x55FF0000);
            valueQ1TextView.setText("" + valueQuestion1);
            String incorrectAnswer1 = getString(R.string.incorrectAnswer1);
            correctAnswer1.setText(incorrectAnswer1);
        }

        if (question2_button2.isChecked()) {
            score += 1;
            question2_button2.setBackgroundColor(0x5500FF00);
            String correct = getString(R.string.correct);
            correctAnswer2.setText(correct);
        } else if (question2_button1.isChecked()) {
            question2_button1.setBackgroundColor(0x55FF0000);
            question2_button2.setBackgroundColor(0x5500FF00);
            String incorrect = getString(R.string.incorrect);
            correctAnswer2.setText(incorrect);
        } else if (question2_button3.isChecked()) {
            question2_button3.setBackgroundColor(0x55FF0000);
            question2_button2.setBackgroundColor(0x5500FF00);
            String incorrect = getString(R.string.incorrect);
            correctAnswer2.setText(incorrect);
        } else if (question2_button4.isChecked()) {
            question2_button4.setBackgroundColor(0x55FF0000);
            question2_button2.setBackgroundColor(0x5500FF00);
            String incorrect = getString(R.string.incorrect);
            correctAnswer2.setText(incorrect);
        } else if (!question2_button1.isChecked() && !question2_button2.isChecked() && !question2_button3.isChecked() && !question2_button4.isChecked()) {
            question2_button2.setBackgroundColor(0x5500FF00);
            String incorrect = getString(R.string.incorrect);
            correctAnswer2.setText(incorrect);
        }

        if (question3_checkbox1.isChecked() && !question3_checkbox2.isChecked() && question3_checkbox3.isChecked()
                && question3_checkbox4.isChecked() && !question3_checkbox5.isChecked() && question3_checkbox6.isChecked()
                && question3_checkbox7.isChecked() && question3_checkbox8.isChecked()) {
            score += 1;
            question3_checkbox1.setBackgroundColor(0x5500FF00);
            question3_checkbox3.setBackgroundColor(0x5500FF00);
            question3_checkbox4.setBackgroundColor(0x5500FF00);
            question3_checkbox6.setBackgroundColor(0x5500FF00);
            question3_checkbox7.setBackgroundColor(0x5500FF00);
            question3_checkbox8.setBackgroundColor(0x5500FF00);
            String correct = getString(R.string.correct);
            correctAnswer3.setText(correct);
        } else {
            question3_checkbox1.setBackgroundColor(0x5500FF00);
            if (question3_checkbox2.isChecked()) {
                question3_checkbox2.setBackgroundColor(0x55FF0000);
            }
            question3_checkbox3.setBackgroundColor(0x5500FF00);
            question3_checkbox4.setBackgroundColor(0x5500FF00);
            if (question3_checkbox5.isChecked()) {
                question3_checkbox5.setBackgroundColor(0x55FF0000);
            }
            question3_checkbox6.setBackgroundColor(0x5500FF00);
            question3_checkbox7.setBackgroundColor(0x5500FF00);
            question3_checkbox8.setBackgroundColor(0x5500FF00);
            String incorrect = getString(R.string.incorrect);
            correctAnswer3.setText(incorrect);
        }

        if (question4_answer.equals("STARK")
                || question4_answer.equals(" STARK")
                || question4_answer.equals("  STARK")
                || question4_answer.equals("HOUSE STARK")
                || question4_answer.equals("HOUSE  STARK")
                || question4_answer.equals("HOUSE   STARK")
                || question4_answer.equals("HOUSESTARK")
                || question4_answer.equals(" HOUSESTARK")
                || question4_answer.equals("  HOUSESTARK")
                || question4_answer.equals(" HOUSE STARK")
                || question4_answer.equals("  HOUSE STARK")
                || question4_answer.equals("   HOUSE STARK")
                || question4_answer.equals(" HOUSE  STARK")
                || question4_answer.equals("  HOUSE  STARK")
                || question4_answer.equals("   HOUSE  STARK")
                || question4_answer.equals(" HOUSE   STARK")
                || question4_answer.equals("  HOUSE   STARK")
                || question4_answer.equals("   HOUSE   STARK")) {
            score += 1;
            answerQuestion4_Input.setBackgroundColor(0x5500FF00);
            String correct = getString(R.string.correct);
            correctAnswer4.setText(correct);
        } else {
            answerQuestion4_Input.setBackgroundColor(0x55FF0000);
            String incorrect = getString(R.string.incorrectAnswer4);
            correctAnswer4.setText(incorrect);
        }

        return score;
    }

    /**
     * Method that resets the screen.
     */
    public void reset(View view) {
        this.recreate();
//        valueQuestion1 = 0;
//        submitted = false;
//        isCorrectAnswer1 = false;
//        TextView valueQ1TextView = findViewById(R.id.valueQuestion1_text_view);
//        TextView correctAnswer1 = findViewById(R.id.correctAnswer1);
//        TextView correctAnswer2 = findViewById(R.id.correctAnswer2);
//        TextView correctAnswer3 = findViewById(R.id.correctAnswer3);
//        TextView correctAnswer4 = findViewById(R.id.correctAnswer4);
//        RadioButton question2_button1 = findViewById(R.id.question2_button1);
//        RadioButton question2_button2 = findViewById(R.id.question2_button2);
//        RadioButton question2_button3 = findViewById(R.id.question2_button3);
//        RadioButton question2_button4 = findViewById(R.id.question2_button4);
//        CheckBox question3_checkbox1 = findViewById(R.id.question3_checkbox1);
//        CheckBox question3_checkbox2 = findViewById(R.id.question3_checkbox2);
//        CheckBox question3_checkbox3 = findViewById(R.id.question3_checkbox3);
//        CheckBox question3_checkbox4 = findViewById(R.id.question3_checkbox4);
//        CheckBox question3_checkbox5 = findViewById(R.id.question3_checkbox5);
//        CheckBox question3_checkbox6 = findViewById(R.id.question3_checkbox6);
//        CheckBox question3_checkbox7 = findViewById(R.id.question3_checkbox7);
//        CheckBox question3_checkbox8 = findViewById(R.id.question3_checkbox8);
//        EditText answerQuestion4_Input = findViewById(R.id.question4_answer_input);
//        RadioGroup button_group = findViewById(R.id.button_group);
//        displayValueQuestion1(valueQuestion1);
//        valueQ1TextView.setBackgroundColor(0x00000000);
//        correctAnswer1.setText("");
//        correctAnswer2.setText("");
//        correctAnswer3.setText("");
//        correctAnswer4.setText("");
//        button_group.clearCheck();
//        question2_button1.setBackgroundColor(0x00000000);
//        question2_button2.setBackgroundColor(0x00000000);
//        question2_button3.setBackgroundColor(0x00000000);
//        question2_button4.setBackgroundColor(0x00000000);
//        question3_checkbox1.setChecked(false);
//        question3_checkbox1.setBackgroundColor(0x00000000);
//        question3_checkbox2.setChecked(false);
//        question3_checkbox2.setBackgroundColor(0x00000000);
//        question3_checkbox3.setChecked(false);
//        question3_checkbox3.setBackgroundColor(0x00000000);
//        question3_checkbox4.setChecked(false);
//        question3_checkbox4.setBackgroundColor(0x00000000);
//        question3_checkbox5.setChecked(false);
//        question3_checkbox5.setBackgroundColor(0x00000000);
//        question3_checkbox6.setChecked(false);
//        question3_checkbox6.setBackgroundColor(0x00000000);
//        question3_checkbox7.setChecked(false);
//        question3_checkbox7.setBackgroundColor(0x00000000);
//        question3_checkbox8.setChecked(false);
//        question3_checkbox8.setBackgroundColor(0x00000000);
//        answerQuestion4_Input.setBackgroundColor(0x00000000);
//        answerQuestion4_Input.setText(null);
    }
}
