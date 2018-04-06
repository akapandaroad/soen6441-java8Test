package test.test.ObserveDesignpTTERN;

/**
 * Created by akapandaroad on 2018-03-04.
 */
public class test {
    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");


        f.registerObserver((String tweet)->{if(tweet!=null&&tweet.contains("queen")) {
            System.out.println("i am your father");
        }
        });

    }
}
