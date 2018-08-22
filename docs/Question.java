package com.forsee.happy.geoquiz;

import android.util.Log;

/**
 * Created by HAPPY on 2017-05-20.
 *
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mIsCheater;

    public Question(int textResId, boolean answerTrue, boolean isCheater) {
        Log.d("QuizActivity", "mQuestionBank reset");
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mIsCheater = isCheater;
    }



    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public boolean isCheater() {
        return mIsCheater;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public void setIscheater(boolean ischeater) {
        mIsCheater = ischeater;
    }
}
