package kimtank.example.com.taskandbackstack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import kimtank.example.com.taskandbackstack.common.ActivityPosition;
import kimtank.example.com.taskandbackstack.common.AppCompatActivityActionBarSetting;
import kimtank.example.com.taskandbackstack.common.BackPressCloseHandler;

public class EActivity extends AppCompatActivityActionBarSetting {

    //클래스 돌때 한번만 실행
    private boolean init;
    private void setInit(Bundle bundle) {
        if (init)
            return;
        init = true;
        Intent intent = getIntent();
        if (intent.getStringArrayListExtra("sendArrayList") != null)
            arrayList = intent.getStringArrayListExtra("sendArrayList");
        if(intent.getIntExtra("position", -1)!=-1){
            position = ActivityPosition.E_ACTIVITY_POSITION.getPosition();
        }
    }

    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInit(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv5 = findViewById(R.id.textview5);

        buttonNext = findViewById(R.id.button_next);
        buttonBack = findViewById(R.id.button_back);

        showTitleHomeButton(arrayList.get(position).toString());
        tv5.setText(arrayList.get(position).toString());

    }

    //텍스트뷰
    private TextView tv5;
    //버튼
    private Button buttonNext;
    private Button buttonBack;
    //보낼값
    private ArrayList<String> arrayList;

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
                    Intent intent = new Intent(EActivity.this, CActivity.class);
                    arrayList.clear();
                    arrayList.add("dummy1");
                    arrayList.add("dummy1");
                    arrayList.add("두번째돌이");
                    arrayList.add("두번째도는데 dd");
                    /*arrayList.add("두번째돌이");*/
                    intent.putExtra("sendArrayList", arrayList);
                    intent.putExtra("position", position);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
