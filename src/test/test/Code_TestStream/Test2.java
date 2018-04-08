package test.test.Code_TestStream;

import org.junit.*;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Created by akapandaroad on 2018-04-07.
 */
public class Test2 {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");
    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950));


    /**
     * Find all transactions in 2011 and sort by value (small to high)
     */

    @Test
    public void Test1() {
        transactions.stream().
                filter(transaction -> transaction.getYear() == 2011)
                .sorted((t1,t2)->-t1.getValue()+t2.getValue())//可以从小到大，也可以从大到小
                .forEach(System.out::println);

        transactions.stream().sorted(Comparator.comparing(Transaction::getValue).reversed());


    }



    /**What are all the unique cities where the traders work?*/


    @Test
    public void Test2() {
        transactions.stream().map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);

    }


    /**Find all traders from Cambridge and sort them by name*/
    @Test
    public void Test3() {
        List<Trader> result = transactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());




        System.out.println(result);
                
    }
    

    /**Return a string of all traders’ names sorted alphabetically*/

    @Test
    public void Test4(){

        transactions.stream().map(Transaction::getTrader)
                .map(Trader::getName).sorted().forEach(System.out::println);


    }

    /**Are any traders based in Milan?*/

    @Test
    public void Test5(){

        Boolean result=transactions.stream().map(transaction -> transaction.getTrader())
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        System.out.println(result);


    }



    /**Print all transactions’ values from the traders living in Cambridge*/
    @Test
    public void test6(){
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue).reduce(Integer::sum);

    }

    /**What’s the highest value of all the transactions?*/

    @Test
    public void test7(){
        Optional<Integer> result = transactions.stream().max(Comparator.comparing(Transaction::getValue))
                .map(Transaction::getValue);
        System.out.println(result.get());

        Optional<Integer> result2 = transactions.stream().map(Transaction::getValue).max(Integer::compare);
        System.out.println(result2.get());


    }

    /**Find the transaction with the smallest value*/


    @Test
    public void test8(){

        Instant start =Instant.now();
        OptionalInt result = IntStream.rangeClosed(0, 100).parallel().reduce(Integer::sum);


        System.out.println(result.getAsInt());
        Instant end=Instant.now();


        System.out.println(Duration.between(start,end));


    }


}
