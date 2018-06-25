package dca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kraghunathan on 8/21/17.
 */
public class Simulation {


    public List<Yield> runningYield(final List<Stock> monthlyPrices, int startYear, int endYear){
        List<Yield> ret = new ArrayList<Yield>();
        System.out.println( monthlyPrices.get(0).getDate().getMonth());
        for( int i = startYear ; i < endYear ; i++ ) {
            ret.add( sim( monthlyPrices, 25, 60, i, 1));
        }
        return ret;
    }

    public Yield sim(final List<Stock> monthlyPrices, final int multiple, final int tenureMonths, final int startingYear, final int startingMonth ){

        float moneySpent = 0;
        int purchasedQty = 0;

        int startingIndex = getStartingIndex( monthlyPrices, startingYear, startingMonth);
        if( startingIndex == -1)
            return null;

        float startingPrice = monthlyPrices.get(startingIndex).getOpen();
        float fixedAmount = multiple  * startingPrice;

        for( int i  = startingIndex ; i < startingIndex + tenureMonths ; i++) {
            float price = monthlyPrices.get(i).getOpen();
            int qty = (int) (fixedAmount / price);

            purchasedQty += qty;
            moneySpent += (qty * price);
        }

        Yield y = new Yield(multiple, startingYear, startingMonth, moneySpent, purchasedQty, tenureMonths);
        y.setSalePrice( monthlyPrices.get( startingIndex + tenureMonths+1).getOpen());
        y.calculateYield();
        return y;
    }

    int getStartingIndex(final List<Stock> monthlyPrices, int startingYear, int startingMonth){
        for( int i = 0 ; i < monthlyPrices.size() ; i++) {
            Date d = monthlyPrices.get(i).getDate();

            int m = d.getMonth()+1;
            int y = d.getYear()+1900;
            if( startingYear == y && startingMonth == m) {
                return i;
            }

        }
        return -1;
    }
}
