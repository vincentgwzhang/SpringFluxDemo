package org.personal.springfluxdemo.reactor8;

import java.util.Observable;
import org.junit.jupiter.api.Test;

public class ObserverDemo extends Observable
{
    @Test
    public void testCase1() {
        ObserverDemo observerDemo = new ObserverDemo();
        observerDemo.addObserver((o, args)-> {
            System.out.println("changed:");
        });

        observerDemo.addObserver((o, args)-> {
            System.out.println("changed:");
        });

        observerDemo.setChanged();

        observerDemo.notifyObservers();
    }
}
