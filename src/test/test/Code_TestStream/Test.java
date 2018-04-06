package test.test.Code_TestStream;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by akapandaroad on 2018-02-07.
 */
public class Test {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));





        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().map(a->a*a).collect(toList());
        System.out.println(squares);

        /**Find all transactions in 2011 and sort by value (small to high)*/
        transactions.stream().filter(transaction -> transaction.getYear()==2011).sorted(comparing(Transaction::getValue)).collect(toList());





        List<Transaction> tr2011= transactions.stream()
                .filter(transaction -> transaction.getYear()==2011)
                .sorted(comparing(Transaction::getValue))
                .collect(Collectors.toList());



        System.out.println(tr2011);



        /**What are all the unique cities where the traders work?*/


        List<String> cities=transactions.stream()
                .map(transaction -> transaction.getTrader()
                        .getCity()).distinct().collect(Collectors.toList());



        Set<String> cities2 =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .collect(toSet());
        System.out.println(cities);
        /**Find all traders from Cambridge and sort them by name*/



      //
        //  List<Trader> haha=transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals(" ")).sorted(comparing(Trader::getName)).collect(Collectors.toList());



        List<Trader> traders=transactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(comparing(Trader::getName)).collect(Collectors.toList());




        /**Return a string of all traders’ names sorted alphabetically*/


        String tradeStr=transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().collect(joining());






        /**Are any traders based in Milan?*/

        Boolean hhh=transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("mILAN"));



        boolean milanBased=transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));





        /**Print all transactions’ values from the traders living in Cambridge*/


        transactions.stream().map(transaction -> transaction.getTrader()).filter(trader -> trader.getCity().equals("Cambridge")).collect(toList());

        //List<Transaction> valeshaha=transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Cambridge")) .collect(Collectors.toList());
        //error mehtod





        List<Transaction> values=transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.toList());


        System.out.println(values+"---------");

       // System.out.println(valeshaha+"==========");


        /**What’s the highest value of all the transactions?*/


        transactions.stream().map(Transaction::getValue).reduce(Integer::max);


        System.out.println("What’s the highest value of all the transactions?");
        Optional<Integer> highvalue=transactions.stream()
                .map(transaction -> transaction.getValue()).reduce(Integer::min);

        Optional<Integer> highvalue2=transactions.stream()
                .map(Transaction::getValue).reduce(Integer::max);


        System.out.println(highvalue+"---"+highvalue2);



        /**Find the transaction with the smallest value*/


        Optional<Transaction> smallestTransaction =transactions.stream().min(comparing(Transaction::getValue));




        transactions.stream().map(Transaction::getTrader).sorted(comparing(trader -> trader.getCity()));




    }
}