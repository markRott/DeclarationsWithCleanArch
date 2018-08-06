package app.com.dataonsubmitteddeclarations.managers;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class CompositeDisposableManager {

    private final CompositeDisposable compositeDisposable;

    public CompositeDisposableManager() {
        this.compositeDisposable = new CompositeDisposable();
    }

    public void addDisposable(final Disposable disposable) {
        if (disposable == null) return;
        compositeDisposable.add(disposable);
//        Timber.d("Add disposable object = %s", disposable.toString());
    }

    public void clearCompositeDisposable() {
        if (!compositeDisposable.isDisposed()) {
//            Timber.d("Clear composite disposable. Current size = %s", compositeDisposable.size());
            compositeDisposable.clear();
        }
    }

    @Override
    public String toString() {
        return "CompositeDisposableManager{" +
                "compositeDisposable=" + compositeDisposable +
                '}';
    }
}
