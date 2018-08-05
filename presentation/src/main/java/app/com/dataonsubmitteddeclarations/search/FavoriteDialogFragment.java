package app.com.dataonsubmitteddeclarations.search;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.com.dataonsubmitteddeclarations.R;
import app.com.domain.models.PersonModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FavoriteDialogFragment extends DialogFragment {

    public static final String SEND_FAVORITE_MODEL = "send_favorite_model";

    private static final String ARGS_PERSON_MODEL = "person_model";
    private static final boolean FAVORITE = true;

    @BindView(R.id.tv_favorite_title)
    TextView tvTitle;
    @BindView(R.id.edt_favorite_comment)
    EditText edtComment;
    @BindView(R.id.btn_add_favorite)
    Button btnAddToFavorite;
    @BindView(R.id.btn_remove_favorite)
    Button btnRemoveFromFavorite;

    private Unbinder unbinder;
    private PersonModel personModel;

    public static FavoriteDialogFragment newInstance(PersonModel personModel) {
        final FavoriteDialogFragment fragment = new FavoriteDialogFragment();
        final Bundle args = new Bundle();
        args.putSerializable(ARGS_PERSON_MODEL, personModel);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_favorite, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataFromArguments();
        setupTitle();
        fillComment();
//        setupButtonState();
    }

    private void getDataFromArguments() {
        if (getArguments() != null && getArguments().containsKey(ARGS_PERSON_MODEL)) {
            personModel = (PersonModel) getArguments().getSerializable(ARGS_PERSON_MODEL);
        } else {
            personModel = new PersonModel();
        }
    }

    private void setupTitle() {
        tvTitle.setText(personModel.isFavorite() ?
                R.string.title_favorite_remove : R.string.title_favorite_add);
    }

    private void fillComment() {
        edtComment.setText(!TextUtils.isEmpty(personModel.getComment()) ?
                personModel.getComment() : "");
    }

    private void setupButtonState() {
        btnAddToFavorite.setEnabled(!personModel.isFavorite());
        btnRemoveFromFavorite.setEnabled(personModel.isFavorite());
    }

    @Override
    public void onDetach() {
        unbinder.unbind();
        super.onDetach();
    }

    @OnClick(R.id.btn_add_favorite)
    public void addToFavorite() {
        personModel.setFavoriteStatus(FAVORITE);
        setupComment();
        sendDataAndDismiss();
    }

    private void setupComment() {
        final String comment = edtComment.getText().toString().trim();
        personModel.setComment(comment);
    }

    @OnClick(R.id.btn_remove_favorite)
    public void removeFromFavorite() {
        personModel.setFavoriteStatus(!FAVORITE);
        sendDataAndDismiss();
    }

    private void sendDataAndDismiss() {
        sendDataToTargetFragment();
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    public void cancel() {
        dismiss();
    }

    public void sendDataToTargetFragment() {
        final Intent favoriteIntent = new Intent();
        favoriteIntent.putExtra(SEND_FAVORITE_MODEL, personModel);
        getTargetFragment().onActivityResult(
                getTargetRequestCode(), Activity.RESULT_OK, favoriteIntent);

    }
}
