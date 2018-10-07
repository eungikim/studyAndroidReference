package kimtank.example.com.taskandbackstack.common;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class AppCompatActivityActionBarSetting extends AppCompatActivity {
    public void showTitleHomeButton(String title) {
        //액션바 보이기
        getSupportActionBar().show();
        //타이틀 정하기
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        //홈버튼 보이기
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //액션바 동작
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
