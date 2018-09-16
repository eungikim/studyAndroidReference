package eungi.happy.webbasedcontent;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import eungi.happy.webbasedcontent.util.Constants;
import eungi.happy.webbasedcontent.webview.MyWebChromeClient;
import eungi.happy.webbasedcontent.webview.WebAppInterface;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private Boolean mIsPressedBackButton = false;

    @BindView(R.id.main_web_view) WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setWebViewSettings();

        // build flavor 를 통한 url 변경
        String baseUrl = Constants.WEB_URL.PRODUCT;
        switch (BuildConfig.BUILD_MODE) {
            case Constants.BUILD_MODE.DEV:
                baseUrl = Constants.WEB_URL.DEV;
                break;
            case Constants.BUILD_MODE.LOCAL:
                baseUrl = Constants.WEB_URL.LOCAL;
                break;
        }

        Log.i(TAG, "Base URL: " + baseUrl);
        mWebView.loadUrl(baseUrl + Constants.WEB_PATH.DASHBOARD);

    }

    @Override
    protected void onPause() {
        // 태스크 종료에 의한 쿠키값 보존 실패를 막기 위한 코드
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager.getInstance().sync();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.destroy();
        super.onDestroy();
    }

    // 웹뷰 네비게이션
    @Override
    public void onBackPressed() {
        String path = getWebViewPath();
        if (path == null) {
            finish();
            Toast.makeText(getApplicationContext(), "Error code 404", Toast.LENGTH_LONG).show();
        } else if (path.equals(Constants.WEB_PATH.DASHBOARD) ||
                path.equals(Constants.WEB_PATH.LOGIN) ||
                path.equals(Constants.WEB_PATH.LOGIN_)) {
            terminateAppProc();
        } else if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    private void setWebViewSettings() {
        //mWebView.clearCache(true);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 필수
        webSettings.setDomStorageEnabled(true); // 필수
        // 쿠키 싱크
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setAcceptThirdPartyCookies(mWebView, true);
        }

        mWebView.setWebViewClient(new WebViewClient() {}); // 필수
        mWebView.setWebChromeClient(new MyWebChromeClient() {}); // 필수
        mWebView.addJavascriptInterface(new WebAppInterface(mWebViewInterface), "Android");

        WebView.setWebContentsDebuggingEnabled(true);
        Log.d("MY_APP_TAG", "UA: " + mWebView.getSettings().getUserAgentString());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            PackageInfo webViewPackageInfo = WebView.getCurrentWebViewPackage();
            Log.d("MY_APP_TAG", "WebView version: " + webViewPackageInfo.versionName);
        }
    }

    private boolean checkWebViewAlive() {
        return mWebView != null && mWebView.getUrl() != null;
    }

    private String getWebViewPath() {
        String path = null;
        if (checkWebViewAlive())
            path = Uri.parse(mWebView.getUrl()).getPath();

        return path;
    }

    private void terminateAppProc() {
        if (mIsPressedBackButton) {
            finish();
        } else {
            mIsPressedBackButton = true;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> mIsPressedBackButton = false, 3000);
        }
    }

    public interface WebViewInterface {
        void callback(String msg);
        void toast(String msg);
    }

    public WebViewInterface mWebViewInterface = new WebViewInterface() {
        @Override
        public void callback(String msg) {
            Log.d(TAG, "callback() called with: msg = [" + msg + "]");
            runOnUiThread(() ->
                    mWebView.loadUrl("javascript:button1_callback('"+msg+"')")
            );
        }

        @Override
        public void toast(String msg) {
            Log.d(TAG, "toast() called with: msg = [" + msg + "]");
            Snackbar.make(mWebView, msg, Snackbar.LENGTH_LONG).show();
        }
    };

}
