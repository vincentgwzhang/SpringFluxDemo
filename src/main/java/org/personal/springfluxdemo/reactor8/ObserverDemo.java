package org.personal.springfluxdemo.reactor8;

import org.junit.jupiter.api.Test;

import java.util.Observable;

public class ObserverDemo extends Observable {
    @Test
    public void testCase1() {

        ObserverDemo observerDemo = new ObserverDemo();

        observerDemo.addObserver((o, args) -> {
            System.out.println("changed:" + args);
        });

        observerDemo.addObserver((o, args) -> {
            System.out.println("changed:");
        });

        observerDemo.setChanged();

        observerDemo.notifyObservers();
    }
}
