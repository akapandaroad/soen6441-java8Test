package test.test.ObserveDesignpTTERN;

/**
 * Created by akapandaroad on 2018-03-04.
 */
interface Subject{
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
