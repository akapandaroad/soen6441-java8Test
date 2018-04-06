package test.test.ObserveDesignpTTERN;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akapandaroad on 2018-03-04.
 */
class Feed implements Subject{
    private final List<Observer> observers = new ArrayList<>();
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    } }
