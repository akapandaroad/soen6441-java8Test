package test.test.ObserveDesignpTTERN;

/**
 * Created by akapandaroad on 2018-03-04.
 */
class NYTimes implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY! " + tweet);
        } }
}
