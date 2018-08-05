package app.com.dataonsubmitteddeclarations.search.adapter;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.base.BaseRecyclerAdapter;
import app.com.domain.models.PersonModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonAdapter extends BaseRecyclerAdapter<PersonModel, PersonAdapter.PersonItemHolder> {

    private TouchPdfIconListener<PersonModel> touchPdfIconListener;
    private TouchFavoriteListener<PersonModel> touchFavoriteListener;

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
        setupLikeClickListener(holder, model, position);
        setupPdfIconClickListener(holder, model, position);
    }

    private void fillViews(PersonItemHolder holder, final PersonModel model) {
        holder.firstName.setText(model.getFirstName());
        holder.lastName.setText(model.getLastName());
        holder.position.setText(model.getPosition());
        holder.placeOfWork.setText(model.getPlaceOfWork());
        holder.prbFavorite.setVisibility(model.isProgressBarVisibilityState() ? View.VISIBLE : View.INVISIBLE);
    }

    private void fillIcons(PersonItemHolder holder, final PersonModel model) {
        holder.ivFavorite.setImageResource(model.isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_unfavorite);
        holder.ivFavorite.setVisibility(model.isProgressBarVisibilityState() ? View.INVISIBLE : View.VISIBLE);
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

    private void setupLikeClickListener(
            final PersonItemHolder holder,
            final PersonModel model,
            int position) {
        if (touchFavoriteListener != null) {
            holder.ivFavorite.setOnClickListener(v -> touchFavoriteListener.touchFavoriteIcon(model, position));
        }
    }

    public void setTouchPdfIconListener(TouchPdfIconListener<PersonModel> touchPdfIconListener) {
        this.touchPdfIconListener = touchPdfIconListener;
    }

    public void setTouchFavoriteListener(TouchFavoriteListener<PersonModel> touchFavoriteListener) {
        this.touchFavoriteListener = touchFavoriteListener;
    }

    @CheckResult
    public int findPositionByPersonId(PersonModel personModel) {
        PersonModel currPersonModel = null;

        for (int i = 0; i < getData().size(); i++) {

            currPersonModel = getData().get(i);

            if (currPersonModel == null) continue;
            if (!currPersonModel.getId().equals(personModel.getId())) continue;

            return i;
        }
        return -1;
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
