package eungi.happy.webbasedcontent.webview;

import android.content.SharedPreferences;
import android.webkit.JavascriptInterface;

import eungi.happy.webbasedcontent.MainActivity;

/**
 * Created by Eungi on 2018-08-06.
 */
public class WebAppInterface {

    private MainActivity.WebViewInterface mWebViewInterface;

    public WebAppInterface(MainActivity.WebViewInterface webViewInterface) {
        mWebViewInterface = webViewInterface;
    }

    @JavascriptInterface
    public void setTopicData(String message) {
        mWebViewInterface.toast(message);
        mWebViewInterface.callback(message);
    }


}
