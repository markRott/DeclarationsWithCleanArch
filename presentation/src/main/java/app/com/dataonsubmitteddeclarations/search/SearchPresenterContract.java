package app.com.dataonsubmitteddeclarations.search;

import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;

public interface SearchPresenterContract {

    void lifeSearchByInputText(final Flowable<String> textViewFlowable);

    void dropCurrentQuery();

    void favoriteRequest(final PersonModel personModel);
}
