package eungi.happy.webbasedcontent.webview;

import android.support.v7.app.AlertDialog;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by Eungi on 2018-08-06.
 */
public class MyWebChromeClient extends WebChromeClient {

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
        result.confirm();
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        new AlertDialog.Builder(view.getContext())
                .setMessage(message)
                .setPositiveButton("확인", (dialog, which) -> result.confirm())
                .setNegativeButton("취소", (dialog, which) -> result.cancel())
                .create().show();
        return true;
    }

}
