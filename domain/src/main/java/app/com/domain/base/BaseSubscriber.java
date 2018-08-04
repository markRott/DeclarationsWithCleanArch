package app.com.domain.base;

import io.reactivex.subscribers.DisposableSubscriber;

public class BaseSubscriber<T> extends DisposableSubscriber<T> {

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onError(Throwable t) {
    }

    @Override
    public void onComplete() {
    }
}
