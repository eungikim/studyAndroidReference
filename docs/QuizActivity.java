package com.forsee.happy.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_CHEATER_ARRAY = "cheater_array";
    private static final int REQUEST_CODE_CHEAT = 0;
    private final int SIZE_OF_QUESTION = 5;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true, false),
            new Question(R.string.question_mideast, false, false),
            new Question(R.string.question_africa, false, false),
            new Question(R.string.question_americas, true, false),
            new Question(R.string.question_asia, true, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate");

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex -1);
                mCurrentIndex = mCurrentIndex == -1 ? mQuestionBank.length -1: mCurrentIndex;
                updateQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            boolean[] isCheaterArr;
            isCheaterArr = savedInstanceState.getBooleanArray(KEY_CHEATER_ARRAY);
            for (int i = 0; i < mQuestionBank.length; i++) {
                mQuestionBank[i].setIscheater(isCheaterArr[i]);
            }
        }
        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        boolean isCheater = mQuestionBank[mCurrentIndex].isCheater();

        int messageRedId = 0;
        if (isCheater) {
            messageRedId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageRedId = R.string.correct_toast;
            } else {
                messageRedId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(QuizActivity.this, messageRedId, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        boolean[] isCheaterArr = new boolean[SIZE_OF_QUESTION];
        for (int i = 0; i < mQuestionBank.length; i++) {
            isCheaterArr[i] = mQuestionBank[i].isCheater();
        }
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBooleanArray(KEY_CHEATER_ARRAY, isCheaterArr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) return;
            boolean isCheater = CheatActivity.wasAnswerShown(data);
            mQuestionBank[mCurrentIndex].setIscheater(isCheater);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LOG_METHOD("onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        LOG_METHOD("onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        LOG_METHOD("onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        LOG_METHOD("onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LOG_METHOD("onDestroy");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        LOG_METHOD("onRestart");
    }

    private void LOG_METHOD(String nameOfMethod) {
    	Log.d(TAG, nameOfMethod);
        Log.d(TAG, "Hello World");
        Log.d(TAG, "Hello World");
        Log.d(TAG, "Hello World");
    }
}
