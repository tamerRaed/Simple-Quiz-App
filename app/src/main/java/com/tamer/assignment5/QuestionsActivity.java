package com.tamer.assignment5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {
    private int result = 0;
    private int questionNumber = 1;
    private Button confirm;
    private TextView tv_question_num, tv_question;
    private RadioButton rb_choice1, rb_choice2, rb_choice3;
    private RadioGroup radioGroup;
    private int btn_state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        initViews();
        setButtonText();
        fillQuestionData(questionNumber);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (btn_state) {
                    case 0:
                        // Confirm
                        if (isSelectedChoice()) {
                            if (checkQuestionTrueOrFalse(questionNumber)) {
                                result++;

                            }
                            if (questionNumber == 5) {
                                confirm.setText(R.string.finish);
                                btn_state = -1;
                                setButtonText();
                            } else {
                                confirm.setText(R.string.next);
                                btn_state = 1;
                                setButtonText();
                            }
                        } else {
                            Toast.makeText(QuestionsActivity.this, getString(R.string.no_answer_selected), Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 1:
                        // Next
                        clearRadioGroup();
                        enableChooseAnswer();
                        ++questionNumber;
                        fillQuestionData(questionNumber);
                        btn_state = 0;
                        setButtonText();
                        break;
                    case -1:
                        // Finish
                        Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
                        intent.putExtra("result", result);
                        intent.putExtra("questionNumbers", questionNumber);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void setButtonText() {
        switch (btn_state) {
            case 0:
                confirm.setText(R.string.confirm);
                break;
            case 1:
                confirm.setText(R.string.next);
                break;
            case -1:
                confirm.setText(R.string.finish);
                break;
        }
    }

    private void initViews() {
        rb_choice1 = findViewById(R.id.choice1);
        rb_choice2 = findViewById(R.id.choice2);
        rb_choice3 = findViewById(R.id.choice3);
        radioGroup = findViewById(R.id.radioGroup);
        confirm = findViewById(R.id.btn_confirm);
        tv_question = findViewById(R.id.tv_question);
        tv_question_num = findViewById(R.id.tv_question_num);
    }

    public void fillQuestionData(int questionNumber) {
        switch (questionNumber) {
            case 1:
                tv_question_num.setText(R.string.question_num_1);
                tv_question.setText(R.string.question_1);
                rb_choice1.setText(R.string.iOS);
                rb_choice2.setText(R.string.android);
                rb_choice3.setText(R.string.windows_phone);

                break;
            case 2:
                tv_question_num.setText(R.string.question_num_2);
                tv_question.setText(R.string.question_2);
                rb_choice1.setText(R.string.python);
                rb_choice2.setText(R.string.javaScript);
                rb_choice3.setText(R.string.java);
                break;
            case 3:
                tv_question_num.setText(R.string.question_num_3);
                tv_question.setText(R.string.question_3);
                rb_choice1.setText(R.string.android_IDE);
                rb_choice2.setText(R.string.android_studio);
                rb_choice3.setText(R.string.eclipse);
                break;
            case 4:
                tv_question_num.setText(R.string.question_num_4);
                tv_question.setText(R.string.question_4);
                rb_choice1.setText(R.string.facebook);
                rb_choice2.setText(R.string.udacity);
                rb_choice3.setText(R.string.google);
                break;
            case 5:
                tv_question_num.setText(R.string.question_num_5);
                tv_question.setText(R.string.question_5);
                rb_choice1.setText(R.string.pie);
                rb_choice2.setText(R.string.android_10);
                rb_choice3.setText(R.string.oreo);
                break;
        }
    }

    public boolean isSelectedChoice() {
        return radioGroup.getCheckedRadioButtonId() != -1;
    }

    public boolean checkQuestionTrueOrFalse(int questionNumber) {
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        disableChangeChoice();
        switch (questionNumber) {
            case 1:
                if (radioButton.getText().equals(getResources().getString(R.string.android))) {
                    fillBackgroundColor(radioButton,true);
                    return true;
                } else {
                    fillBackgroundColor(radioButton,false);
                    return false;
                }

            case 2:
                if (radioButton.getText().equals(getResources().getString(R.string.java))) {
                    fillBackgroundColor(radioButton,true);
                    return true;
                } else {
                    fillBackgroundColor(radioButton,false);
                    return false;
                }
            case 3:
                if (radioButton.getText().equals(getResources().getString(R.string.android_studio))) {
                    fillBackgroundColor(radioButton,true);
                    return true;
                } else {
                    fillBackgroundColor(radioButton,false);
                    return false;
                }
            case 4:
                if (radioButton.getText().equals(getResources().getString(R.string.google))) {
                    fillBackgroundColor(radioButton,true);
                    return true;
                } else {
                    fillBackgroundColor(radioButton,false);
                    return false;
                }
            case 5:
                if (radioButton.getText().equals(getResources().getString(R.string.android_10))) {
                    fillBackgroundColor(radioButton,true);
                    return true;
                } else {
                    fillBackgroundColor(radioButton,false);
                    return false;
                }
        }
        return false;
    }


    public void clearRadioGroup() {
        radioGroup.clearCheck();
        rb_choice1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        rb_choice2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        rb_choice3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
    }

    public void disableChangeChoice() {
        rb_choice1.setClickable(false);
        rb_choice2.setClickable(false);
        rb_choice3.setClickable(false);
    }

    private void enableChooseAnswer() {
        rb_choice1.setClickable(true);
        rb_choice2.setClickable(true);
        rb_choice3.setClickable(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("questionNum", questionNumber);
        outState.putInt("btn_state", btn_state);
        outState.putInt("result", result);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        btn_state = savedInstanceState.getInt("btn_state");
        questionNumber = savedInstanceState.getInt("questionNum");
        result = savedInstanceState.getInt("result");

        fillQuestionData(questionNumber);
        if (btn_state == 1) {
            checkQuestionTrueOrFalse(questionNumber);
        }
        setButtonText();
    }
    private void fillBackgroundColor(RadioButton radioButton, boolean status){
        if (status){
            radioButton.setBackgroundResource(R.drawable.true_choice_bg);
        }else {
            radioButton.setBackgroundResource(R.drawable.false_choice_bg);
        }
    }
}