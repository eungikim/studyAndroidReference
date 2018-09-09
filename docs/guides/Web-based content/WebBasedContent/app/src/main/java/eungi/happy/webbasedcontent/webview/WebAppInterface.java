package eungi.happy.webbasedcontent.webview;

import android.content.SharedPreferences;
import android.webkit.JavascriptInterface;

import eungi.happy.webbasedcontent.MainActivity;

/**
 * Created by Eungi on 2018-08-06.
 */
public class WebAppInterface {

    private MainActivity.WebViewInterface mWebViewInterface;
    private SharedPreferences mPreferences;

    public WebAppInterface(MainActivity.WebViewInterface webViewInterface) {
        mWebViewInterface = webViewInterface;
    }

    @JavascriptInterface
    public void activeRecognizer() {
        mWebViewInterface.activeRecognizer();
    }


}
