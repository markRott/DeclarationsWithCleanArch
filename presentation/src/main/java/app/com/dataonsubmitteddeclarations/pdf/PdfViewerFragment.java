package app.com.dataonsubmitteddeclarations.pdf;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import app.com.dataonsubmitteddeclarations.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PdfViewerFragment extends Fragment {

    private static final String ARGS_PDF_URL = "pdf_url";

    @BindView(R.id.wv_pdf)
    WebView webView;
    @BindView(R.id.prb_load_pdf)
    ProgressBar prbLoadPdf;

    private Unbinder unbinder;

    public static PdfViewerFragment newInstance(final String pdfUrl) {
        final PdfViewerFragment fragment = new PdfViewerFragment();
        final Bundle args = new Bundle();
        args.putString(ARGS_PDF_URL, pdfUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_open_pdf, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView.setWebViewClient(new AppWebViewClient(this));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(getPdfUrl());
    }

    private String getPdfUrl() {
        final String firstPart = "https://docs.google.com/gview?embedded=true&url=";
        String secondPart = "";
        final Bundle args = getArguments();
        if (args != null && args.containsKey(ARGS_PDF_URL)) {
            secondPart = args.getString(ARGS_PDF_URL);
        }
        return new StringBuilder(firstPart).append(secondPart).toString();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public void finishLoading() {
        if (isAdded() && prbLoadPdf != null) {
            prbLoadPdf.setVisibility(View.GONE);
        }
    }
}
