package app.com.dataonsubmitteddeclarations.pdf;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.ref.WeakReference;

import timber.log.Timber;

public class AppWebViewClient extends WebViewClient {

    private WeakReference<PdfViewerFragment> weakReference;

    AppWebViewClient(PdfViewerFragment pdfViewerFragment) {
        weakReference = new WeakReference<>(pdfViewerFragment);
        Timber.d(weakReference.toString());
        Timber.d(weakReference.get() == null ? "NULL" : weakReference.get().toString());
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Timber.d("onPageStarted");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Timber.d(url);
        if (weakReference.get() != null) {
            weakReference.get().finishLoading();
        }
    }
}
