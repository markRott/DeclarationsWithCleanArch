package app.com.domain.base;

import app.com.domain.interfaces.ThreadContract;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseInteractor {

    private ThreadContract threadContract;
    private CompositeDisposable compositeDisposable;

    public BaseInteractor(ThreadContract threadContract) {
        this.threadContract = threadContract;
        compositeDisposable = new CompositeDisposable();
    }

    protected void dispose() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
//            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }

    protected void addDisposable(final Disposable localDisposable) {
        if (localDisposable == null) return;
        compositeDisposable.add(localDisposable);
    }
}
