package app.com.domain.base;

import app.com.domain.interfaces.ThreadContract;

public class BaseInteractor {

    private ThreadContract threadContract;

    public BaseInteractor(ThreadContract threadContract) {
        this.threadContract = threadContract;
    }
}
