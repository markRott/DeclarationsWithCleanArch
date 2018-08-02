package app.com.domain.interfaces;

import io.reactivex.Scheduler;

public interface ThreadContract {

    Scheduler io();

    Scheduler computation();

    Scheduler ui();
}
