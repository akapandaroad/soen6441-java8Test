package test.test.ObserveDesignpTTERN;

/**
 * Created by akapandaroad on 2018-03-04.
 */
class LeMonde implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")){
            System.out.println("Today cheese, wine and news! " + tweet);
        } }
}