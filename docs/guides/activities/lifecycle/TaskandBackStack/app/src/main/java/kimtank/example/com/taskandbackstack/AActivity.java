package kimtank.example.com.taskandbackstack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import kimtank.example.com.taskandbackstack.common.ActivityPosition;
import kimtank.example.com.taskandbackstack.common.AppCompatActivityActionBarSetting;
import kimtank.example.com.taskandbackstack.common.BackPressCloseHandler;

public class AActivity extends AppCompatActivityActionBarSetting {

    //클래스 돌때 한번만 실행
    private boolean init;
    //보낼값
    private ArrayList<String> arrayList;
    //각 뷰 별 포지션 구분
    private int position=-1;

    private void setInit(Bundle bundle){
        if(init)
            return;
        init = true;
        if(arrayList==null){
            arrayList = new ArrayList<>(5);
            arrayList.add("A액티비티");
            arrayList.add("B액티비티");
            arrayList.add("C액티비티");
            arrayList.add("D액티비티");
            arrayList.add("E액티비티");
        }
        if(position==-1){
            position = ActivityPosition.A_ACTIVITY_POSITION.getPosition();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInit(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.textview1);

        buttonNext = findViewById(R.id.button_next);
        buttonBack = findViewById(R.id.button_back);

        showTitleHomeButton(arrayList.get(position).toString());
        tv1.setText(arrayList.get(position).toString());
    }

    //텍스트뷰
    private TextView tv1;
    //버튼
    private Button buttonNext;
    private Button buttonBack;

    @Override
    protected void onResume() {
        super.onResume();

        buttonNext.setOnClickListener(buttonEvent);
        buttonBack.setOnClickListener(buttonEvent);
    }

    //버튼 동작
    View.OnClickListener buttonEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_next:
                    Intent intent = new Intent(AActivity.this, BActivity.class);
                    intent.putExtra("sendArrayList", arrayList);
                    intent.putExtra("position", position);
                    startActivity(intent);
                    break;
                case R.id.button_back:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    //뒤로가기두번 시 종료
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        new BackPressCloseHandler(this).onBackPressed();
    }
}
