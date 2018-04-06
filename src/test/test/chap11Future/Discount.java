package test.test.chap11Future;

import static test.test.chap11Future.Util.delay;
import static test.test.chap11Future.Util.format;

/**
 * Created by akapandaroad on 2018-03-26.
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        delay();
        return (price * (100 - code.percentage) / 100);
    }
}
