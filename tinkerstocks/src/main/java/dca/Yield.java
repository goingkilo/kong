package dca;

/**
 * Created by kraghunathan on 8/21/17.
 */
public class Yield {

    private final int startYear;
    private final int startMonth;
    private final int multiple;
    private final float netCost;
    private final int netQty;
    private final int tenureMonths;


    private   float salePrice;


    private float yieldPercent;

    public Yield(int multiple, int startYear, int startMonth, float netCost, int netQty, int tenureMonths) {
        this.multiple = multiple;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.netCost = netCost;
        this.netQty = netQty;
        this.tenureMonths = tenureMonths;
    }

    public int getMultiple() {
        return multiple;
    }

    public float getNetCost() {
        return netCost;
    }

    public int getNetQty() {
        return netQty;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public void calculateYield() {
        this.yieldPercent = (salePrice - (netCost/netQty))*100;
    }

    public float getYield() {
        return yieldPercent;
    }

}
