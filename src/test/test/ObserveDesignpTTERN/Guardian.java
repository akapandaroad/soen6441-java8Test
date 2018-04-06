package test.test.ObserveDesignpTTERN;

/**
 * Created by akapandaroad on 2018-03-04.
 */
class Guardian implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("queen")){
            System.out.println("Yet another news in London... " + tweet);
        }
    } }
