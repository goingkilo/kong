package lego.stocks;

/**
 * Created by kraghunathan on 5/28/17.
 */
public class Stock {

    String date,open,high,low,close,volume,adjustedClose,symbol,name;

    public Stock(String date, String open,  String high, String low, String close, String volume, String adjustedClose, String symbol, String name) {
        this.adjustedClose = adjustedClose;
        this.close = close;
        this.date = date;
        this.high = high;
        this.low = low;
        this.name = name;
        this.open = open;
        this.symbol = symbol;
        this.volume = volume;
    }

    public Stock() {
    }

    public String getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(String adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
