package app.com.dataonsubmitteddeclarations.search;

import io.reactivex.Flowable;

public interface SearchPresenterContract {

    void lifeSearchByInputText(final Flowable<String> textViewFlowable);

    void dropCurrentQuery();
}
