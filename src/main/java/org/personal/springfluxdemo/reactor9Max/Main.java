package org.personal.springfluxdemo.reactor9Max;

import java.util.concurrent.Flow;

public class Main {

    public static void main(String[] args) {
        Flow.Publisher<String> publisher = subscriber -> {
            subscriber.onNext("1111"); // 1
            subscriber.onNext("2222");
            subscriber.onError(new RuntimeException("Runtime exception")); // 2
            //  subscriber.onComplete();
        };


        publisher.subscribe(new Flow.Subscriber<>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.cancel();
            }

            @Override
            public void onNext(String item) {
                System.out.println(item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError triggered");
            }

            @Override
            public void onComplete() {
                System.out.println("publish complete");
            }
        });
    }
}
