package kimtank.example.com.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntentActivity extends AppCompatActivity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        TextView textView = (TextView)findViewById(R.id.textView);
        Button button = (Button)findViewById(R.id.button3);
        Button button1 = (Button)findViewById(R.id.button2);

        button.setText("확인");
        button1.setText("취소");

        textView.setText("텍스트를 누르면 \"취소\"를 가져갑니다.");
        textView.setTextSize(30.0f);

        intent = new Intent(this, MainActivity.class);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cancel = "취소";
                intent.putExtra("cancel", cancel);
                setResult(RESULT_FIRST_USER, intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkSuccess = "유효성 성공";
                intent.putExtra("result", checkSuccess);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkFail = "유효성 검사 실패";
                intent.putExtra("result", checkFail);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}
