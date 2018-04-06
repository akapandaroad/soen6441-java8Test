package test.test.chap11Future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by akapandaroad on 2018-03-26.
 */
public class Test1 {



        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));



        public List<String> findPrices(String product){


            return shops.stream().map(shop -> String.format("%d price is %.2f",shop.getName(),shop.getPrice(product)))
                    .collect(Collectors.toList());
        }



        public List<String> findPrices2(String product){

            List<CompletableFuture<String>> pricesFuture=shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(()->shop.getName()+"price is"+shop.getPrice(product))).collect(Collectors.toList());

            return pricesFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());


        }
    public static void main(String[] args) {

            Test1 test1=new Test1();


    }
}
