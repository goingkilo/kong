package dca;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kraghunathan on 8/20/17.
 */
public class StockDataHandler {

    static String dataPath = "/Users/kraghunathan/k/flask/stocks/data/";

    static String[] stockNames  = {
            "acc","adaniports","ambujacem","asianpaint","auropharma","axisbank",
            "bajaj-auto","bankbaroda","bhartiartl","bhel","boschltd","bpcl",
            "canbk","cipla","coalindia",
            "dabur","drreddy",
            "eichermot",
            "first_working_days",
            "gail","grasim",
            "hcltech","hdfc","hdfcbank","heromotoco","hindalco","hindunilvr",
            "icicibank","idea","indusindbk","infratel","infy","itc",
            "kotakbank","lt","lupin","mahabank","maruti","ntpc","ongc","pel","powergrid","reliance","sbin","sunpharma","tatamotors","tatamtrdvr","tatapower","tatasteel","tcs","techm","tvsmotor","ultracemco","wipro","yesbank","zeel.csv"
    };

    public static void main(String[] args) throws IOException, ParseException {
        //new App().go( path + "mahabank.csv" );//stockNames[1]);
    }

    public List<Stock> loadAll(String scrip)  throws IOException, ParseException {
        List<Stock> stocks = new ArrayList<Stock>();
        String file = dataPath + scrip + ".csv";
        CSVReader reader = new CSVReader(new FileReader(file), ',');
        String[] row;
        boolean first = true;
        while ((row = reader.readNext()) != null) {
            if (first) {
                first = false;
                continue;
            }

            Stock stock = new Stock(row);
            stocks.add(stock);
        }
        Collections.reverse(stocks);
        return  stocks ;
    }

    public List<Stock> loadMonthly( String scrip, List<Stock> stocks)  throws IOException, ParseException {
        List<Stock> monthly = new ArrayList<Stock>();
        int currentMonth = stocks.get(0).getDate().getMonth();
        monthly.add(stocks.get(0));
        for( int i = 0 ;  i < stocks.size() ;i++) {
            if( stocks.get(i).getDate().getMonth() == currentMonth) {
                continue;
            }
            else {
                currentMonth = stocks.get(i).getDate().getMonth();
                monthly.add(stocks.get(i));
            }
        }

        return monthly;
    }

}
