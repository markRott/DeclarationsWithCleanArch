package app.com.dataonsubmitteddeclarations.utils;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {

    private static RxBus instance;

    public static RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }
        return instance;
    }

    private RxBus() {
    }

    private PublishSubject<Object> subject = PublishSubject.create();

    public void sendData(Object object) {
        subject.onNext(object);
    }

    public Observable<Object> getSubject() {
        return subject;
    }
}
