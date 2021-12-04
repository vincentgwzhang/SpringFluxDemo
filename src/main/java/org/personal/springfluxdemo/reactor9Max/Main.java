package org.personal.springfluxdemo.reactor9Max;

import java.util.concurrent.Flow;

public class Main {

    public static void main(String[] args) {
        Flow.Publisher<Book> publisher = subscriber -> {
            subscriber.onNext(new Book("name 1", "Vincent"));
            subscriber.onNext(new Book("name 2", "Yan yan"));
            subscriber.onError(new RuntimeException("Runtime exception"));
            subscriber.onNext(new Book("name 3", "Weimianren"));
            subscriber.onComplete();

            /*
             * 尽管已经执行 subscriber.onComplete();
             * 但是没有做成错误
             */
            subscriber.onNext(new Book("name 4", "Vincent Zhang"));
        };

        publisher.subscribe(new SubscriberImpl());
    }
}
