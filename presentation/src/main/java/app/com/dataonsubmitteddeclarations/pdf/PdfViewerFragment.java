package app.com.dataonsubmitteddeclarations.pdf;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.base.BackPressedContract;
import app.com.dataonsubmitteddeclarations.utils.ViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PdfViewerFragment extends Fragment implements BackPressedContract, PageLoadFinished {

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupWebView();
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewUtils.hideKeyboardFrom(getContext(), getView());
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new AppWebViewClient(this));
        webView.loadUrl(getPdfUrl());
    }

    private String getPdfUrl() {
        final String firstPart = "https://docs.google.com/gview?embedded=true&url=";
        String secondPart = "";
        final Bundle args = getArguments();
        if (args != null && args.containsKey(ARGS_PDF_URL)) {
            secondPart = args.getString(ARGS_PDF_URL);
        }
        return String.format("%s%s", firstPart, secondPart);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onFinishLoading() {
        if (isAdded() && prbLoadPdf != null) {
            prbLoadPdf.setVisibility(View.GONE);
        }
    }
}
