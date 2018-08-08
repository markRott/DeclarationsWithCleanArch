package app.com.dataonsubmitteddeclarations.search.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.base.BaseRecyclerAdapter;
import app.com.dataonsubmitteddeclarations.search.adapter.listeners.TouchFavoriteListener;
import app.com.dataonsubmitteddeclarations.search.adapter.listeners.TouchPdfIconListener;
import app.com.domain.models.PersonModel;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class PersonAdapter extends BaseRecyclerAdapter<PersonModel, PersonAdapter.PersonItemHolder> {

    private TouchPdfIconListener<PersonModel> touchPdfIconListener;
    private TouchFavoriteListener<PersonModel> touchFavoriteListener;

    public PersonAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public PersonItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);

        return new PersonItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonItemHolder holder, int position) {
        fillPersonItemData(holder, position);
    }

    private void fillPersonItemData(PersonItemHolder holder, final int position) {
        final PersonModel model = getItemByPosition(position);
        fillViews(holder, model);
        fillIcons(holder, model);
        setupFavoriteClickListener(holder, model, position);
        setupPdfIconClickListener(holder, model, position);
    }

    private void fillViews(PersonItemHolder holder, final PersonModel model) {
        fillName(holder, model);
        fillPosition(holder, model);
        fillPlaceOfWork(holder, model);
        fillComment(holder, model);
        holder.prbFavorite.setVisibility(model.isProgressBarVisibilityState() ? VISIBLE : INVISIBLE);
    }

    private void fillPosition(PersonItemHolder holder, final PersonModel model) {
        setupVisibilityState(model.getPosition(), holder.position);
        holder.position.setText(getTextWithPrefix(R.string.title_position, model.getPosition()));
    }

    private void fillPlaceOfWork(PersonItemHolder holder, final PersonModel model) {
        setupVisibilityState(model.getPlaceOfWork(), holder.placeOfWork);
        holder.placeOfWork.setText(getTextWithPrefix(R.string.title_place_of_work, model.getPlaceOfWork()));
    }

    private void fillName(PersonItemHolder holder, final PersonModel model) {
        holder.firstName.setText(getTextWithPrefix(R.string.title_first_name, model.getFirstName()));
        holder.lastName.setText(getTextWithPrefix(R.string.title_last_name, model.getLastName()));
        setupVisibilityState(model.getMiddleName(), holder.middleName);
        holder.middleName.setText(getTextWithPrefix(R.string.title_middle_name, model.getMiddleName()));
    }

    private void fillComment(PersonItemHolder holder, final PersonModel model) {
        setupVisibilityState(model.getComment(), holder.comment);
        final String comment = setupComment(model);
        holder.comment.setText(comment);
        holder.comment.setTextColor(model.isDraftComment() ?
                ContextCompat.getColor(getContext(), R.color.colorCommentDraft) :
                ContextCompat.getColor(getContext(), R.color.colorCommentNormal));
    }

    private String setupComment(final PersonModel model) {
        String comment;
        if (model.isRemoveComment()) {
            comment = getTextWithPrefix(R.string.comment_remove, model.getComment());
        } else if (model.isDraftComment()) {
            comment = getTextWithPrefix(R.string.comment_draft, model.getComment());
        } else {
            comment = getTextWithPrefix(R.string.title_comment, model.getComment());
        }
        return comment;
    }

    private String getTextWithPrefix(@StringRes int id, String text) {
        final Resources res = getContext().getResources();
        return String.format(res.getString(id), text);
    }

    private void setupVisibilityState(final String data, final View view) {
        view.setVisibility(TextUtils.isEmpty(data) ? GONE : VISIBLE);
    }

    private void fillIcons(final PersonItemHolder holder, final PersonModel model) {
        holder.ivFavorite.setImageResource(model.isFavoriteStatus() ? R.drawable.ic_favorite : R.drawable.ic_unfavorite);
        holder.ivFavorite.setVisibility(model.isProgressBarVisibilityState() ? INVISIBLE : VISIBLE);
        holder.ivPdf.setImageResource(R.drawable.ic_pdf);
    }

    private void setupPdfIconClickListener(
            final PersonItemHolder holder,
            final PersonModel model,
            int position) {
        if (touchPdfIconListener != null) {
            holder.ivPdf.setOnClickListener(v -> touchPdfIconListener.touchPdfIcon(model, position));
        }
    }

    private void setupFavoriteClickListener(
            final PersonItemHolder holder,
            final PersonModel model,
            int position) {
        if (touchFavoriteListener != null) {
            holder.ivFavorite.setOnClickListener(v -> {
                model.setPositionInAdapter(position);
                touchFavoriteListener.touchFavoriteIcon(model, position);
            });
        }
    }

    public void setTouchPdfIconListener(TouchPdfIconListener<PersonModel> touchPdfIconListener) {
        this.touchPdfIconListener = touchPdfIconListener;
    }

    public void setTouchFavoriteListener(TouchFavoriteListener<PersonModel> touchFavoriteListener) {
        this.touchFavoriteListener = touchFavoriteListener;
    }

    static class PersonItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_first_name)
        TextView firstName;
        @BindView(R.id.tv_last_name)
        TextView lastName;
        @BindView(R.id.tv_position)
        TextView position;
        @BindView(R.id.tv_place_of_work)
        TextView placeOfWork;
        @BindView(R.id.tv_middle_name)
        TextView middleName;
        @BindView(R.id.tv_comment_name)
        TextView comment;
        @BindView(R.id.iv_favorite)
        ImageView ivFavorite;
        @BindView(R.id.iv_pdf)
        ImageView ivPdf;
        @BindView(R.id.prb_favorite)
        ProgressBar prbFavorite;

        PersonItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
