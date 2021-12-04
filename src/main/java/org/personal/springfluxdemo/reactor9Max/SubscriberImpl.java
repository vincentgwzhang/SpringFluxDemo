package org.personal.springfluxdemo.reactor9Max;

import java.util.concurrent.Flow;

public class SubscriberImpl implements Flow.Subscriber<Book> {

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.cancel();
    }

    @Override
    public void onNext(Book item) {
        System.out.println("Begin to process book:" + item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError triggered");
    }

    @Override
    public void onComplete() {
        System.out.println("publish complete");
    }
}
