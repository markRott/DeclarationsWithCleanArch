package app.com.dataonsubmitteddeclarations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import app.com.dataonsubmitteddeclarations.search.SearchContract;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchFragment extends Fragment implements SearchContract {

    @BindView(R.id.edt_search)
    EditText edtSearch;
    @BindView(R.id.iv_clear_text)
    ImageView ivClear;
    @BindView(R.id.vg_empty_list)
    ViewGroup vgNoData;
    @BindView(R.id.prg_search)
    ProgressBar prgSearch;

    private Unbinder unbinder;

    public static SearchFragment newInstance() {
        final SearchFragment fragment = new SearchFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_clear_text)
    public void clearEditText(View view) {
        edtSearch.setText("");
    }

    @Override
    public void showProgress() {
        prgSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        prgSearch.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataView() {
        vgNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoDataView() {
        vgNoData.setVisibility(View.GONE);
    }
}
