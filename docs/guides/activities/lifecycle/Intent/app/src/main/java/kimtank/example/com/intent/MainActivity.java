package kimtank.example.com.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.result_tv);
        Button button = (Button)findViewById(R.id.button);
        button.setText("검사하러가기");

        final Intent intent = new Intent(this, IntentActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result;
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                result = data.getStringExtra("result");
                textView.setText(result);
            } else if (resultCode == RESULT_CANCELED){
                result = data.getStringExtra("result");
                textView.setText(result);
            } else {
                result = data.getStringExtra("cancel");
                textView.setText(result);
            }
        } else {
            textView.setText("유효성검사");
        }
    }
}
