package kimtank.example.com.activitylifecycleexample;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    /**
     *  onCreate(), onStart(), onResume(), onPause(), onStop(), onDestroy
     *  액티비티 주기 패러다임의 시각적 표현그림
     *  https://developer.android.com/guide/components/images/activity_lifecycle.png?hl=ko
     */

    /**
     *처음 액티비티 생성시 발생하는 이 콜백을 구현해야함.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: onCreate 1번");
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: onCreate 2번");
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: onCreate 3번");
    }

    /**
     * 액티비티가 Started상태가되면 이 콜백을 호출함
     * ex)앱이 UI를 유지관리하는 코드를 초기화하는 곳
     */
    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: 1번" );
        super.onStart();
        Log.e(TAG, "onStart: 2번");
    }

    /**
     * 액티비티이 다시 시작된 상태로 전환되면 해당 작업이 화면 맨 앞으로 오고
     * 시스템에서 onResume()콜백을 호출
     * ex)사용자가 다른 액티비티으로 이동하거나 장치화면이 꺼지는것이 발생가능
     *
     */
    @Override
    protected void onResume() {
        Log.e(TAG, "onResume: 1번");
        super.onResume();
        Log.e(TAG, "onResume: 2번");
        ConstraintLayout test = (ConstraintLayout)findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LifecycleActivity.class);
                startActivity(intent);
            }
        });

    }
    //카메라를 예를들어 ON_RESUME이벤트를 수신하는법
    /*public class CameraComponent implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void initializeCamera() {
            if (camera == null) {
                getCamera();
            }
        }
    }
    //카메라값을 초기화? 시켜줌
    */

    /**
     *  시스템이 사용자가 액티비티를 종료함을 알리는 첫번째 표시(항상 액티비티가 파괴되고있는게 아님)
     *
     *  onPause의 실행은 매우 짧고, 절약(리소스, 메모리)작업을 수행할 충분한 시간이 없음
     * 따라서 애플리케이션 또는 사용자 데이터를 저장하거나, 네트워크호출을 하거나, 데이터베이스 트랜잭션을
     * 실행하는데 onPause()를 사용하면 안됨 -> onStop() 중부하 셧다운 작업을 수행하면 저런 작업을 할수도있음
     */
    @Override
    protected void onPause() {
        Log.e(TAG, "onPause: 1번" );
        super.onPause();
        Log.e(TAG, "onPause: 2번" );
    }
    //초기화한 카메라값을 해제시켜줌
    /*public class JavaCameraComponent implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void releaseCamera() {
            if (camera != null) {
                camera.release();
                camera = null;
            }
        }
    }*/

    /**
     * 액티비티가 사용자에게 표시되지 않으면 중지됨 상태로 전환되고 onStop()콜백 호출
     * ON_STOP 이벤트를 수신 -> 구성요소가 화면에 표시되지 않는 동안 실행할 필요가 없는 기능을 구성요소가 중지 가능
     * #필요없는 리소스 해제 및 조정 ex)다중 윈도우 모드에서 UI관련작업이 계속되도록함
     * CPU집약적인 종료 작업을 수행해야 함.
     */
    @Override
    protected void onStop() {
        Log.e(TAG, "onStop: 1번");
        //superclass 함수 호출
        super.onStop();
        Log.e(TAG, "onStop: 2번");

        // 액티비티가 중지되고 있으므로 노트의 현재 임시 보관함
        // 그리고 현재 노트 진행 상태가 손실되지 않았는지 확인하고 싶습니다.
        /*ContentValues values = new ContentValues();
        values.put(NotePad.Notes.COLUMN_NAME_NOTE, getCurrentNoteText());
        values.put(NotePad.Notes.COLUMN_NAME_TITLE, getCurrentNoteTitle());*/

        // 이 업데이트를 AsyncQueryHandler 또는 동등한 환경에서 수행
       /* mAsyncQueryHandler.startUpdate (
                mToken,  // int 호출 상관 관계를 나타내는 토큰
                null,    // cookie, 쿠키, 여기에서 사용되지 않음
                mUri,    // 노트가 업데이트될 URI입니다.
                values,  // 열 이름과 해당 이름에 적용할 새 값의 맵.
                null,    // SELECT 기준이 사용되지 않습니다.
                null     // 사용 중인 WHERE 열 없음.
        );*/

       /*
       * 상위코드 SQLite 사용 라이브러리 Room사용해야함
       * */
    }
    // 정시 상태에서 액티비티는 다시 사용자와 상호작용하거나 액티비티가 종료되어 사라짐
    //1. 액티비티가 다시 시작되면 시스템은 onRestart()를 호출 2.액티비티 실행이 완료되면 onDestroy()호출

    /**
     * 액티비티가 파괴되기전 이 콜백을 호출.(액티비티가 완료, 장치회전또는 다중윈도우모드같은 일시적 파괴)
     * ON_DESTROY 이벤트를 수신, 필요한것을 정리할 수 있음.
     *
     * 액티비티에서 로직이 삭제되는 이유를 결정하는 대신 ViewModel 개체를 사용하여 액티비티에 대한
     * view 데이터를 포함해야함. 구성변경으로 인해 활동을 재생성할 경우 ViewModel은 보존됭 다음 인스턴스에서 제공됨->아무것도안해도됨
     * 활동이 재생성되지 않을 경우 ViewModel은 파괴되기 전에 필요한 모든 데이터를 정리할 수 있는 OnCleared()메서드를 갖게됨.
     *
     * isFinishing() 메소드를 사용하여 두 시나리오를 구분가능
     *
     * onStop()과 같이 이전 콜백에 의해 아직 해제되지 않은 모든 리소스를 해제해야 함.
     */
    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: 1");
        super.onDestroy();
        Log.e(TAG, "onDestroy: 2");
    }
}
