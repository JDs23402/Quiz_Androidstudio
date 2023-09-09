package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView total_question,question;
    Button ansa,ansb,ansc,ansd;
    Button submit;

    int score=0;
    int totalquestion=Questionanswer.question.length;
    int currentquestionidx=0;
    String selectedanswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total_question=(TextView) findViewById(R.id.totalquestions);
        question=(TextView) findViewById(R.id.questions);
        ansa=(Button) findViewById(R.id.ansa);
        ansb=(Button) findViewById(R.id.ansb);
        ansc=(Button) findViewById(R.id.ansc);
        ansd=(Button) findViewById(R.id.ansd);
        submit=(Button) findViewById(R.id.submit);

        ansa.setOnClickListener(this);
        ansb.setOnClickListener(this);
        ansc.setOnClickListener(this);
        ansd.setOnClickListener(this);
        submit.setOnClickListener(this);

        total_question.setText("Total Question :"+totalquestion);

        loadQuestion();
    }

    void loadQuestion() {

        if(currentquestionidx==totalquestion)
        {
            finishQuiz();
            return;
        }

        question.setText(Questionanswer.question[currentquestionidx]);
        ansa.setText(Questionanswer.choices[currentquestionidx][0]);
        ansb.setText(Questionanswer.choices[currentquestionidx][1]);
        ansc.setText(Questionanswer.choices[currentquestionidx][2]);
        ansd.setText(Questionanswer.choices[currentquestionidx][3]);
    }

    void finishQuiz() {
        String passStatus="";
        if(score>totalquestion*0.60)
            passStatus="Passed";
        else
            passStatus="Failed";

        new AlertDialog.Builder(this).setTitle(passStatus)
                .setMessage("Score "+score+" out of "+totalquestion)
                .setPositiveButton("Restart",((dialogInterface, i) -> restart()))
                .setCancelable(false)
                .show();
    }

    void restart()
    {
        score=0;
        currentquestionidx=0;
        loadQuestion();
    }

    @Override
    public void onClick(View view) {
        ansa.setBackgroundColor(Color.GRAY);
        ansb.setBackgroundColor(Color.GRAY);
        ansc.setBackgroundColor(Color.GRAY);
        ansd.setBackgroundColor(Color.GRAY);

        Button clickedButton=(Button) view;
        if(clickedButton.getId()==R.id.submit)
        {
            if(selectedanswer.equals(Questionanswer.correctanswer[currentquestionidx]))
            {
                score++;
            }
            currentquestionidx++;
            loadQuestion();
        }
        else
        {
            selectedanswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }
}