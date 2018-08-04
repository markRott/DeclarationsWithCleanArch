package app.com.dataonsubmitteddeclarations.pdf;

import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.ref.WeakReference;

import timber.log.Timber;

public class AppWebViewClient extends WebViewClient {

    private WeakReference<PageLoadFinished> weakReference;

    AppWebViewClient(PageLoadFinished callback) {
        weakReference = new WeakReference<>(callback);
        Timber.d(weakReference.get().toString());
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && request != null && request.getUrl() != null) {
            String url = request.getUrl().toString();
            view.loadUrl(url);
            return true;
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Timber.d("onPageStarted");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Timber.d(url);
        if (weakReference.get() != null) {
            weakReference.get().onFinishLoading();
        }
    }
}
