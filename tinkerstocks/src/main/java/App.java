import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        Stock tesla = YahooFinance.get( args[0], true);
        System.out.println(tesla.getHistory());
    }
}
