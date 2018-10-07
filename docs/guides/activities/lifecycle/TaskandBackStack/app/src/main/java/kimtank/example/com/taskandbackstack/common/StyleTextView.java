package kimtank.example.com.taskandbackstack.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class StyleTextView extends TextView{
    public StyleTextView(Context context) {
        super(context);
        setting(context);
    }

    public StyleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setting(context);
    }

    public StyleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setting(context);
    }

    private void setting(Context context) {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        setTextColor(Color.BLACK);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
    }
}
