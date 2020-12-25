package com.tamer.assignment5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView tv_result = findViewById(R.id.tv_result);

        if (getIntent() == null
                || !getIntent().hasExtra("result")
                || !getIntent().hasExtra("questionNumbers")) {
            finish();
            return;
        }

        Intent intent = getIntent();
        int result = intent.getIntExtra("result",0);
        int questionNumbers = intent.getIntExtra("questionNumbers",0);

        String resultText = getString(R.string.quiz_result, result, questionNumbers);
        tv_result.setText(Html.fromHtml(resultText));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ResultActivity.this,MainActivity.class));
    }
}